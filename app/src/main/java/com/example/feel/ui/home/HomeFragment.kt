package com.example.feel.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.feel.R
import kotlinx.android.synthetic.main.fragment_home.view.*


class HomeFragment : Fragment() {

    private val homeViewModel : HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)

        homeViewModel.text.observe(viewLifecycleOwner, { textView.text = it })

        root.StartButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_feelingPositivityFragment)
        }

        return root
    }
}