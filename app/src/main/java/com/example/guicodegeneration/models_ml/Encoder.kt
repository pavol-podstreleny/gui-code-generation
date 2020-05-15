package com.example.guicodegeneration.models_ml

import android.content.Context
import android.graphics.Bitmap
import com.example.guicodegeneration.utils.BitmapUtils
import org.pytorch.IValue
import org.pytorch.Tensor
import org.pytorch.torchvision.TensorImageUtils

class Encoder(context: Context, modelName: String) :
    BaseModel<Bitmap>(context = context, modelName = modelName) {

    private val width = 224
    private val height = 224

    override fun predict(input: Bitmap): Tensor {
        val rescaledBitmap = BitmapUtils.rescaleBitmap(width, height, input)

        val floatTensor = TensorImageUtils.bitmapToFloat32Tensor(
            rescaledBitmap,
            TensorImageUtils.TORCHVISION_NORM_MEAN_RGB,
            TensorImageUtils.TORCHVISION_NORM_STD_RGB
        )

        //Return annotation vectors
        return module.forward(IValue.from(floatTensor)).toTensor()
    }
}