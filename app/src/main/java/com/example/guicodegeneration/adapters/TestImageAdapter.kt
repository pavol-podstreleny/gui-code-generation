package com.example.guicodegeneration.adapters

import android.graphics.Bitmap
import android.os.Debug
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.guicodegeneration.R
import com.example.guicodegeneration.utils.BitmapBag

class TestImageAdapter(
    private val listener: OnTestImageClicked,
    private var testImages: ArrayList<BitmapBag>? = arrayListOf()
) :
    RecyclerView.Adapter<TestImageAdapter.ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        Log.d("Rofl", "lol4")
        return ImageViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.test_image_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        Log.d("Rofl", "lol3")
        if (testImages == null) {
            return 0
        }
        return testImages!!.size
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        Log.d("Rofl", "lol2")
        if (testImages != null) {
            val bitmap = testImages?.get(position)
            holder.bind(bitmap)
        }
    }

    fun setTestImages(testImages: ArrayList<BitmapBag>?) {
        this.testImages = testImages
        notifyDataSetChanged()
        Log.d("Rofl", "lol1")
    }

    inner class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val image: ImageView = view.findViewById(R.id.imageTest)
        private val cardView: CardView = view.findViewById(R.id.cardView)

        init {
            cardView.setOnClickListener {
                val bitmapBag = testImages?.get(adapterPosition)
                if (bitmapBag != null) {
                    listener.onClick(bitmapBag.bitmap, bitmapBag.fileName)
                }

            }
        }

        fun bind(bitmap: BitmapBag?) {
            Log.d("Rofl", "lol")
            image.setImageBitmap(bitmap?.bitmap)
        }
    }
}

interface OnTestImageClicked {
    fun onClick(bitmap: Bitmap, fileName: String)
}