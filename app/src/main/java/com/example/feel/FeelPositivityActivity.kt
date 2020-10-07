package com.example.feel

import android.os.Bundle
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class FeelPositivityActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.feeling_positivity)

        val seekText = findViewById<TextView>(R.id.FeelingValue)
        val seekProgress = findViewById<TextView>(R.id.SeekBarProgress)

        val seek = findViewById<SeekBar>(R.id.seekBar)
        seek?.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar, progress: Int, fromUser: Boolean) {
                if (progress < 30 ){
                    seekText.text = getString(R.string.FeelingValueBad)
                }else if (progress < 70){
                    seekText.text = getString(R.string.FeelingValueNeutral)
                }else {
                    seekText.text = getString(R.string.FeelingValueGood)
                }

                val prog = progress * (seek.width - 2 * seek.thumbOffset) / seek.max
                seekProgress.text = progress.toString()
                seekProgress.x = seek.x + prog + seek.thumbOffset / 2
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                Toast.makeText(this@FeelPositivityActivity,
                    "Progress is: " + seek.progress + "%",
                    Toast.LENGTH_SHORT).show()
            }
        })
    }
}