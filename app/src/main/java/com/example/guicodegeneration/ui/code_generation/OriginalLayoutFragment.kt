package com.example.guicodegeneration.ui.code_generation

import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.guicodegeneration.R
import com.example.guicodegeneration.utils.BitmapUtils

class OriginalLayoutFragment : Fragment() {

    private val bitmapKey = "bitmap"
    private val fileSubstring = "file"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_original_layout, container, false)

        val bitmap = arguments?.getParcelable<Uri>(bitmapKey)
        if (bitmap != null) {
            if (!bitmap.toString().contains(fileSubstring)) {
                //Load bitmap
                val bitmapValue =
                    BitmapUtils.loadBitmapFromAssets(context!!.assets, bitmap.toString())
                view.findViewById<ImageView>(R.id.imageView).setImageBitmap(bitmapValue)
            } else {
                view.findViewById<ImageView>(R.id.imageView).setImageBitmap(
                    MediaStore.Images.Media.getBitmap(
                        activity?.contentResolver,
                        bitmap
                    )

                )
            }
        }
        return view
    }
}