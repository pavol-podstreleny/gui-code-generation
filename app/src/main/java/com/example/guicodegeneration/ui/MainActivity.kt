package com.example.guicodegeneration.ui

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.guicodegeneration.R
import com.example.guicodegeneration.adapters.OnTestImageClicked
import com.example.guicodegeneration.adapters.TestImageAdapter
import com.example.guicodegeneration.ui.code_generation.CodeGenerationActivity
import com.example.guicodegeneration.utils.hide
import com.example.guicodegeneration.utils.show
import com.example.guicodegeneration.viewmodel.NeuralNetworkViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(), OnTestImageClicked {

    private val viewModel: NeuralNetworkViewModel by inject()
    private lateinit var viewAdapter: TestImageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        viewAdapter = TestImageAdapter(this)

        test_images_rv.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        //Load test image from assets
        viewModel.loadTestImageBitmaps(assets)

        // Handle loaded images from assets
        viewModel.testImageBitmaps.observe(this, Observer {
            viewAdapter.setTestImages(it)
        })

        val tokenKey = "tokens"
        val bitmapKey = "bitmap"

        // Start another activity when model predicts tokens
        viewModel.predictedTokens.observe(this, Observer {
            contentDisplay(display = true)
            val intent = Intent(this@MainActivity, CodeGenerationActivity::class.java)
            intent.putExtra(tokenKey, it)
            intent.putExtra(bitmapKey, viewModel.bitmapUri)
            startActivity(intent)
        })

    }

    override fun onClick(bitmap: Bitmap, fileName: String) {
        contentDisplay(display = false)
        viewModel.predictTokens(bitmap, Uri.parse(fileName))
    }

    private fun contentDisplay(display: Boolean = true) {
        when (display) {
            true -> {
                test_images_rv.show()
                test_image_tv.show()
                load_custom_bt.show()
                custom_image_tv.show()

                // hide loading
                loading_pb.hide()
                generating_layout_tv.hide()
            }
            false -> {
                test_images_rv.hide()
                test_image_tv.hide()
                load_custom_bt.hide()
                custom_image_tv.hide()

                // show loading
                loading_pb.show()
                generating_layout_tv.show()
            }
        }
    }

}
