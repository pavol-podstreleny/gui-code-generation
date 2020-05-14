package com.example.guicodegeneration.repository

import android.content.res.AssetManager
import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.guicodegeneration.utils.BitmapBag
import com.example.guicodegeneration.utils.BitmapUtils
import java.util.concurrent.Executor

class NeuralNetworkRepository(
    private val neuralNetworkExecutor: Executor
) {

    fun loadTestImageBitmaps(assetManager: AssetManager): LiveData<ArrayList<BitmapBag>> {
        val testImages = MutableLiveData<ArrayList<BitmapBag>>()
        neuralNetworkExecutor.execute {
            testImages.postValue(BitmapUtils.loadBitmapsFromAssets(assetManager))
        }
        return testImages
    }

}