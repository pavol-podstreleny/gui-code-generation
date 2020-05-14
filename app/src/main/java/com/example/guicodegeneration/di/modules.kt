package com.example.guicodegeneration.di

import com.example.guicodegeneration.AppExecutor
import com.example.guicodegeneration.repository.NeuralNetworkRepository
import com.example.guicodegeneration.viewmodel.NeuralNetworkViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    single { NeuralNetworkRepository(AppExecutor.getModelExecutor()) }
    viewModel { NeuralNetworkViewModel(get()) }
}