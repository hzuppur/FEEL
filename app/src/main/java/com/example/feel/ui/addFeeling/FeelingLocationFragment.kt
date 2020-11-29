package com.example.feel.ui.addFeeling

import android.annotation.SuppressLint
import android.graphics.Matrix
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.feel.R
import com.example.feel.data.FeelingSpot
import com.example.feel.data.FeelingTempViewModel
import kotlinx.android.synthetic.main.fragment_feeling_location.view.*


class FeelingLocationFragment : Fragment() {

    private lateinit var currentView: View
    private val viewModel: FeelingTempViewModel by activityViewModels()
    private val feelingLocations: MutableList<FeelingSpot> = mutableListOf()

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        currentView =  inflater.inflate(R.layout.fragment_feeling_location, container, false)

        val nextButton = currentView.NextButton
        nextButton.setOnClickListener {
            viewModel.feelingLocation = feelingLocations
            findNavController().navigate(R.id.action_feelingLocationFragment_to_feelingTriggerFragment)
        }

        val bodyImage = currentView.body
        bodyImage.setOnTouchListener { v, event ->
            when (event?.action) {
                MotionEvent.ACTION_DOWN ->
                    onBodyClicked(event)
            }
            v?.onTouchEvent(event) ?: true
        }

        return currentView
    }

    private fun onBodyClicked(event: MotionEvent) {
        // If touch color is -1, that means that the click was on body because the function don't get the color of vector image
        val touchOnBody = getHotspotColor(event.x, event.y) == -1

        val touchSpotSize = 200

        if (touchOnBody) {
            feelingLocations.add(FeelingSpot(event.x, event.y))

            val constraintLayout = currentView.body_layout
            val imageView = ImageView(requireContext())
            imageView.x = event.x - touchSpotSize/2
            imageView.y = event.y - touchSpotSize/2
            imageView.setImageResource(R.drawable.ic_touch_spot)
            constraintLayout.addView(imageView)
            imageView.layoutParams.height = touchSpotSize
            imageView.layoutParams.width = touchSpotSize
            imageView.requestLayout()
        }
    }

    private fun getHotspotColor(x: Float, y: Float): Int {
        val img = currentView.body

        val imgDrawable = img.drawable
        val bitmap = imgDrawable.toBitmap()

        val inverse = Matrix()
        img.imageMatrix.invert(inverse)
        val touchPoint = floatArrayOf(x, y)
        inverse.mapPoints(touchPoint)
        val xCoord = touchPoint[0].toInt()
        val yCoord = touchPoint[1].toInt()

        val touchedRGB = bitmap.getPixel(xCoord, yCoord)

        return touchedRGB
    }
}