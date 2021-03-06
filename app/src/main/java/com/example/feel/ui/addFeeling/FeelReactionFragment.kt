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
import kotlinx.android.synthetic.main.fragment_feel_reaction.view.*


class FeelReactionFragment : Fragment() {

    private val viewModel: FeelingTempViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_feel_reaction, container, false)
        val reaction = view.FeelingReactionText

        view.NextButton.setOnClickListener {
            viewModel.reactionToFeeling = reaction.text.toString()
            findNavController().navigate(R.id.action_feelReactionFragment_to_feelWhenFragment)
        }

        return view
    }
}