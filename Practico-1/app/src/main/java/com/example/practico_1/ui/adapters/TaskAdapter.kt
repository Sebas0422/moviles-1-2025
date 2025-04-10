package com.example.practico_1.ui.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practico_1.databinding.TaskItemLayoutBinding
import com.example.practico_1.models.Task

class TaskAdapter(
    private var listTask: ArrayList<Task>,
    private val listener: OnNotaClickListener
) : RecyclerView.Adapter<TaskAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = TaskItemLayoutBinding.inflate(
            inflater,
            parent,
            false
        )
        return ViewHolder(binding, listener)
    }

    override fun getItemCount(): Int {
        return listTask.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listTask[position]
        holder.bind(item)
    }

    fun itemAdded(task: Task) {
        listTask.add(1, task)
        notifyItemInserted(1)
    }

    fun itemUpdated(task: Task) {
        val index = getIndex(task)
        listTask[index] = task
        notifyItemChanged(index)
    }

    fun itemDelete(task: Task) {
        val index = getIndex(task)
        listTask.removeAt(index)
        notifyItemRemoved(index)
    }

    private fun getIndex(task: Task): Int {
        val foundData = listTask.first { it.id == task.id }
        return listTask.indexOf(foundData)
    }

    fun setData(people: java.util.ArrayList<Task>) {
        this.listTask = people
        notifyDataSetChanged()
    }

    fun notifyChange(index: Int) {
        notifyItemChanged(index)
    }

    class ViewHolder(
        private val binding: TaskItemLayoutBinding,
        private val listener: OnNotaClickListener
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Task) {
            binding.lblTitle.text = item.title
            binding.txtContent.setText(item.content)
            binding.checkBox.isChecked = item.isChecked
            if (item.isChecked) {
                binding.lblTitle.paintFlags =
                    binding.lblTitle.paintFlags or android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
                binding.txtContent.paintFlags =
                    binding.txtContent.paintFlags or android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
            } else {
                binding.lblTitle.paintFlags =
                    binding.lblTitle.paintFlags and android.graphics.Paint.STRIKE_THRU_TEXT_FLAG.inv()
                binding.txtContent.paintFlags =
                    binding.txtContent.paintFlags and android.graphics.Paint.STRIKE_THRU_TEXT_FLAG.inv()
            }
            binding.root.setBackgroundColor(item.color)
            setupEventListeners(item, listener)
        }

        private fun setupEventListeners(item: Task, listener: OnNotaClickListener) {
            binding.btnRed.setOnClickListener {
                listener.onNotaColorClickListener(
                    item,
                    Color.parseColor("#F44336")
                )
            }
            binding.btnOrange.setOnClickListener {
                listener.onNotaColorClickListener(
                    item,
                    Color.parseColor("#FF9800")
                )
            }
            binding.btnPurple.setOnClickListener {
                listener.onNotaColorClickListener(
                    item,
                    Color.parseColor("#9C27B0")
                )
            }
            binding.btnLightBlue.setOnClickListener {
                listener.onNotaColorClickListener(
                    item, Color.parseColor(
                        "#40C4FF"
                    )
                )
            }
            binding.btnBlue.setOnClickListener {
                listener.onNotaColorClickListener(
                    item,
                    Color.parseColor("#536DFE")
                )
            }
            binding.btnPink.setOnClickListener {
                listener.onNotaColorClickListener(
                    item,
                    Color.parseColor("#FF69DF")
                )
            }
            binding.btnCyan.setOnClickListener {
                listener.onNotaColorClickListener(
                    item,
                    Color.parseColor("#64FFDA")
                )
            }
            binding.btnLime.setOnClickListener {
                listener.onNotaColorClickListener(
                    item,
                    Color.parseColor("#B2FF59")
                )
            }
            binding.btnDeepPurple.setOnClickListener {
                listener.onNotaColorClickListener(
                    item, Color.parseColor(
                        "#E040FB"
                    )
                )
            }
            binding.btnGreen.setOnClickListener {
                listener.onNotaColorClickListener(
                    item,
                    Color.parseColor("#00BFA5")
                )
            }
            binding.btnDelete.setOnClickListener { listener.onNotaDeleteClickListener(item) }
            binding.btnEdit.setOnClickListener { listener.onNotaEditClickListener(item) }
            binding.checkBox.setOnClickListener {
                item.isChecked = binding.checkBox.isChecked
                if (item.isChecked) {
                    binding.lblTitle.paintFlags =
                        binding.lblTitle.paintFlags or android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
                    binding.txtContent.paintFlags =
                        binding.txtContent.paintFlags or android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
                } else {
                    binding.lblTitle.paintFlags =
                        binding.lblTitle.paintFlags and android.graphics.Paint.STRIKE_THRU_TEXT_FLAG.inv()
                    binding.txtContent.paintFlags =
                        binding.txtContent.paintFlags and android.graphics.Paint.STRIKE_THRU_TEXT_FLAG.inv()
                }
            }
        }

    }

    public interface OnNotaClickListener {
        fun onNotaEditClickListener(task: Task)
        fun onNotaDeleteClickListener(task: Task)
        fun onNotaColorClickListener(item: Task, parseColor: Int)
    }
}