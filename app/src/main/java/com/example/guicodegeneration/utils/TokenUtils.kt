package com.example.guicodegeneration.utils

import android.content.Context
import android.view.ContextThemeWrapper
import android.view.View
import android.widget.*
import com.example.guicodegeneration.R

object TokenUtils {

    private val tokens = HashMap<String, Int>()
    private val reversedTokens = HashMap<Int, String>()
    private const val PADDING_DP = 10

    init {
        tokens["{"] = 1
        tokens["}"] = 2
        tokens["row"] = 3
        tokens["label"] = 4
        tokens["small-title"] = 5
        tokens["text"] = 6
        tokens["quadruple"] = 7
        tokens["switch"] = 8
        tokens["btn-inactive"] = 9
        tokens["img"] = 10
        tokens["btn-green"] = 11
        tokens["btn-red"] = 12
        tokens["btn"] = 13
        tokens["btn-orange"] = 14
        tokens["stack"] = 15
        tokens["footer"] = 16
        tokens["slider"] = 17
        tokens["double"] = 18
        tokens["btn-search"] = 19
        tokens["btn-add"] = 20
        tokens["header"] = 21
        tokens["btn-active"] = 22
        tokens["single"] = 23
        tokens["radio"] = 24
        tokens["btn-home"] = 25
        tokens["btn-dashboard"] = 26
        tokens["check"] = 27
        tokens["btn-download"] = 28
        tokens["btn-contact"] = 29
        tokens["btn-more"] = 30
        tokens["btn-notifications"] = 31
        tokens["<pad>"] = 0
        tokens["<start>"] = 32
        tokens["<end>"] = 33

        for ((key, value) in tokens.entries) {
            reversedTokens[value] = key
        }
    }


    fun getEndTokenId(): Long {
        return tokens["<end>"]!!.toLong()
    }

    fun getTokenString(id: Long): String {
        return reversedTokens[id.toInt()]!!
    }

    fun getViewFromToken(context: Context, token: String): View? {
        return when (token) {
            "body" -> {
                provideLinearLayout(
                    ContextThemeWrapper(context, R.style.AppTheme),
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.VERTICAL
                )
            }


            "stack" -> {
                provideFrameLayout(context)
            }


            "row" -> {
                val linearLayout = provideLinearLayout(
                    context,
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.HORIZONTAL
                )
                val paddingDp = PADDING_DP
                val density = context.resources.displayMetrics.density
                val paddingPixel = (paddingDp * density).toInt()
                linearLayout.setPadding(0, paddingPixel, 0, paddingPixel)
                linearLayout.weightSum = 1f
                linearLayout
            }

            "label" -> {
                provideTextView(context)
            }

            "btn" -> {
                provideButton(context, R.string.token_button, 0)
            }

            "slider" -> {
                val newcontext = ContextThemeWrapper(context, R.style.AppTheme)
                val seekBar = SeekBar(newcontext)
                seekBar.apply {
                    id = View.generateViewId()
                    layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        1f
                    )

                    max = 10
                    progress = 5
                }
                seekBar
            }

            "check" -> {
                val newcontext = ContextThemeWrapper(context, R.style.AppTheme)
                val checkBox = CheckBox(newcontext)
                checkBox.id = View.generateViewId()
                checkBox.layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                val paddingDp = PADDING_DP
                val density = context.resources.displayMetrics.density
                val paddingPixel = (paddingDp * density).toInt()
                checkBox.setPadding(0, 0, paddingPixel, 0)
                checkBox.setText(R.string.token_check)
                checkBox
            }

            "radio" -> {
                val newcontext = ContextThemeWrapper(context, R.style.AppTheme)
                val radioButton = RadioButton(newcontext)
                radioButton.id = View.generateViewId()
                radioButton.layoutParams = FrameLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                val paddingDp = PADDING_DP
                val density = context.resources.displayMetrics.density
                val paddingPixel = (paddingDp * density).toInt()
                radioButton.setTextAppearance(context, R.style.TextToken)
                radioButton.setPadding(0, 0, paddingPixel, 0)
                radioButton.setText(R.string.token_radio)
                radioButton
            }

            "switch" -> {
                val newcontext = ContextThemeWrapper(context, R.style.AppTheme)
                val switcher = Switch(newcontext)
                switcher.id = View.generateViewId()
                switcher.layoutParams = FrameLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                val paddingDp = 10
                val density = context.resources.displayMetrics.density
                val paddingPixel = (paddingDp * density).toInt()
                switcher.setTextAppearance(context, R.style.TextToken)
                switcher.setPadding(0, 0, paddingPixel, 0)
                switcher.setText(R.string.token_switcher)
                switcher
            }

            "footer" -> {
                val footer = provideLinearLayout(
                    context,
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.HORIZONTAL
                )
                footer.weightSum = 1f
                footer
            }

            "btn-home" -> {
                provideDrawableButton(context, R.drawable.ic_home, 1)
            }

            "btn-dashboard" -> {
                provideDrawableButton(context, R.drawable.ic_dashboard, 1)
            }

            "btn-notifications" -> {
                provideDrawableButton(
                    context,
                    R.drawable.ic_notifications,
                    1
                )
            }

            "btn-search" -> {
                provideDrawableButton(
                    context, R.drawable.ic_search, 1
                )
            }


            else -> return null
        }

    }

    private fun provideDrawableButton(
        context: Context,
        drawable: Int,
        layout_weight: Int
    ): Button? {
        val b = provideButton(context, R.string.token_empty, layout_weight)
        b.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, 0, drawable)
        b.setBackgroundColor(context.resources.getColor(android.R.color.white))
        b.setTextAppearance(context, R.style.TextToken)
        return b
    }

    private fun provideButton(
        context: Context,
        textResource: Int,
        layout_weight: Int
    ): Button {
        val button = Button(context)
        button.id = View.generateViewId()
        button.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT,
            layout_weight.toFloat()
        )
        button.setText(textResource)
        button.setTextAppearance(context, R.style.TextToken)
        return button
    }

    private fun provideTextView(context: Context): TextView? {
        //Does not have text appearance
        val textView = TextView(context)
        textView.id = View.generateViewId()
        textView.setText(R.string.token_label)
        textView.layoutParams = FrameLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        textView.setTextAppearance(context, R.style.TextToken)
        return textView
    }

    private fun provideLinearLayout(
        context: Context,
        width: Int,
        height: Int,
        orientation: Int
    ): LinearLayout {
        val linearParams = LinearLayout.LayoutParams(width, height)
        val linearLayout = LinearLayout(context)
        linearLayout.id = View.generateViewId()
        linearLayout.orientation = orientation
        linearLayout.layoutParams = linearParams
        return linearLayout
    }

    private fun provideFrameLayout(context: Context): FrameLayout? {
        val frameParams = LinearLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.MATCH_PARENT,
            1f
        )
        val layout = FrameLayout(context)
        layout.id = View.generateViewId()
        layout.layoutParams = frameParams
        val paddingDp = 10
        val density = context.resources.displayMetrics.density
        val paddingPixel = (paddingDp * density).toInt()
        layout.setPadding(paddingPixel, paddingPixel, paddingPixel, paddingPixel)
        val innerLayout = provideLinearLayout(
            context,
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.VERTICAL
        )
        layout.addView(innerLayout)
        return layout
    }


}
