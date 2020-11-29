package com.example.feel.fragments.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.feel.data.FeelingSpot
import com.example.feel.R
import com.example.feel.data.Feeling
import com.example.feel.data.FeelingViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*


class AddFragment : Fragment() {

    private lateinit var mFeelingViewModel: FeelingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        mFeelingViewModel = ViewModelProvider(this).get(FeelingViewModel::class.java)

        view.add_btn.setOnClickListener {
            insertDataToDataToDatabase()
        }

        return view
    }

    private fun insertDataToDataToDatabase() {
        val feeling_positivity = editPositivity.text
        val feeling_intensity = editIntensity.text
        val feeling_location = listOf(FeelingSpot(1f,1f), FeelingSpot(1f,1f))
        val trigger = editTrigger.text.toString()
        val feeling_type = editType.text.toString()
        val reaction_to_feeling = editReaction.text.toString()
        val feeling_time = editDate.text.toString()


        val feeling = Feeling(
            0,
            Integer.parseInt(feeling_positivity.toString()),
            Integer.parseInt(feeling_intensity.toString()),
            feeling_location,
            trigger,
            feeling_type,
            reaction_to_feeling,
            0
            )

        mFeelingViewModel.addFeeling(feeling)

        Toast.makeText(requireContext(), "Added succsesfully", Toast.LENGTH_LONG).show()
        findNavController().navigate(R.id.action_addFragment_to_listFragment)
    }
}
