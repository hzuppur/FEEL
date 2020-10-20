package com.example.feel.ui.addFeeling

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.feel.FeelBetterActivity
import com.example.feel.MainActivity
import com.example.feel.R
import com.example.feel.UnderstandActivity


class FeelUnderstandOrBetter : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.feeling_understand_or_better)

        val nextButton = findViewById<Button>(R.id.NextButton)
        val betterButton = findViewById<Button>(R.id.feelBetterButton)
        val understandButton = findViewById<Button>(R.id.understandButton)


        nextButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        betterButton.setOnClickListener {
            val intent = Intent(this, FeelBetterActivity::class.java)
            startActivity(intent)
        }

        understandButton.setOnClickListener {
            val intent = Intent(this, UnderstandActivity::class.java)
            startActivity(intent)
        }
    }
}