package com.example.guicodegeneration.utils

import android.content.Context
import android.content.res.AssetManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.annotation.WorkerThread
import java.io.File
import java.io.FileOutputStream

object BitmapUtils {

    private const val assetImagesFolder = "testimages"


    @WorkerThread
    fun rescaleBitmap(width: Int = 224, height: Int = 224, bitmap: Bitmap): Bitmap {
        return Bitmap.createScaledBitmap(bitmap, width, height, true)
    }

    fun loadBitmapsFromAssets(assetManager: AssetManager): ArrayList<BitmapBag> {
        val bitmapList = arrayListOf<BitmapBag>()
        val rootAssetDir = assetManager.list(assetImagesFolder)
        if (rootAssetDir != null) {
            for (file in rootAssetDir) {
                bitmapList.add(
                    BitmapBag(
                        loadBitmapFromAssets(assetManager, "${assetImagesFolder}/${file}"),
                        "${assetImagesFolder}/${file}"
                    )
                )
            }
        }
        return bitmapList
    }


    fun loadBitmapFromAssets(assetManager: AssetManager, nameOfAsset: String): Bitmap {
        return BitmapFactory.decodeStream(assetManager.open(nameOfAsset))
    }


    fun assetFilePath(
        context: Context,
        assetName: String
    ): String {
        val file = File(context.filesDir, assetName)
        if (file.exists() && file.length() > 0) {
            return file.absolutePath
        }
        context.assets.open(assetName).use { `is` ->
            FileOutputStream(file).use { os ->
                val buffer = ByteArray(4 * 1024)
                var read: Int
                while (`is`.read(buffer).also { read = it } != -1) {
                    os.write(buffer, 0, read)
                }
                os.flush()
            }
            return file.absolutePath
        }
    }


}

data class BitmapBag(val bitmap: Bitmap, val fileName: String)