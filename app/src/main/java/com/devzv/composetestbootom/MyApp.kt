package com.devzv.composetestbootom

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.devzv.composetestbootom.screen.registration.PasswordViewModel
import com.devzv.composetestbootom.screen.registration.RegistrationUseCase
import org.kodein.di.*

class MyApp : Application(), DIAware {

    override lateinit var di: DI
        private set

    private val appDIModule = DI.Module(name = "APP") {
        bind<RegistrationUseCase>() with singleton(ref = softReference) {
            RegistrationUseCase()
        }
        bind<ViewModelProvider.Factory>(tag = "PasswordViewModel") with provider {
            PasswordViewModel.Factory(instance())
        }
    }

    override fun onCreate() {
        super.onCreate()

        di = DI {
            import(appDIModule)
        }
    }



}