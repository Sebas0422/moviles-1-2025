package com.example.practico_3_4.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practico_3_4.db.models.Score
import com.example.practico_3_4.ui.Adapters.ScoreAdpater
import com.example.practico_3_4.ui.viewmodels.MainActivityViewModel
import com.example.pruebatetris.R
import com.example.pruebatetris.databinding.ActivityScoreListBinding

class ScoreListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScoreListBinding
    private val viewModel: MainActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityScoreListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupReciclerView()
        setupViewModelObservers()
        viewModel.loadScores(this)
    }

    private fun setupViewModelObservers() {
        viewModel.scoreList.observe(this) {
            val adapter = binding.rvScores.adapter as ScoreAdpater
            adapter.setData(it)
        }
    }

    private fun setupReciclerView() {
        val adapter = ScoreAdpater(arrayListOf())
        binding.rvScores.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(this@ScoreListActivity).apply {
                orientation = RecyclerView.VERTICAL
            }
            addItemDecoration(
                DividerItemDecoration(
                    this@ScoreListActivity,
                    LinearLayoutManager.VERTICAL
                )
            )
        }
    }

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, ScoreListActivity::class.java)
        }
    }
}