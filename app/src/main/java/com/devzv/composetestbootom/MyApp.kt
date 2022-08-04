package com.devzv.composetestbootom

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.disk.DiskCache
import coil.imageLoader
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.devzv.composetestbootom.data.getImages
import com.devzv.composetestbootom.screen.registration.PasswordViewModel
import com.devzv.composetestbootom.screen.registration.RegistrationUseCase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import net.sqlcipher.database.SupportFactory
import org.kodein.di.*

class MyApp : Application(), DIAware, ImageLoaderFactory {

    override lateinit var di: DI
        private set

    companion object {
        private const val PRELOAD_IMAGE = false
        private const val ENCRYPT_DB = true
    }

    private val appDIModule = DI.Module(name = "APP") {
        bindSingleton<Context> {
            this@MyApp
        }
        bindSingleton {
            val driver =
                if (ENCRYPT_DB) {
                    AndroidSqliteDriver(
                        schema = Database.Schema,
                        context = instance(),
                        name = "app_sqldelight_database_enc",
                        factory = SupportFactory(byteArrayOf(1))
                    )
                } else {
                    AndroidSqliteDriver(
                        schema = Database.Schema,
                        context = instance(),
                        name = "app_sqldelight_database",
                    )
                }
            Database(driver)
        }
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

        if (PRELOAD_IMAGE) {
            getImages().forEach {
                imageLoader.enqueue(
                    ImageRequest.Builder(this)
                        .data(it)
                        .memoryCachePolicy(CachePolicy.DISABLED)
                        .build()
                )
            }
        }
    }


}