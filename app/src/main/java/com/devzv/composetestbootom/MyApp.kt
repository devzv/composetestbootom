package com.devzv.composetestbootom

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.disk.DiskCache
import coil.imageLoader
import coil.request.CachePolicy
import coil.request.ImageRequest
import coil.size.ViewSizeResolver
import com.devzv.composetestbootom.data.imagePaths
import com.devzv.composetestbootom.screen.registration.PasswordViewModel
import com.devzv.composetestbootom.screen.registration.RegistrationUseCase
import org.kodein.di.*

class MyApp : Application(), DIAware, ImageLoaderFactory {

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

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .crossfade(true)
            .diskCache {
                DiskCache.Builder()
                    .directory(cacheDir.resolve("coil_image_cache"))
                    .maxSizePercent(0.5)
                    .build()
            }
            .build()
    }

    override fun onCreate() {
        super.onCreate()

        di = DI {
            import(appDIModule)
        }

        val preloadImage = true
        if (preloadImage) {
            imagePaths.forEach {
                imageLoader.enqueue(ImageRequest.Builder(this)
                    .data(it)
                    .memoryCachePolicy(CachePolicy.DISABLED)
                    .build()
                )
            }
        }
    }



}