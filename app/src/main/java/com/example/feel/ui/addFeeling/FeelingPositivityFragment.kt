package com.example.feel.ui.addFeeling

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.feel.R
import com.example.feel.data.FeelingTempViewModel
import kotlinx.android.synthetic.main.fragment_feeling_positivity.view.*


class FeelingPositivityFragment : Fragment() {

    private val viewModel: FeelingTempViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_feeling_positivity, container, false)

        val seekText = view.FeelingValue
        val seekProgress = view.SeekBarProgress
        val nextButton = view.NextButton
        var positivity = 50

        nextButton.setOnClickListener {
            viewModel.feelingPositivity = positivity
            findNavController().navigate(R.id.action_feelingPositivityFragment_to_feelingIntensityFragment)
        }

        val seek = view.seekBar
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
                positivity = progress
                seekProgress.x = seek.x + prog + seek.thumbOffset / 2
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}

            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })

        return view
    }
}