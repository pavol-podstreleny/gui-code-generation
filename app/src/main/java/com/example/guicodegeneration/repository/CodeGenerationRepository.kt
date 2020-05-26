package com.example.guicodegeneration.repository

import android.view.View
import androidx.annotation.UiThread
import com.example.guicodegeneration.compiler.Compiler
import com.example.guicodegeneration.utils.TokenUtils

class CodeGenerationRepository(private val compiler: Compiler) {

    @UiThread
    fun generateView(tokens: LongArray): View? {
        val stringTokens = arrayListOf<String>()

        for (tokenID in tokens) {
            stringTokens.add(TokenUtils.getTokenString(tokenID))
        }

        return compiler.compile(stringTokens.toTypedArray())
    }

}