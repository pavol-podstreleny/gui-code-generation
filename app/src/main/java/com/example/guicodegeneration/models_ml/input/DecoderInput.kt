package com.example.guicodegeneration.models_ml.input

import com.example.guicodegeneration.utils.TokenUtils
import org.pytorch.Tensor

data class DecoderInput(
    val annotationVectors: Tensor,
    val initialHiddenState: Tensor,
    val start_token: Tensor = Tensor.fromBlob(
        longArrayOf(TokenUtils.getEndTokenId())
        , longArrayOf(1, 1)
    )
)