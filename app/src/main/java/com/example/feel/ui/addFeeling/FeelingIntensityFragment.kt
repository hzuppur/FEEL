package com.example.feel.ui.addFeeling

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.navigation.fragment.findNavController
import com.example.feel.R
import kotlinx.android.synthetic.main.fragment_feeling_intensity.view.*


class FeelingIntensityFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_feeling_intensity, container, false)
        val seekText = view.IntensityValue
        val seekProgress = view.SeekBarProgress

        val nextButton = view.NextButton
        nextButton.setOnClickListener {
            findNavController().navigate(R.id.action_feelingIntensityFragment_to_feelingLocationFragment)
        }

        val seek = view.seekBar
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

            override fun onStartTrackingTouch(p0: SeekBar?) {}

            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })

        return view
    }

}