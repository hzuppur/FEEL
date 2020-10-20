package com.example.feel.ui.addFeeling

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.feel.R


class FeelPositivityActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.feeling_positivity)

        val seekText = findViewById<TextView>(R.id.FeelingValue)
        val seekProgress = findViewById<TextView>(R.id.SeekBarProgress)
        val nextButton = findViewById<Button>(R.id.NextButton)
        nextButton.setOnClickListener {
            val intent = Intent(this, FeelIntensityActivity::class.java)
            startActivity(intent)
        }

        val seek = findViewById<SeekBar>(R.id.seekBar)
        seek?.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar, progress: Int, fromUser: Boolean) {
                when {
                    progress < 30 -> seekText.text = getString(R.string.FeelingValueBad)
                    progress < 70 -> seekText.text = getString(R.string.FeelingValueNeutral)
                    else -> seekText.text = getString(R.string.FeelingValueGood)

                }

                val prog = progress * (seek.width - 2 * seek.thumbOffset) / seek.max
                seekProgress.text = progress.toString()
                seekProgress.x = seek.x + prog + seek.thumbOffset / 2
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }
        })
    }
}