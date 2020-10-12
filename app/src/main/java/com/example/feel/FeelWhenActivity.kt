package com.example.feel

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*


class FeelWhenActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.feeling_when)

        val nextButton = findViewById<Button>(R.id.NextButton)
        nextButton.setOnClickListener {
            val intent = Intent(this, FeelUnderstandOrBetter::class.java)
            startActivity(intent)
        }

        val mPickTimeBtn = findViewById<Button>(R.id.pickDateBtn)
        val mPickTimeToday = findViewById<Button>(R.id.buttonToday)
        val textView = findViewById<TextView>(R.id.dateTv)

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        mPickTimeBtn.setOnClickListener {
            val dpd = DatePickerDialog(
                this,
                { view, year, monthOfYear, dayOfMonth ->
                    // Display Selected date in TextView
                    textView.text = "$dayOfMonth/ $month/ $year"
                }, year, month, day
            )
            dpd.show()
        }
        mPickTimeToday.setOnClickListener {
            val sdf = SimpleDateFormat("dd/ M/ yyyy")
            val currentDate = sdf.format(Date())
            // Display Selected date in TextView
            textView.text = currentDate
        }
    }
}