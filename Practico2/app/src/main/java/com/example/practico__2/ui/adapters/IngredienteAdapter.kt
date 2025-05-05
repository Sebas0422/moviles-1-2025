package com.example.practico__2.ui.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.practico__2.R
import com.example.practico__2.db.models.Ingrediente

class IngredienteAdapter(
    private val ingredientes: List<Ingrediente>,
    private val onItemClick: (Ingrediente) -> Unit
) : RecyclerView.Adapter<IngredienteAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val btnIngrediente: Button = itemView.findViewById(R.id.btnIngrediente)

        fun bind(ingrediente: Ingrediente) {
            btnIngrediente.text = ingrediente.nombre
            btnIngrediente.setBackgroundColor(
                if (ingrediente.seleccionado)
                    Color.parseColor("#A5D6A7")
                else
                    Color.parseColor("#E0E0E0")
            )
            btnIngrediente.setOnClickListener {
                onItemClick(ingrediente)
                notifyItemChanged(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.ingrediente_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = ingredientes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(ingredientes[position])
    }
}
