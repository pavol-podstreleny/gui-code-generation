package com.example.guicodegeneration.utils

object TokenUtils {

    private val tokens = HashMap<String, Int>()
    private val reversedTokens = HashMap<Int, String>()

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


}

