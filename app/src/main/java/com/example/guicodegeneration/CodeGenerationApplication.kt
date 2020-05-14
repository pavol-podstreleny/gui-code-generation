package com.example.guicodegeneration

import android.app.Application
import com.example.guicodegeneration.di.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CodeGenerationApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@CodeGenerationApplication)

            modules(
                listOf(
                    mainModule
                )
            )
        }
    }

}