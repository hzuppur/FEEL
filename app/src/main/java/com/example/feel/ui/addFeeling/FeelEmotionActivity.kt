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

        findViewById<TextView>(R.id.FeelingText).text = knob.value.toString()

    }

    override fun onRotate(value: Int) {
        val feelingText = findViewById<TextView>(R.id.FeelingText)

        val step = 360/6

        when {
            value < step -> {
                feelingText.text = "Kurb"
            }
            value < step *2 -> {
                feelingText.text = "Vihane"
            }
            value < step * 3 -> {
                feelingText.text = "Kuri"
            }
            value < step * 4 -> {
                feelingText.text = "Rõõmus"
            }
            value < step * 5 -> {
                feelingText.text = "Masendunud"
            }
            true -> {
                feelingText.text = "Ükskõikne"
            }
        }

        //feelingText.text = value.toString()
    }
}