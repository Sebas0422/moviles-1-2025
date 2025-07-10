package com.example.proyectofinal.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinal.databinding.ItemWorkerLayoutBinding
import com.example.proyectofinal.models.Worker
import com.example.proyectofinal.models.WorkerList
import com.bumptech.glide.Glide

class ItemWorkerAdapter(
    private var workerList: WorkerList,
    private val listener: OnWorkerClick
) : RecyclerView.Adapter<ItemWorkerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = ItemWorkerLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val worker = workerList[position]
        holder.bind(worker, listener)
    }

    override fun getItemCount(): Int {
        return workerList.size
    }

    fun setData(workers: WorkerList) {
        workerList = workers
        notifyDataSetChanged()
    }

    class ViewHolder( private val binding: ItemWorkerLayoutBinding ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(worker: Worker, listener: OnWorkerClick) {
            Glide.with(binding.imageView.context)
                .load(worker.picture_url)
                .into(binding.imageView)
            binding.lblWorkerFullName.text = worker.user.fullName
            binding.lblInfo.text = worker.profileScore
            itemView.setOnClickListener {
                listener.onWorkerClick(worker)
            }
        }
    }

    interface OnWorkerClick {
        fun onWorkerClick(worker: Worker)
    }
}