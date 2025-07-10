package com.example.proyectofinal.ui.acitivities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinal.R
import com.example.proyectofinal.databinding.ActivityViewUserAcitivityBinding
import com.example.proyectofinal.models.Category
import com.example.proyectofinal.models.CategoryList
import com.example.proyectofinal.ui.adapters.ItemCategoryAdapter
import com.example.proyectofinal.ui.fragments.WorkersFragment
import com.example.proyectofinal.ui.viewModels.CategoryViewModel

class ViewUserAcitivity : AppCompatActivity(), ItemCategoryAdapter.OnCategoryClick {
    private lateinit var binding: ActivityViewUserAcitivityBinding
    private val categoryViewModel: CategoryViewModel by viewModels()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityViewUserAcitivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupEventListeners()
        setupRecyclerView()
        setupEventViewModelObservers()
        categoryViewModel.loadCategories()
    }

    private fun setupEventListeners() {
        binding.btnSearchCategory.setOnClickListener {
            val searchQuery = binding.txtSearchCategory.editText?.text.toString()
            if (searchQuery.isNotEmpty()) {
                val filterCategory = categoryViewModel.searchCategories(searchQuery)
                if (filterCategory.isNotEmpty()) {
                    val adapter = binding.rvCategories.adapter as ItemCategoryAdapter
                    adapter.setData(filterCategory)
                }
            } else {
                val adapter = binding.rvCategories.adapter as ItemCategoryAdapter
                adapter.setData(categoryViewModel.categoryList.value ?: CategoryList( emptyList()))
            }
        }
    }

    private fun setupRecyclerView() {
        val adapter = ItemCategoryAdapter(arrayListOf(), this)
        binding.rvCategories.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(this@ViewUserAcitivity).apply {
                orientation = RecyclerView.VERTICAL
            }
            addItemDecoration(
                DividerItemDecoration(
                    this@ViewUserAcitivity,
                    LinearLayoutManager.VERTICAL
                )
            )
        }
    }

    private fun setupEventViewModelObservers() {
        categoryViewModel.categoryList.observe(this) { categories ->
            if (categories == null) {
                return@observe
            }
            val adapter = binding.rvCategories.adapter as ItemCategoryAdapter
            adapter.setData(categories)
        }
    }

    override fun onCategoryClick(category: Category) {
        val intent = ViewWorkersActivity.newIntent(this, category.id)
        startActivity(intent)
    }

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, ViewUserAcitivity::class.java)
        }
    }
}