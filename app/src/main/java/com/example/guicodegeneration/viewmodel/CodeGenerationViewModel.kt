package com.example.guicodegeneration.viewmodel

import android.view.View
import androidx.annotation.MainThread
import androidx.lifecycle.ViewModel
import com.example.guicodegeneration.repository.CodeGenerationRepository

class CodeGenerationViewModel(
    private val codeGenerationRepository: CodeGenerationRepository
) : ViewModel() {

    @MainThread
    fun generateTokens(tokens: LongArray): View? {
        return codeGenerationRepository.generateView(tokens)
    }

}