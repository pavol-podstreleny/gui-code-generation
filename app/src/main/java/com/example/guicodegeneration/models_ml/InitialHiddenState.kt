package com.example.guicodegeneration.models_ml

import android.content.Context
import org.pytorch.IValue
import org.pytorch.Tensor

class InitialHiddenState(context: Context, modelName: String) :
    BaseModel<Tensor>(context = context, modelName = modelName) {

    override fun predict(input: Tensor): Tensor {
        return module.forward(IValue.from(input)).toTensor()
    }
}