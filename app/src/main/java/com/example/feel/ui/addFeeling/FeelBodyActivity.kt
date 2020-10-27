package com.example.feel.ui.addFeeling

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Matrix
import android.os.Bundle
import android.view.MotionEvent
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.graphics.drawable.toBitmap
import com.example.feel.R


class FeelBodyActivity : AppCompatActivity() {
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.feeling_body)

        val nextButton = findViewById<Button>(R.id.NextButton)
        nextButton.setOnClickListener {
            val intent = Intent(this, FeelTriggerActivity::class.java)
            startActivity(intent)
        }

        val bodyImage = findViewById<ImageView>(R.id.body)
        bodyImage.setOnTouchListener { v, event ->
            when (event?.action) {
                MotionEvent.ACTION_DOWN ->
                    onBodyClicked(event);
            }

            v?.onTouchEvent(event) ?: true
        }
    }

    private fun onBodyClicked(event: MotionEvent) {
        // If touch color is -1, that means that the click was on body because the function don't get the color of vector image
        val touchOnBody = getHotspotColor(event.x, event.y) == -1

        val touchSpotSize = 200

        if (touchOnBody) {
            val constraintLayout = findViewById<ConstraintLayout>(R.id.body_layout)
            val imageView = ImageView(this)
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
        val img = findViewById<ImageView>(R.id.body)

        val imgDrawable = img.drawable
        //imgDrawable will not be null if you had set src to ImageView, in case of background drawable it will be null
        //imgDrawable will not be null if you had set src to ImageView, in case of background drawable it will be null
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

