package com.example.feel.ui.feelBetter

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.feel.R
import kotlinx.android.synthetic.main.feel_better.*


class FeelBetterActivity : AppCompatActivity() {
    private lateinit var mediaPlayer: MediaPlayer
    private var currentMediaID = -1
    private var mediaPlaying = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.feel_better)

        val exerciseList = mutableListOf(
            Exercise("Mine oma ohutusse kohta", R.raw.mine_oma_ohutusse_kohta),
            Exercise("Sisemine naeratus", R.raw.sisemine_naeratus),
            Exercise("Tundele keskendumine", R.raw.tundele_keskendumine),
        )

        mediaPlayer = MediaPlayer.create(this, exerciseList[0].fileID)

        val adapter = ExerciseAdapter(exerciseList)
        rvFragments.adapter = adapter
        rvFragments.layoutManager = LinearLayoutManager(this)

        adapter.onItemClick = { exercise ->
            playPauseMedia(exercise.fileID)
        }

        val nextButton = findViewById<Button>(R.id.NextButton)
        nextButton.setOnClickListener {
            onBackPressed()
        }
    }

    private fun playPauseMedia(audioFileId: Int) {
        if (audioFileId != currentMediaID) {
            mediaPlayer.stop()
            mediaPlayer = MediaPlayer.create(this, audioFileId)
            mediaPlayer.start()
            currentMediaID = audioFileId
            mediaPlaying = true
        } else {
            mediaPlaying = if (mediaPlaying) {
                mediaPlayer.pause()
                false
            } else {
                mediaPlayer.start()
                true
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mediaPlayer.isPlaying) {
            mediaPlayer.stop()
            mediaPlayer.release()
        }
    }
}