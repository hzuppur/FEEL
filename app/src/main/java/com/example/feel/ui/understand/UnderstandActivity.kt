package com.example.feel.ui.understand

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.feel.R
import kotlinx.android.synthetic.main.understand_feeling.*
import java.util.*


class UnderstandActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var playButton: Button

    private var timer = Timer()
    private var audioPlaying = false

    private lateinit var understandText: List<String>
    private lateinit var timestamps: List<Int>
    private var audioFileId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.understand_feeling)

        findViewById<Button>(R.id.BackButton).setOnClickListener { onBackPressed() }
        playButton = findViewById(R.id.PlayButton)
        playButton.setOnClickListener { audioPlayback() }

        // Load the resources
        understandText = resources.getStringArray(R.array.understand_depression_txt).toList()
        timestamps = resources.getIntArray(R.array.understand_depression_timestamps).toList()
        audioFileId = R.raw.feel_depresioon

        // Set up Recyclerview
        val adapter = UnderstandListAdapter()
        recyclerView = understandRecyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = SlowLinearLayoutManager(this)

        // Add text to Recyclerview
        adapter.setData(understandText.toList())

        // Create media player
        mediaPlayer = MediaPlayer.create(this, audioFileId)
        mediaPlayer.setOnCompletionListener { setUpAudioScroll() }

        // Set up the activity for audio and scroll sync
        setUpAudioScroll()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mediaPlayer.isPlaying) {
            mediaPlayer.stop()
            mediaPlayer.release()
        }
    }

    private fun setUpAudioScroll() {
        // purge the previous timer, only has affect when it is not first audio playback
        timer.cancel()
        timer.purge()
        scrollTimer()

        // Set media player to start
        mediaPlayer.seekTo(0)
        audioPlaying = false
        playButton.text = getString(R.string.PlayButton)
    }

    private fun scrollToPos(pos: Int) {
        recyclerView.smoothScrollToPosition(pos)
    }

    private fun audioPlayback() {
        if (audioPlaying) {
            mediaPlayer.pause()
            playButton.text = getString(R.string.ContinueButton)
            audioPlaying = false
        } else {
            mediaPlayer.start()
            playButton.text = getString(R.string.PauseButton)
            audioPlaying = true
        }
    }

    private fun scrollTimer() {
        // Counts the time the audio has played to scroll down at the right time
        timer = Timer()
        var countTimer = 1
        var currentTime = 0

        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                runOnUiThread {
                    // Increment the timer only when the audio is playing and scroll when whe hit the timestamp
                    if (audioPlaying) {
                        if (timestamps.contains(currentTime)) {
                            scrollToPos(countTimer++)
                        }
                        currentTime++
                    }
                }
            }
        }, 0, 100)
    }
}