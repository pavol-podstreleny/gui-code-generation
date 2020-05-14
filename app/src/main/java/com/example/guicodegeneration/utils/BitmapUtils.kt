package com.example.guicodegeneration.utils

import android.content.res.AssetManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory

object BitmapUtils {

    private val assetImagesFolder = "testimages"


    fun loadBitmapsFromAssets(assetManager: AssetManager): ArrayList<BitmapBag> {
        val bitmapList = arrayListOf<BitmapBag>()
        val rootAssetDir = assetManager.list(assetImagesFolder)
        if (rootAssetDir != null) {
            for (file in rootAssetDir) {
                bitmapList.add(
                    BitmapBag(
                        loadBitmapFromAssets(assetManager, "${assetImagesFolder}/${file}"),
                        file
                    )
                )
            }
        }
        return bitmapList
    }


    private fun loadBitmapFromAssets(assetManager: AssetManager, nameOfAsset: String): Bitmap {
        return BitmapFactory.decodeStream(assetManager.open(nameOfAsset))
    }


}

data class BitmapBag(val bitmap: Bitmap, val fileName: String)