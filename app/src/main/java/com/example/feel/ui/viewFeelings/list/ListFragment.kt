package com.example.feel.ui.viewFeelings.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.feel.R
import com.example.feel.data.DetailedViewViewModel
import com.example.feel.data.Feeling
import com.example.feel.data.FeelingViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*


class ListFragment : Fragment(), CellClickListener {

    private lateinit var mFeelingViewModel: FeelingViewModel
    private val mDetailedViewViewModel: DetailedViewViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        // Recyclerview
        val adapter = ListAdapter(this)
        val recyclerView = view.recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())


        // FeelingViewModel
        mFeelingViewModel = ViewModelProvider(this).get(FeelingViewModel::class.java)
        mFeelingViewModel.readAllData.observe(viewLifecycleOwner, { feeling ->
            adapter.setData(feeling.sortedByDescending { it.feeling_time })
        })

        return view
    }

    override fun onCellClickListener(data: Feeling) {
        mDetailedViewViewModel.addFeeling(data)
        findNavController().navigate(R.id.action_listFragment_to_detailedViewFragment)
    }
}