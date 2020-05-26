package com.example.guicodegeneration.ui.code_generation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.guicodegeneration.R
import com.example.guicodegeneration.viewmodel.CodeGenerationViewModel
import org.koin.android.ext.android.inject

class GeneratedLayoutFragment : Fragment() {

    private val viewModel: CodeGenerationViewModel by inject()
    private val tokensKey = "tokens"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val data: LongArray? = arguments?.getLongArray(tokensKey)
        if (data != null) {
            val view = viewModel.generateTokens(data)
            if (view != null) {
                val toolbar = generateToolbar()
                (view as ViewGroup).addView(toolbar, 0)
                return view
            }
        }
        return inflater.inflate(R.layout.fragment_empty_layout, container, false)
    }

    private fun generateToolbar(): View {
        //Create a toolbar
        val toolbar = Toolbar(context)
        toolbar.setTitle(R.string.fragment_generated_layout_title)
        toolbar.visibility = View.VISIBLE
        toolbar.setTitleTextColor(resources.getColor(android.R.color.white))
        toolbar.setBackgroundColor(resources.getColor(R.color.colorPrimary))
        return toolbar
    }


}