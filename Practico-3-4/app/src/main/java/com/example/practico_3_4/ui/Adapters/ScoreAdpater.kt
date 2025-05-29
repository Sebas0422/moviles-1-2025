package com.example.practico_3_4.ui.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practico_3_4.db.models.Score
import com.example.pruebatetris.databinding.ScoreItemLayoutBinding

class ScoreAdpater(
    var scores : List<Score>
) : RecyclerView.Adapter<ScoreAdpater.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ScoreItemLayoutBinding.inflate(
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
        val item = scores[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return scores.size
    }

    fun setData(scores: List<Score>) {
        this.scores = scores
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ScoreItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Score) {
            binding.txtName.text = "Nombre: ${item.name}"
            binding.txtScoreNumber.text = "Puntuación: ${item.score}"
        }
    }
}