package com.example.feel.ui.addFeeling

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.graphics.Color
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.feel.R
import com.example.feel.data.FeelingTempViewModel
import kotlinx.android.synthetic.main.fragment_feel_when.view.*
import java.util.*


class FeelWhenFragment : Fragment(), DatePickerDialog.OnDateSetListener {

    private val viewModel: FeelingTempViewModel by activityViewModels()
    lateinit var textView: TextView
    lateinit var button: Button
    var day = 0
    var month: Int = 0
    var year: Int = 0
    var feelingDate: Long? = null

    @SuppressLint("SimpleDateFormat")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_feel_when, container, false)

        view.NextButton.setOnClickListener {
            viewModel.feelingTime = feelingDate
            findNavController().navigate(R.id.action_feelWhenFragment_to_feelOrUnderstandBetterFragment)
        }

        val mPickTimeBtn = view.pickDateBtn
        val mPickTimeToday = view.buttonToday
        textView = view.dateTv

        mPickTimeBtn.setOnClickListener {
            val calendar: Calendar = Calendar.getInstance()
            day = calendar.get(Calendar.DAY_OF_MONTH)
            month = calendar.get(Calendar.MONTH)
            year = calendar.get(Calendar.YEAR)

            val datePickerDialog = DatePickerDialog(requireContext(), this, year, month, day)
            datePickerDialog.show()
            datePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK)
            datePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK)
        }


        mPickTimeToday.setOnClickListener {
            val sdf = SimpleDateFormat("dd/ M/ yyyy")
            val currentDate = sdf.format(Date())
            // Display Selected date in TextView
            textView.text = currentDate
            feelingDate = Date().time
        }

        return view
    }

    @SuppressLint("SetTextI18n")
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        textView.text = "${dayOfMonth}/ ${month}/ $year"
        val combinedCal: Calendar = GregorianCalendar(TimeZone.getTimeZone("GMT+2"))
        combinedCal.set(year, month, dayOfMonth)
        feelingDate = combinedCal.time.time
    }
}