package com.example.guicodegeneration.repository

import android.content.res.AssetManager
import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.guicodegeneration.models_ml.Decoder
import com.example.guicodegeneration.models_ml.Encoder
import com.example.guicodegeneration.models_ml.InitialHiddenState
import com.example.guicodegeneration.models_ml.input.DecoderInput
import com.example.guicodegeneration.utils.BitmapBag
import com.example.guicodegeneration.utils.BitmapUtils
import java.util.concurrent.Executor

class NeuralNetworkRepository(
    private val neuralNetworkExecutor: Executor,
    private val encoder: Encoder,
    private val initialHiddenState: InitialHiddenState,
    private val decoder: Decoder
) {

    fun loadTestImageBitmaps(assetManager: AssetManager): LiveData<ArrayList<BitmapBag>> {
        val testImages = MutableLiveData<ArrayList<BitmapBag>>()
        neuralNetworkExecutor.execute {
            testImages.postValue(BitmapUtils.loadBitmapsFromAssets(assetManager))
        }
        return testImages
    }

    fun generateTokenIds(bitmap: Bitmap): LiveData<LongArray> {
        val liveData = MutableLiveData<LongArray>()

        neuralNetworkExecutor.execute {
            val annotationVectors = encoder.predict(bitmap)
            val h = initialHiddenState.predict(annotationVectors)
            val result = decoder.predict(DecoderInput(annotationVectors, h))
            liveData.postValue(result.dataAsLongArray)
        }
        return liveData
    }

}