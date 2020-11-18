package com.example.feel.ui.addFeeling

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Matrix
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.feel.R
import kotlinx.android.synthetic.main.feeling_emotion.*
import kotlin.math.floor


class FeelEmotionActivity : AppCompatActivity(), RotaryKnobView.RotaryKnobListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.feeling_emotion)
        knob.listener = this

        val nextButton = findViewById<Button>(R.id.NextButton)
        nextButton.setOnClickListener {
            val intent = Intent(this, FeelReactedActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onRotate(value: Int) {
        val feelingText = findViewById<TextView>(R.id.FeelingText)

        val feelings = arrayListOf(
            "Hirm",
            "Vastikus",
            "Rõõm",
            "Kurbus",
            "Üllatus",
            "Neutraalsus",
            "Ärevus",
            "Armastus",
            "Depresioon",
            "Põlgus",
            "Häbi",
            "Kadedus",
            "Uhkus",
            "Viha"
        )

        val index = ((value) / (360.0) * 13.9).toInt()

        feelingText.text = feelings[index]
    }
}