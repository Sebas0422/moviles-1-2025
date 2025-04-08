package com.example.practico_1.ui.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practico_1.databinding.NotaItemLayoutBinding
import com.example.practico_1.models.Nota

class NotaAdapter(
    private var listNota: ArrayList<Nota>,
    private val listener: OnNotaClickListener
) : RecyclerView.Adapter<NotaAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = NotaItemLayoutBinding.inflate(
            inflater,
            parent,
            false
        )
        return ViewHolder(binding, listener)
    }

    override fun getItemCount(): Int {
        return listNota.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listNota[position]
        holder.bind(item)
    }

    fun itemAdded(nota: Nota) {
        listNota.add(1, nota)
        notifyItemInserted(1)
    }

    fun itemUpdated(nota: Nota) {
        val index = getIndex(nota)
        listNota[index] = nota
        notifyItemChanged(index)
    }

    fun itemDelete(nota: Nota) {
        val index = getIndex(nota)
        listNota.removeAt(index)
        notifyItemRemoved(index)
    }

    private fun getIndex(nota: Nota): Int {
        val foundData = listNota.first { it.id == nota.id }
        return listNota.indexOf(foundData)
    }

    fun setData(people: java.util.ArrayList<Nota>) {
        this.listNota = people
        notifyDataSetChanged()
    }

    fun notifyChange(index: Int) {
        notifyItemChanged(index)
    }

    class ViewHolder(
        private val binding: NotaItemLayoutBinding,
        private val listener: OnNotaClickListener
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Nota) {
            binding.lblTitle.text = item.title
            binding.txtContent.setText(item.content)
            binding.checkBox.isChecked = item.isChecked
            setupEventListeners(item, listener)
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

        private fun setupEventListeners(item: Nota, listener: OnNotaClickListener) {
            binding.btnRed.setOnClickListener { binding.root.setBackgroundColor(Color.parseColor("#F44336")) }
            binding.btnOrange.setOnClickListener {
                binding.root.setBackgroundColor(
                    Color.parseColor(
                        "#FF9800"
                    )
                )
            }
            binding.btnPurple.setOnClickListener {
                binding.root.setBackgroundColor(
                    Color.parseColor(
                        "#9C27B0"
                    )
                )
            }
            binding.btnLightBlue.setOnClickListener {
                binding.root.setBackgroundColor(
                    Color.parseColor(
                        "#40C4FF"
                    )
                )
            }
            binding.btnBlue.setOnClickListener { binding.root.setBackgroundColor(Color.parseColor("#536DFE")) }
            binding.btnPink.setOnClickListener { binding.root.setBackgroundColor(Color.parseColor("#FF69DF")) }
            binding.btnCyan.setOnClickListener { binding.root.setBackgroundColor(Color.parseColor("#64FFDA")) }
            binding.btnLime.setOnClickListener { binding.root.setBackgroundColor(Color.parseColor("#B2FF59")) }
            binding.btnDeepPurple.setOnClickListener {
                binding.root.setBackgroundColor(
                    Color.parseColor(
                        "#E040FB"
                    )
                )
            }
            binding.btnGreen.setOnClickListener { binding.root.setBackgroundColor(Color.parseColor("#00BFA5")) }
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
        fun onNotaEditClickListener(nota: Nota)
        fun onNotaDeleteClickListener(nota: Nota)
    }
}