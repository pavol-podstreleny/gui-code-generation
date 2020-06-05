package com.example.guicodegeneration

import java.util.concurrent.Executor
import java.util.concurrent.Executors

object AppExecutor {

    private val model = Executors.newSingleThreadExecutor()

    fun getModelExecutor(): Executor {
        return model
    }

}