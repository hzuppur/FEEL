package com.example.feel

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.feel.ui.addFeeling.FeelTriggerActivity
import com.example.feel.ui.addFeeling.FeelUnderstandOrBetter
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
            val intent = Intent(this, FeelUnderstandOrBetter::class.java)
            startActivity(intent)
        }
    }
}