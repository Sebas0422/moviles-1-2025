package com.example.proyectofinal.ui.acitivities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import com.example.proyectofinal.R
import com.example.proyectofinal.databinding.ActivityViewWorkersBinding
import com.example.proyectofinal.ui.fragments.WorkersFragment

class ViewWorkersActivity : AppCompatActivity() {
    private lateinit var binding: ActivityViewWorkersBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityViewWorkersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        loadWorkers()
    }

    private fun loadWorkers() {
        val categoryId = intent.getIntExtra(CATEGORY_ID_PARAM, 0)
        if (categoryId == 0)
            return
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fgmWorker) as NavHostFragment
        val navController = navHostFragment.navController

        val bundle = Bundle().apply {
            putInt("categoryId", categoryId)
        }
        navController.setGraph(R.navigation.nav_graph, bundle)

    }

    companion object{
        private const val CATEGORY_ID_PARAM = "categoryId"
        fun newIntent(context: Context, id: Int): Intent {
            return Intent(context, ViewWorkersActivity::class.java).apply {
                putExtra(CATEGORY_ID_PARAM, id)
            }
        }
    }
}