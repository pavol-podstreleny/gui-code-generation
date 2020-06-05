package com.example.guicodegeneration.compiler

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.annotation.UiThread
import com.example.guicodegeneration.utils.TokenUtils

/***
 * Compiler builds tree hierarchy of Views / ViewGroups
 */
class Compiler(private val context: Context) {

    private val tokenBody = "body"
    private val tokenStack = "stack"
    private val tokenOpenBrace = "{"
    private val tokenCloseBrace = "}"

    @UiThread
    fun compile(tokens: Array<String>): View? {
        var tokenView = TokenUtils.getViewFromToken(context, tokenBody)
        if (isNull(tokenView)) {
            return null
        }

        var node = LayoutNode(
            tag = tokenView!!,
            token = tokenBody
        )

        for (token in tokens) {
            if (token != tokenOpenBrace && token != tokenCloseBrace) {
                tokenView = TokenUtils.getViewFromToken(context, token)
                if (isNull(tokenView)) {
                    return null
                }
                node.addChildren(
                    LayoutNode(
                        tag = tokenView!!,
                        token = token
                    )
                )
            } else if (token == tokenOpenBrace) {
                node = node.childrenNodes[node.childrenNodes.size - 1]
            } else if (token == tokenCloseBrace) {
                node = node.parentNode
            }
        }

        //Everything compiled correctly -> return view
        return generateLayout(node)

    }


    @UiThread
    private fun isNull(view: View?): Boolean {
        return view == null
    }

    @UiThread
    private fun generateLayout(topNode: LayoutNode): View {

        val actualView = topNode.tag as ViewGroup

        for (topLevelNode in topNode.childrenNodes) {
            if (!isParentStackAdd(
                    parentToken = topNode.token,
                    viewGroup = actualView,
                    actualNode = topLevelNode
                )
            ) {
                actualView.addView(topLevelNode.tag)
            }

            for (secondLevelNode in topLevelNode.childrenNodes) {
                if (!isParentStackAdd(
                        parentToken = topLevelNode.token,
                        viewGroup = (topLevelNode.tag as ViewGroup),
                        actualNode = secondLevelNode
                    )
                ) {
                    (topLevelNode.tag).addView(secondLevelNode.tag)
                }

                for (thirdLevelNode in secondLevelNode.childrenNodes) {
                    if (!isParentStackAdd(
                            parentToken = secondLevelNode.token,
                            viewGroup = (secondLevelNode.tag as ViewGroup),
                            actualNode = thirdLevelNode
                        )
                    ) {
                        (secondLevelNode.tag).addView(thirdLevelNode.tag)
                    }
                }
            }
        }

        return actualView

    }

    @UiThread
    private fun isParentStackAdd(
        parentToken: String,
        viewGroup: ViewGroup,
        actualNode: LayoutNode
    ): Boolean {
        if (parentToken == tokenStack) {
            (viewGroup.getChildAt(0) as ViewGroup).addView(
                actualNode.tag
            )
            return true
        }
        return false

    }


}