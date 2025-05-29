package com.example.practico_3_4.ui.components

import com.example.practico_3_4.models.Piece
import com.example.practico_3_4.models.TetrominoType
import com.example.practico_3_4.ui.viewmodels.GamerViewModel

class TetrisGame(val width: Int = 10, val height: Int = 20) {

    var board = Array(height) { IntArray(width) { 0 } }
    var currentPiece: Piece? = null
    var gameOver = false
    private var viewModel: GamerViewModel? = null
    var level = 0

    init { spawnPiece() }

    fun spawnPiece() {
        val piece = Piece(TetrominoType.entries.toTypedArray().random())
        currentPiece = if (canMove(piece, 0, 0)) piece else {
            gameOver = true
            null
        }
    }

    fun canMove(piece: Piece?, dx: Int, dy: Int): Boolean {
        if (piece == null) return false
        val shape = piece.shape()
        for (row in shape.indices) {
            for (col in shape[row].indices) {
                if (shape[row][col] == 1) {
                    val newX = piece.position.x + col + dx
                    val newY = piece.position.y + row + dy
                    if (newX !in 0 until width || newY !in 0 until height || board[newY][newX] != 0) {
                        return false
                    }
                }
            }
        }
        return true
    }

    fun moveLeft() {
        if (canMove(currentPiece, -1, 0)) currentPiece?.position?.x = currentPiece!!.position.x - 1
    }

    fun moveRight() {
        if (canMove(currentPiece, 1, 0)) currentPiece?.position?.x = currentPiece!!.position.x + 1
    }

    fun moveDown(): Boolean {
        return if (canMove(currentPiece, 0, 1)) {
            currentPiece?.position?.y = currentPiece!!.position.y + 1
            true
        } else {
            placePiece(currentPiece)
            clearLines()
            spawnPiece()
            false
        }
    }

    fun rotatePiece() {
        currentPiece?.rotate()
        if (!canMove(currentPiece, 0, 0)) {
            currentPiece?.rotateBack()
        }
    }
    private fun placePiece(piece: Piece?) {
        if (piece == null) return
        val shape = piece.shape()
        val colorId = piece.type.ordinal + 1
        for (row in shape.indices) {
            for (col in shape[row].indices) {
                if (shape[row][col] == 1) {
                    val x = piece.position.x + col
                    val y = piece.position.y + row
                    if (y in 0 until height && x in 0 until width) {
                        board[y][x] = colorId
                    }
                }
            }
        }
    }

    private fun clearLines() {
        val newBoard = board.filter { row -> row.any { it == 0 } }.toMutableList()
        var countRowRemove = 0
        while (newBoard.size < height) {
            newBoard.add(0, IntArray(width) { 0 })
            countRowRemove++
        }
        for (i in 0 until height) board[i] = newBoard[i]
        viewModel?.addScore(countRowRemove * countRowRemove * 10)
    }

    fun getBoardWithPiece(): Array<IntArray> {
        val displayBoard = board.map { it.copyOf() }.toTypedArray()
        currentPiece?.let { piece ->
            val shape = piece.shape()
            val colorId = -(piece.type.ordinal + 1)
            for (row in shape.indices) {
                for (col in shape[row].indices) {
                    if (shape[row][col] == 1) {
                        val x = piece.position.x + col
                        val y = piece.position.y + row
                        if (y in 0 until height && x in 0 until width) {
                            displayBoard[y][x] = colorId
                        }
                    }
                }
            }
        }
        return displayBoard
    }

    fun setViewModel(model: GamerViewModel) {
        this.viewModel = model
    }

    fun resetBoard() {
        board = Array(height) { IntArray(width) { 0 } }
    }
}