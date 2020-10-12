package com.example.feel


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class FeelReactedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.feeling_reacted)

        val nextButton = findViewById<Button>(R.id.NextButton)
        nextButton.setOnClickListener {
            val intent = Intent(this, FeelWhenActivity::class.java)
            startActivity(intent)
        }
    }
}