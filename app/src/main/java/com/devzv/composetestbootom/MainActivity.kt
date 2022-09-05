package com.devzv.composetestbootom

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.*
import com.devzv.composetestbootom.screen.AppScreen
import com.devzv.composetestbootom.screen.CatalogScreen
import com.devzv.composetestbootom.screen.RegistrationScreen
import com.devzv.composetestbootom.screen.cart.CartScreen
import com.devzv.composetestbootom.screen.catalog.CatalogFirstLevelScreen
import com.devzv.composetestbootom.screen.catalog.CatalogSecondLevelScreen
import com.devzv.composetestbootom.screen.catalog.CatalogTopLevelScreen
import com.devzv.composetestbootom.screen.language.LanguageScreen
import com.devzv.composetestbootom.screen.main.MainScreen
import com.devzv.composetestbootom.screen.profile.ProfileScreen
import com.devzv.composetestbootom.screen.registration.LoginScreen
import com.devzv.composetestbootom.screen.registration.LoginViewModel
import com.devzv.composetestbootom.screen.registration.PasswordScreen
import com.devzv.composetestbootom.screen.registration.PasswordViewModel
import com.devzv.composetestbootom.screen.shops.ShopsScreen
import com.devzv.composetestbootom.ui.language.LocalLanguage
import com.devzv.composetestbootom.ui.language.SupportedLanguage
import com.devzv.composetestbootom.ui.theme.ComposeTestBootomTheme
import org.kodein.di.compose.androidContextDI
import org.kodein.di.compose.rememberInstance
import org.kodein.di.compose.withDI

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTestBootomTheme {
                val viewModel: MainViewModel = viewModel()
                CompositionLocalProvider(LocalLanguage provides viewModel.currentLanguage) {
                    AppScreen(onLanguageChange = { newLang ->
                        viewModel.onLanguageChange(newLang)
                    })
                }
            }
        }
    }
}

@Composable
fun AppScreen(onLanguageChange: (SupportedLanguage) -> Unit) {
    val appScreens = setOf(
        AppScreen.Main,
        AppScreen.Catalog,
        AppScreen.Cart,
        AppScreen.Shops,
        AppScreen.Profile,
        AppScreen.Language,
    )
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigation {
                val currentBackStackEntry by navController.currentBackStackEntryAsState()
                appScreens.forEach { screen ->
                    BottomNavigationItem(
                        selected = currentBackStackEntry?.destination?.hierarchy?.any { it.route == screen.route } == true ,
                        icon = {},
                        label = { Text(text = screen.title) },
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            startDestination = AppScreen.Main.route,
        ) {
            composable(route = AppScreen.Main.route) {
                //CompositionLocalProvider(MyApp.LocalLanguage provides SupportedLanguage.LT) {
                    MainScreen()
                //}
            }
            catalogGraph(navController)

            composable(route = AppScreen.Cart.route) {
                CartScreen()
            }
            composable(route = AppScreen.Shops.route) {
                ShopsScreen()
            }
            composable(route = AppScreen.Profile.route) {
                ProfileScreen {
                    navController.navigate(AppScreen.Registration.route)
                }
            }
            registrationGraph(navController)

            composable(route = AppScreen.Language.route){
                LanguageScreen(onLanguageChange)
            }
        }
    }
}

private fun NavGraphBuilder.registrationGraph(navController: NavController) {
    navigation(route = AppScreen.Registration.route, startDestination = RegistrationScreen.Login.route) {
        composable(route = RegistrationScreen.Login.route) {
            val context = LocalContext.current
            val vm: LoginViewModel = viewModel()
            LoginScreen(
                login = vm.login,
                onValueChanged = vm::onLoginChanged,
                onNextClick = {
                    if (vm.login.isNotEmpty()) {
                        navController.navigate("${RegistrationScreen.Password.route}/${vm.login}")
                    } else {
                        Toast.makeText(context, "EMPTY", Toast.LENGTH_SHORT).show()
                    }
                },
            )
        }
        composable(route = "${RegistrationScreen.Password.route}/{login}") {
            withDI(di = androidContextDI()) {
                val factory: ViewModelProvider.Factory by rememberInstance(tag = "PasswordViewModel")
                val vm: PasswordViewModel = viewModel(factory = factory)
                if (vm.registerSuccess) {
                    navController.navigate(AppScreen.Main.route) {
                        popUpTo(AppScreen.Main.route)
                        launchSingleTop = true
                        restoreState = true
                    }
                } else {
                    PasswordScreen(
                        password = vm.password,
                        onValueChanged = vm::onPasswordChanged,
                        onApplyClick = {
                            vm.onApplyClick(it.arguments?.getString("login") ?: "")
                        }
                    )
                }
            }

        }
    }
}

private fun NavGraphBuilder.catalogGraph(navController: NavController) {
    navigation(route = AppScreen.Catalog.route, startDestination = CatalogScreen.TopLevel.route) {
        composable(route = CatalogScreen.TopLevel.route) {
            CatalogTopLevelScreen {
                navController.navigate(CatalogScreen.FirstLevel.route)
            }
        }
        composable(route = CatalogScreen.FirstLevel.route) {
            CatalogFirstLevelScreen {
                navController.navigate(CatalogScreen.SecondLevel.route)
            }
        }
        composable(route = CatalogScreen.SecondLevel.route) {
            CatalogSecondLevelScreen {
                navController.popBackStack()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeTestBootomTheme {
        AppScreen(onLanguageChange = {})
    }
}