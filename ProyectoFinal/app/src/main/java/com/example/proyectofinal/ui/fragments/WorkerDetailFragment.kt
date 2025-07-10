package com.example.proyectofinal.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proyectofinal.databinding.FragmentWorkerDetailBinding
import com.example.proyectofinal.ui.adapters.ItemReviewAdapter
import com.example.proyectofinal.ui.viewModels.WorkerViewModel
import kotlin.getValue

class WorkerDetailFragment : Fragment() {
    private lateinit var binding: FragmentWorkerDetailBinding
    private val args: WorkerDetailFragmentArgs by navArgs()
    private val workerViewModel: WorkerViewModel by viewModels()

    private var workerId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        workerId = args.workerIdParam
    }

    private fun setupRecycleView() {
        val adapter = ItemReviewAdapter(arrayListOf())
        binding.rvReviews.apply {
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

    private fun setupViewModelObservers() {
        workerViewModel.worker.observe(viewLifecycleOwner) { worker ->
            Glide.with(binding.imageView2.context)
                .load(worker.picture_url)
                .into(binding.imageView2)
            binding.lblScoreWorkerDetails.text =
                if (worker.average_rating == 0) "Sin calificación" else "${worker.average_rating}% calification"
            binding.lblWorkerCountDetail.text =
                if (worker.reviews_count == 0) "Sin trabajadores" else "${worker.reviews_count} trabajadores"
            for (category in worker.categories) {
                binding.lblCategoriesWorkerDetail.text =
                    "${binding.lblCategoriesWorkerDetail.text} ${category.name}, "
            }
            val adapter = binding.rvReviews.adapter as ItemReviewAdapter
            adapter.updateData(worker.reviews)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWorkerDetailBinding.inflate(inflater, container, false)
        //setupEventListeners()
        setupRecycleView()
        setupViewModelObservers()
        workerViewModel.loadWorker(workerId)
        return binding.root
    }

}