package com.example.guicodegeneration.ui

import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.guicodegeneration.R
import com.example.guicodegeneration.adapters.OnTestImageClicked
import com.example.guicodegeneration.adapters.TestImageAdapter
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

        viewModel.predictedTokens.observe(this, Observer {
            for (value in it) {
                Toast.makeText(this.application, value.toString(), Toast.LENGTH_LONG).show()
            }
        })

    }

    override fun onClick(bitmap: Bitmap, fileName: String) {
        viewModel.predictTokens(bitmap, Uri.parse(fileName))
    }
}
