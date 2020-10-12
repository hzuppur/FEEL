package com.example.feel

import android.os.Bundle
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class FeelIntensityActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.feeling_intensity)

        val seekText = findViewById<TextView>(R.id.IntensityValue)
        val seekProgress = findViewById<TextView>(R.id.SeekBarProgress)

        val seek = findViewById<SeekBar>(R.id.seekBar)
        seek?.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar, progress: Int, fromUser: Boolean) {
                when {
                    progress < 20 -> seekText.text = getString(R.string.IntensityVeryWeak)
                    progress < 40 -> seekText.text = getString(R.string.IntensityWeak)
                    progress < 75 -> seekText.text = getString(R.string.IntensityStrong)
                    else -> seekText.text = getString(R.string.IntensityVeryStrong)

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