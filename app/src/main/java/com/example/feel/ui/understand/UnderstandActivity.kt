package com.example.feel.ui.understand

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.feel.R


class UnderstandActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.understand_feeling)

        val nextButton = findViewById<Button>(R.id.NextButton)
        nextButton.setOnClickListener {
            onBackPressed()
        }
    }
}