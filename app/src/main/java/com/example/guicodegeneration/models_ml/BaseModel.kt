package com.example.guicodegeneration.models_ml

import android.content.Context
import androidx.annotation.WorkerThread
import com.example.guicodegeneration.utils.BitmapUtils
import org.jetbrains.annotations.NotNull
import org.pytorch.Module
import org.pytorch.Tensor
import java.io.File

abstract class BaseModel<INPUT>(context: Context, val modelName: String) {

    private val modelFilePath =
        File(BitmapUtils.assetFilePath(context, modelName)).absolutePath
    protected val module: Module = Module.load(modelFilePath);

    @WorkerThread
    @NotNull
    abstract fun predict(input: INPUT): Tensor
}