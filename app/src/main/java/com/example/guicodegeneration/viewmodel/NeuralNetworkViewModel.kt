package com.example.guicodegeneration.viewmodel

import android.content.res.AssetManager
import android.graphics.Bitmap
import android.net.Uri
import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.guicodegeneration.repository.NeuralNetworkRepository
import com.example.guicodegeneration.utils.BitmapBag

class NeuralNetworkViewModel(neuralNetworkRepository: NeuralNetworkRepository) : ViewModel() {

    private val loadBitmap = MutableLiveData<AssetManager>()
    private val bitmap = MutableLiveData<Bitmap>()

    var bitmapUri: Uri? = null

    val testImageBitmaps: LiveData<ArrayList<BitmapBag>> =
        Transformations.switchMap(loadBitmap) { data ->
            neuralNetworkRepository.loadTestImageBitmaps(data)
        }

    val predictedTokens: LiveData<LongArray> = Transformations.switchMap(bitmap) { data ->
        neuralNetworkRepository.generateTokenIds(data)
    }

    fun loadTestImageBitmaps(assetManager: AssetManager) {
        this.loadBitmap.value = assetManager
    }

    @MainThread
    fun predictTokens(bitmap: Bitmap, uri: Uri) {
        this.bitmapUri = uri
        this.bitmap.value = bitmap
    }
}