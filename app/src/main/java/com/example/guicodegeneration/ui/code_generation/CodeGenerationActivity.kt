package com.example.guicodegeneration.ui.code_generation

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.guicodegeneration.R
import com.example.guicodegeneration.adapters.CodeGenerationAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_code_generation.*

class CodeGenerationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_code_generation)

        val tokensKey = "tokens"
        val bitmapUriKey = "bitmap"

        if (intent != null) {
            val tokens = intent.getLongArrayExtra(tokensKey)
            val bitmapUri = intent.getParcelableExtra<Uri>(bitmapUriKey)
            val adapter = CodeGenerationAdapter(this, tokens, bitmapUri)
            pager.adapter = adapter
        }

        TabLayoutMediator(tabLayout, pager) { tab, position ->
            when (position) {
                1 -> tab.setText(R.string.tab_generated_layout)
                0 -> tab.setText(R.string.tab_original_picture)
            }
        }.attach()
    }

}