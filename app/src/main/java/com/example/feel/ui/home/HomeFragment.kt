package com.example.feel.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.feel.ui.addFeeling.FeelPositivityActivity
import com.example.feel.R


class HomeFragment : Fragment(), View.OnClickListener {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)

        homeViewModel.text.observe(viewLifecycleOwner, Observer { textView.text = it })

        val btn: Button = root!!.findViewById<Button>(R.id.StartButton)
        btn.setOnClickListener(this)

        return root
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.StartButton -> {
                val intent = Intent(activity, FeelPositivityActivity::class.java)
                activity?.startActivity(intent)
            }
        }
    }
}