package com.example.proyectofinal.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinal.databinding.ItemReviewsLayoutBinding
import com.example.proyectofinal.models.Review
import com.example.proyectofinal.models.ReviewList

class ItemReviewAdapter(
    private var reviewList: ReviewList
) : RecyclerView.Adapter<ItemReviewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemReviewsLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val review = reviewList[position]
        holder.bind(review)
    }

    override fun getItemCount(): Int {
        return reviewList.size
    }

    fun updateData(reviews: ReviewList) {
        reviewList = reviews
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemReviewsLayoutBinding) :
        RecyclerView.ViewHolder(binding.root ) {
        fun bind(review: Review) {
            binding.lblReviewUser.text = "${review.user.name}: ${review.comment}"
        }
    }
}