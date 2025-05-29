package com.example.practico_3_4.models

import android.graphics.Point

data class Piece(
    val type: GameType,
    var position: Point = Point(3, 0),
    var rotation: Int = 0
) {
    fun shape(): Array<IntArray> {
        return type.shapes[rotation % type.shapes.size]
    }

    fun rotate() {
        rotation = (rotation + 1) % type.shapes.size
    }

    fun rotateBack() {
        rotation = if (rotation == 0) type.shapes.size - 1 else rotation - 1
    }
}