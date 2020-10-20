package com.example.feel

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.text.LineBreaker
import android.os.Build
import android.os.Bundle
import android.text.Layout.JUSTIFICATION_MODE_INTER_WORD
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.feel.ui.addFeeling.FeelTriggerActivity
import com.example.feel.ui.addFeeling.FeelUnderstandOrBetter
import kotlinx.android.synthetic.main.feel_better.*


class UnderstandActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.understand_feeling)

        val nextButton = findViewById<Button>(R.id.NextButton)
        nextButton.setOnClickListener {
            val intent = Intent(this, FeelUnderstandOrBetter::class.java)
            startActivity(intent)
        }
    }
}