package com.example.feel.ui.feelBetter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.feel.R
import kotlinx.android.synthetic.main.fragment_exercise.view.*


class ExerciseAdapter(private var exercise: List<Exercise>) :
    RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>() {
    private lateinit var mContex: Context
    private var buttons = mutableListOf<Button>()
    private var playing = -1

    inner class ExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.button

        init {
            buttons.add(itemView.button)

            itemView.button.setOnClickListener {
                // Invoke listener to start or stop audio playback
                // Audio playing is handled in FeelBetterActivity
                onItemClick?.invoke(exercise[position])

                // If currently pressed button was playing, change the icon to pause icon
                // Else iterate over all buttons, reset their backgrounds and play icons
                if (playing == exercise[position].fileID) {
                    itemView.button.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.ic_play_arrow,
                        0,
                        0,
                        0
                    )
                    playing = -1
                } else {
                    for (but in buttons) {
                        but.setBackgroundColor(
                            ContextCompat.getColor(
                                mContex,
                                R.color.colorPrimary
                            )
                        )
                        but.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.ic_play_arrow,
                            0,
                            0,
                            0
                        );
                    }

                    itemView.button.setBackgroundColor(
                        ContextCompat.getColor(
                            mContex,
                            R.color.colorPrimaryDark
                        )
                    )
                    itemView.button.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.ic_pause,
                        0,
                        0,
                        0
                    )
                    playing = exercise[position].fileID
                }
            }
        }
    }


    var onItemClick: ((Exercise) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        mContex = parent.context
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.fragment_exercise,
            parent,
            false
        )
        return ExerciseViewHolder(view)
    }

    override fun getItemCount(): Int {
        return exercise.size
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val exercise = exercise[position]
        holder.title.text = exercise.title
    }
}