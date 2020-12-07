package com.example.feel.ui.viewFeelings.detailedView

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.feel.R
import com.example.feel.data.DetailedViewViewModel
import com.example.feel.data.Feeling
import kotlinx.android.synthetic.main.custom_row.view.*
import kotlinx.android.synthetic.main.fragment_detailed_view.view.*
import kotlinx.android.synthetic.main.fragment_feeling_location.view.*
import java.util.*

class DetailedViewFragment : Fragment() {

    private val mDetailedViewViewModel: DetailedViewViewModel by activityViewModels()
    private lateinit var currentView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        currentView = inflater.inflate(R.layout.fragment_detailed_view, container, false)

        val feeling = mDetailedViewViewModel.feeling

        // Set up back button
        currentView.BackButton.setOnClickListener { activity?.onBackPressed() }

        // Add feeling time
        val sdf = java.text.SimpleDateFormat("d. MMMM yyyy", Locale.getDefault())
        val date = Date(feeling.feeling_time)
        currentView.FeelingTime.text = sdf.format(date)

        // Add feeling text values
        currentView.FeelingPos.text = getString(R.string.DetailsFeelingPos, feeling.feeling_positivity)
        currentView.FeelingIntensity.text = getString(R.string.DetailsFeelingIntensity, feeling.feeling_positivity)
        currentView.FeelingEmotion.text = getString(R.string.DetailsFeelingEmotion, feeling.feeling_type)
        currentView.FeelingTrigger.text = getString(R.string.DetailsFeelingEmotion, feeling.trigger)
        currentView.FeelingReaction.text = getString(R.string.DetailsFeelingEmotion, feeling.reaction_to_feeling)


        // Add touch spots to image
        addTouchSpots(feeling)


        return currentView
    }

    private fun addTouchSpots(feeling: Feeling){
        val touchSpotSize = 200
        for (spot in feeling.feeling_location){
            val constraintLayout = currentView.body_layout_container
            val imageView = ImageView(requireContext())
            imageView.x = spot.x - touchSpotSize/2
            imageView.y = spot.y - touchSpotSize/2
            imageView.setImageResource(R.drawable.ic_touch_spot)
            constraintLayout.addView(imageView)
            imageView.layoutParams.height = touchSpotSize
            imageView.layoutParams.width = touchSpotSize
            imageView.requestLayout()
        }
    }
}