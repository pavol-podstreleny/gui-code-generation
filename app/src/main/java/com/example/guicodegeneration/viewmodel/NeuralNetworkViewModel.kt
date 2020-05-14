package com.example.guicodegeneration.viewmodel

import android.content.res.AssetManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.guicodegeneration.repository.NeuralNetworkRepository
import com.example.guicodegeneration.utils.BitmapBag

class NeuralNetworkViewModel(neuralNetworkRepository: NeuralNetworkRepository) : ViewModel() {

    private val loadBitmap = MutableLiveData<AssetManager>()

    val testImageBitmaps: LiveData<ArrayList<BitmapBag>> =
        Transformations.switchMap(loadBitmap) { data ->
            neuralNetworkRepository.loadTestImageBitmaps(data)
        }


    fun loadTestImageBitmaps(assetManager: AssetManager) {
        this.loadBitmap.value = assetManager
    }
}