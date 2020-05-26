package com.example.guicodegeneration.di

import com.example.guicodegeneration.AppExecutor
import com.example.guicodegeneration.compiler.Compiler
import com.example.guicodegeneration.models_ml.Decoder
import com.example.guicodegeneration.models_ml.Encoder
import com.example.guicodegeneration.models_ml.InitialHiddenState
import com.example.guicodegeneration.repository.CodeGenerationRepository
import com.example.guicodegeneration.repository.NeuralNetworkRepository
import com.example.guicodegeneration.viewmodel.CodeGenerationViewModel
import com.example.guicodegeneration.viewmodel.NeuralNetworkViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    single { Encoder(get(), "encoder.pt") }
    single { InitialHiddenState(get(), "initial_hidden_state.pt") }
    single { Decoder(get(), "decoder.pt") }


    single { NeuralNetworkRepository(AppExecutor.getModelExecutor(), get(), get(), get()) }
    viewModel { NeuralNetworkViewModel(get()) }
}

val codeGenerationModule = module {
    single { Compiler(get()) }
    single { CodeGenerationRepository(get()) }
    viewModel { CodeGenerationViewModel(get()) }
}
