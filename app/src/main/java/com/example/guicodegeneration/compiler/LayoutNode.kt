package com.example.guicodegeneration.compiler

import android.view.View

class LayoutNode(
    val tag: View,
    val token: String
) {

    val childrenNodes: ArrayList<LayoutNode> = ArrayList()
    var parentNode: LayoutNode = this


    fun addChildren(node: LayoutNode) {
        node.parentNode = this
        childrenNodes.add(node)
    }

}