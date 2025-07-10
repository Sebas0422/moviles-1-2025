package com.example.proyectofinal.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinal.databinding.ItemCategoryLayoutBinding
import com.example.proyectofinal.models.Category
import com.example.proyectofinal.models.CategoryList

class ItemCategoryAdapter(
    var categories: CategoryList,
    private val listener: OnCategoryClick
) : RecyclerView.Adapter<ItemCategoryAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCategoryLayoutBinding.inflate(
            inflater,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val item = categories[position]
        holder.bind(item, listener)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    fun setData(categories: CategoryList) {
        this.categories = categories
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemCategoryLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Category, listener: OnCategoryClick) {
            binding.lblCategoryName.text = item.name
            itemView.setOnClickListener {
                listener.onCategoryClick(item)
            }
        }
    }

    interface OnCategoryClick {
        fun onCategoryClick(category: Category)
    }
}