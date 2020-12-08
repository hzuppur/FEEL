package com.example.feel.ui.exercises

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.feel.R
import kotlinx.android.synthetic.main.fragment_exercise.view.*


class ExerciseAdapter(
    private var exercise: List<Exercise>

) : RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>() {
    inner class ExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_exercise, parent, false)
        return ExerciseViewHolder(view)
    }

    override fun getItemCount(): Int {
        return exercise.size
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        holder.itemView.apply {
            button.text = exercise[position].title
            button.setOnClickListener {
                val intent = Intent(context, Exercise1::class.java)
                context?.startActivity(intent)
            }
        }
    }
}