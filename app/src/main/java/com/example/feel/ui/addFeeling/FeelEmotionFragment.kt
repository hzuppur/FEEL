package com.example.feel.ui.addFeeling

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.feel.R
import com.example.feel.data.FeelingTempViewModel
import kotlinx.android.synthetic.main.fragment_feel_emotion.*
import kotlinx.android.synthetic.main.fragment_feel_emotion.view.*


class FeelEmotionFragment : Fragment(), RotaryKnobView.RotaryKnobListener {

    private lateinit var currentView: View
    private val viewModel: FeelingTempViewModel by activityViewModels()
    private var emotion: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        currentView = inflater.inflate(R.layout.fragment_feel_emotion, container, false)

        currentView.NextButton.setOnClickListener {
            viewModel.feelingType = emotion
            findNavController().navigate(R.id.action_feelEmotionFragment_to_feelReactionFragment)
        }

        return currentView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        knob.listener = this
    }

    override fun onRotate(value: Int) {
        val feelingText = currentView.FeelingText

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
        emotion = feelings[index]
    }
}
