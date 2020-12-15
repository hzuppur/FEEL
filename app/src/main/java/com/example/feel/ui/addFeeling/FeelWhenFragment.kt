package com.example.feel.ui.addFeeling

import android.app.DatePickerDialog
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.feel.R
import com.example.feel.data.FeelingTempViewModel
import kotlinx.android.synthetic.main.fragment_feel_when.view.*
import java.text.DateFormatSymbols
import java.util.*


class FeelWhenFragment : Fragment(), DatePickerDialog.OnDateSetListener {

    private val viewModel: FeelingTempViewModel by activityViewModels()
    private lateinit var textView: TextView
    private lateinit var nextButton: Button

    private var day = 0
    private var month: Int = 0
    private var year: Int = 0
    private var feelingDate: Long? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_feel_when, container, false)

        nextButton = view.NextButton
        nextButton.setOnClickListener {
            viewModel.feelingTime = feelingDate
            findNavController().navigate(R.id.action_feelWhenFragment_to_feelOrUnderstandBetterFragment)
        }
        markButtonDisable(nextButton)

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
            val sdf = java.text.SimpleDateFormat("d. MMMM yyyy", Locale.getDefault())
            val currentDate = sdf.format(Date())
            // Display Selected date in TextView
            textView.text = currentDate
            feelingDate = Date().time

            markButtonEnable(nextButton)
        }

        return view
    }

    private fun markButtonDisable(button: Button) {
        button.isEnabled = false
        button.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorPrimary))
        button.setBackgroundColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.colorPrimaryDark
            )
        )
    }

    private fun markButtonEnable(button: Button) {
        button.isEnabled = true
        button.setTextColor(Color.WHITE)
        button.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.colorPrimary))
    }


    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val combinedCal: Calendar = GregorianCalendar(TimeZone.getTimeZone("GMT+2"))
        combinedCal.set(year, month, dayOfMonth)

        // Check if date in the future, if not, enable next button
        if (combinedCal.time.time > Date().time){
            Toast.makeText(requireContext(), "Ei sa valida kuupäeva tulevikus", Toast.LENGTH_LONG).show()
        }else {
            feelingDate = combinedCal.time.time
            textView.text = getString(R.string.DateFormat, dayOfMonth.toString(), getMonth(month), year.toString())
            markButtonEnable(nextButton)
        }
    }

    private fun getMonth(month: Int): String? {
        return DateFormatSymbols().months[month]
    }
}