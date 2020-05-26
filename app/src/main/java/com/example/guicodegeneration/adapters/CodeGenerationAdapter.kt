package com.example.guicodegeneration.adapters

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.guicodegeneration.ui.code_generation.GeneratedLayoutFragment
import com.example.guicodegeneration.ui.code_generation.OriginalLayoutFragment

class CodeGenerationAdapter(
    fragmentActivity: FragmentActivity,
    private val longArray: LongArray?,
    private val bitmap: Uri?
) :
    FragmentStateAdapter(fragmentActivity) {

    private val numberOfPages = 2
    private val tokenKey = "tokens"
    private val bitmapUriKey = "bitmap"

    override fun getItemCount(): Int {
        return numberOfPages
    }

    override fun createFragment(position: Int): Fragment {

        return when (position) {
            1 -> {
                val bundle = Bundle()
                bundle.putLongArray(tokenKey, longArray)

                val fragment = GeneratedLayoutFragment()
                fragment.arguments = bundle

                fragment
            }
            0 -> {
                val bundle = Bundle()
                bundle.putParcelable(bitmapUriKey, bitmap)

                val fragment = OriginalLayoutFragment()
                fragment.arguments = bundle
                fragment

            }

            else -> {
                throw IllegalArgumentException("Illegal position argument")
            }
        }
    }
}