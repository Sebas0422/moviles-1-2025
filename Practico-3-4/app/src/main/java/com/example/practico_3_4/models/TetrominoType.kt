package com.example.practico_3_4.models

import android.graphics.Color

enum class TetrominoType(val shapes: Array<Array<IntArray>>, val color: Int) {
    I(arrayOf(
        arrayOf(intArrayOf(1, 1, 1, 1)),
        arrayOf(intArrayOf(1), intArrayOf(1), intArrayOf(1), intArrayOf(1))
    ), Color.CYAN),

    O(arrayOf(
        arrayOf(intArrayOf(1, 1), intArrayOf(1, 1))
    ), Color.YELLOW),

    T(arrayOf(
        arrayOf(intArrayOf(0, 1, 0), intArrayOf(1, 1, 1)),
        arrayOf(intArrayOf(1, 0), intArrayOf(1, 1), intArrayOf(1, 0)),
        arrayOf(intArrayOf(1, 1, 1), intArrayOf(0, 1, 0)),
        arrayOf(intArrayOf(0, 1), intArrayOf(1, 1), intArrayOf(0, 1))
    ), Color.MAGENTA),

    S(arrayOf(
        arrayOf(intArrayOf(0, 1, 1), intArrayOf(1, 1, 0)),
        arrayOf(intArrayOf(1, 0), intArrayOf(1, 1), intArrayOf(0, 1))
    ), Color.GREEN),

    Z(arrayOf(
        arrayOf(intArrayOf(1, 1, 0), intArrayOf(0, 1, 1)),
        arrayOf(intArrayOf(0, 1), intArrayOf(1, 1), intArrayOf(1, 0))
    ), Color.RED),

    J(arrayOf(
        arrayOf(intArrayOf(1, 0, 0), intArrayOf(1, 1, 1)),
        arrayOf(intArrayOf(1, 1), intArrayOf(1, 0), intArrayOf(1, 0)),
        arrayOf(intArrayOf(1, 1, 1), intArrayOf(0, 0, 1)),
        arrayOf(intArrayOf(0, 1), intArrayOf(0, 1), intArrayOf(1, 1))
    ), Color.BLUE),

    L(arrayOf(
        arrayOf(intArrayOf(0, 0, 1), intArrayOf(1, 1, 1)),
        arrayOf(intArrayOf(1, 0), intArrayOf(1, 0), intArrayOf(1, 1)),
        arrayOf(intArrayOf(1, 1, 1), intArrayOf(1, 0, 0)),
        arrayOf(intArrayOf(1, 1), intArrayOf(0, 1), intArrayOf(0, 1))
    ), Color.rgb(255, 165, 0))
}