package com.devzv.composetestbootom.screen

sealed class AbstractScreen constructor(val route: String, val title: String = "")

sealed class AppScreen(route: String, title: String = "") : AbstractScreen("AppScreen_$route", title) {
    object Main : AppScreen("main", "Main")
    object Catalog : AppScreen("catalog", "Catalog")
    object Cart : AppScreen("cart", "Cart")
    object Shops : AppScreen("shops", "Shops")
    object Profile : AppScreen("profile", "Profile")
    object Registration : AppScreen("registration")
    object Language : AppScreen("language", "Language")
}

sealed class RegistrationScreen(route: String, title: String = "") : AbstractScreen("RegistrationScreen_$route", title) {
    object Login : RegistrationScreen("login", "Login")
    object Password : RegistrationScreen("password", "Password")
}

sealed class CatalogScreen(route: String, title: String = "") : AbstractScreen("CatalogScreen_$route", title) {
    object TopLevel : CatalogScreen("toplevel", "Top level")
    object FirstLevel : CatalogScreen("firstlevel", "First level")
    object SecondLevel : CatalogScreen("secondlevel", "Second level")
}
