package com.example.guicodegeneration

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.Executors

object AppExecutor {

    private val model = Executors.newSingleThreadExecutor()

    fun getModelExecutor(): Executor {
        return model
    }

}