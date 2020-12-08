package com.example.feel.ui.feelBetter

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.feel.R
import com.example.feel.ui.exercises.Exercise
import com.example.feel.ui.exercises.ExerciseAdapter
import kotlinx.android.synthetic.main.feel_better.*


class FeelBetterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.feel_better)

        val exerciseList = mutableListOf(
            Exercise("Hingamine"),
            Exercise("Maha rahunemine"),
            Exercise("Mine oma rõõmsasse kohta"),
            Exercise("Mediteerimine"),
            Exercise("Tunde unustamine"),
            Exercise("Hea mälestuse meenutamine")
        )
        val adapter = ExerciseAdapter(exerciseList)
        rvFragments.adapter = adapter
        rvFragments.layoutManager = LinearLayoutManager(this)

        val nextButton = findViewById<Button>(R.id.NextButton)
        nextButton.setOnClickListener {
            onBackPressed()
        }
    }
}