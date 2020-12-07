package com.example.feel.ui.exercises

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.feel.ui.feelBetter.FeelBetterActivity
import com.example.feel.R

class Exercise1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.exercise1)

        val nextButton = findViewById<Button>(R.id.NextButton)
        nextButton.setOnClickListener {
            onBackPressed()
        }
    }
}