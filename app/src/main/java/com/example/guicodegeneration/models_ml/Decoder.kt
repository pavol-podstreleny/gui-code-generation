package com.example.guicodegeneration.models_ml

import android.content.Context
import com.example.guicodegeneration.models_ml.input.DecoderInput
import com.example.guicodegeneration.utils.TokenUtils
import org.pytorch.IValue
import org.pytorch.Tensor


class Decoder(context: Context, modelName: String) :
    BaseModel<DecoderInput>(context, modelName = modelName) {

    private val timeSteps = 150
    private val endTokenID = TokenUtils.getEndTokenId()

    override fun predict(input: DecoderInput): Tensor {
        val annotationVectors = input.annotationVectors;
        var h = input.initialHiddenState;
        var caption = input.start_token

        val tokens = arrayListOf<Long>()

        // Iterate for timeSteps or till <end> token is predicted
        for (time in 0..timeSteps) {
            val outputs = module.forward(
                IValue.from(annotationVectors), IValue.from(caption),
                IValue.from(h)
            ).toTuple()

            h = outputs[0].toTensor()
            caption = outputs[1].toTensor()

            tokens.add(caption.dataAsLongArray[0])

            if (endTokenID == caption.dataAsLongArray[0]) {
                break
            }

        }

        //Remove end token
        tokens.removeAt(tokens.size - 1)

        //Return predicted ids
        return Tensor.fromBlob(tokens.toLongArray(), longArrayOf(tokens.size.toLong()))
    }
}