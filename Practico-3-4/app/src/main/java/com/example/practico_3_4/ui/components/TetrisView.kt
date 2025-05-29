package com.example.practico_3_4.ui.components

import android.app.AlertDialog
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import com.example.practico_3_4.db.models.Score
import com.example.practico_3_4.models.TetrominoType
import com.example.practico_3_4.ui.activities.ScoreListActivity
import com.example.practico_3_4.ui.viewmodels.MainActivityViewModel
import com.example.pruebatetris.databinding.ScoreSaveDialogBinding
import kotlin.math.abs

class TetrisView : View {

    private lateinit var game: TetrisGame

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    private lateinit var viewModel: MainActivityViewModel

    fun setGame(game: TetrisGame) {
        this.game = game
        invalidate()
    }

    fun getGame(): TetrisGame {
        return game
    }

    private val handler = Handler(Looper.getMainLooper())
    private var dropInterval = 500L

    private val dropRunnable = object : Runnable {
        override fun run() {
            if (!game.gameOver) {
                val movedDown = game.moveDown()
                if (!movedDown) {
                    if (game.gameOver) {
                        stopGameLoop()
                    }
                }
                invalidate()
                handler.postDelayed(this, dropInterval)
            }else{
                showSaveScoreDialog(LayoutInflater.from(context), context)
            }
        }
    }

    fun startGameLoop() {
        handler.post(dropRunnable)
    }

    fun stopGameLoop() {
        handler.removeCallbacks(dropRunnable)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val board = game.getBoardWithPiece()
        val cellWidth = width / game.width
        val cellHeight = height / game.height

        for (y in board.indices) {
            for (x in board[y].indices) {
                val value = board[y][x]
                val paint = Paint().apply {
                    style = Paint.Style.FILL
                    color = if (value == 0) Color.LTGRAY
                    else TetrominoType.entries[abs(value) - 1].color
                }

                val left = x * cellWidth.toFloat()
                val top = y * cellHeight.toFloat()
                canvas.drawRect(left, top, left + cellWidth, top + cellHeight, paint)

                val border = Paint().apply {
                    color = Color.DKGRAY
                    style = Paint.Style.STROKE
                }
                canvas.drawRect(left, top, left + cellWidth, top + cellHeight, border)
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                if(event.x < width / 2) {
                    game.moveLeft()
                } else {
                    game.moveRight()
                }
            }
        }
        return true
    }

    fun aumentLevel() {
        game.resetBoard()
        if(dropInterval > 100L) {
            game.level++
            dropInterval -= 100L
            stopGameLoop()
            startGameLoop()
        } else {
            Toast.makeText(context, "Max Level Reached", Toast.LENGTH_SHORT).show()
        }
    }

    fun setViewModel(viewModel: MainActivityViewModel) {
        this.viewModel = viewModel
    }

    private fun showSaveScoreDialog(layoutInflater: LayoutInflater, context: Context) {
        val dialogBinding = ScoreSaveDialogBinding.inflate(layoutInflater)

        val dialog = AlertDialog.Builder(context)
            .setTitle("Guardar Puntuación")
            .setView(dialogBinding.root)
            .setPositiveButton("Aceptar") { _, _ ->
                val name = dialogBinding.txtNameAdd.editText?.text.toString()
                val score = game.getViewModel()?.score?.value ?: 0
                val newScore = Score(score, name)
                viewModel.saveScore(context, newScore)
                val intent = ScoreListActivity.createIntent(context)
                context.startActivity(intent)
            }
            .setNegativeButton("Cancelar", null)
            .create()

        dialog.show()
    }
}