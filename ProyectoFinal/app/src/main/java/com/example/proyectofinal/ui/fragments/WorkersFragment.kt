package com.example.proyectofinal.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinal.databinding.FragmentWorkersBinding
import com.example.proyectofinal.models.Worker
import com.example.proyectofinal.ui.adapters.ItemWorkerAdapter
import com.example.proyectofinal.ui.viewModels.CategoryViewModel
import kotlin.getValue

class WorkersFragment : Fragment(), ItemWorkerAdapter.OnWorkerClick {

    private lateinit var binding: FragmentWorkersBinding
    private val categoryViewModel: CategoryViewModel by viewModels()
    private var categoryId: Int = 0
    private val args: WorkersFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        categoryId = args.categoryId
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWorkersBinding.inflate(inflater, container, false)
        setupEventListeners()
        setupRecyclerView()
        setupViewModelObservers()
        categoryViewModel.loadWorkersByCategory(categoryId)
        return binding.root
    }

    private fun setupViewModelObservers() {
        categoryViewModel.workerList.observe(viewLifecycleOwner) { workerList ->
            val adapter = binding.rvWorker.adapter as ItemWorkerAdapter
            adapter.setData(workerList)
        }
    }

    private fun setupRecyclerView() {
        val adapter = ItemWorkerAdapter(arrayListOf(), this)
        binding.rvWorker.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(context).apply {
                orientation = RecyclerView.VERTICAL
            }
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    LinearLayoutManager.VERTICAL
                )
            )
        }
    }

    private fun setupEventListeners() {
        binding.btnSearchWorker.setOnClickListener {
            val searchQuery = binding.txtSearchWorker.editText?.text.toString()
            categoryViewModel.searchWorker(searchQuery)
        }
    }
    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                requireActivity().onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onWorkerClick(worker: Worker) {
        val action = WorkersFragmentDirections.actionWorkersFragmentToWorkerDetailFragment(
            worker.user_id
        )
        findNavController().navigate(action)
    }
}