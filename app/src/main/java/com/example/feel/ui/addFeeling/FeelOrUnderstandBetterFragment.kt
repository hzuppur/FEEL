package com.example.feel.ui.addFeeling

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.feel.ui.feelBetter.FeelBetterActivity
import com.example.feel.MainActivity
import com.example.feel.R
import com.example.feel.ui.understand.UnderstandActivity
import kotlinx.android.synthetic.main.fragment_feel_or_understand_better.view.*


class FeelOrUnderstandBetterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_feel_or_understand_better, container, false)

        val nextButton = view.NextButton
        val betterButton = view.feelBetterButton
        val understandButton = view.understandButton


        nextButton.setOnClickListener {
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
        }

        betterButton.setOnClickListener {
            val intent = Intent(requireContext(), FeelBetterActivity::class.java)
            startActivity(intent)
        }

        understandButton.setOnClickListener {
            val intent = Intent(requireContext(), UnderstandActivity::class.java)
            startActivity(intent)
        }

        return view
    }
}