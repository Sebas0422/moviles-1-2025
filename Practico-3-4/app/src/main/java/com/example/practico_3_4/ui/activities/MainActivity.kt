package com.example.practico_3_4.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.practico_3_4.ui.components.TetrisGame
import com.example.practico_3_4.ui.components.TetrisView
import com.example.practico_3_4.ui.viewmodels.GamerViewModel
import com.example.practico_3_4.ui.viewmodels.MainActivityViewModel
import com.example.pruebatetris.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var tetrisView: TetrisView
    private val gameViewModel: GamerViewModel by viewModels()
    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var game = TetrisGame()
        game.setViewModel(gameViewModel)

        setupViewModelObservers()

        tetrisView = binding.tetrisView
        tetrisView.setGame(game)

        setupEventListeners()

        tetrisView.startGameLoop()
        tetrisView.setViewModel(viewModel)
    }

    private fun setupEventListeners() {
        binding.btnDown.setOnClickListener {
            if (!tetrisView.getGame().gameOver) {
                val game = tetrisView.getGame()
                while (game.moveDown()) {
                    // Repite hasta que no pueda bajar más
                }
                tetrisView.invalidate()
            }
        }
        binding.btnRotate.setOnClickListener {
            if (!TetrisGame().gameOver) {
                tetrisView.getGame().rotatePiece()
            }
        }
    }

    private fun setupViewModelObservers() {
        gameViewModel.score.observe(this) { score ->
            binding.lblScore.text = score.toString()
            if(score/ 10 > tetrisView.getGame().level){
                tetrisView.aumentLevel()
            }
        }
    }

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        tetrisView.stopGameLoop()
    }
}