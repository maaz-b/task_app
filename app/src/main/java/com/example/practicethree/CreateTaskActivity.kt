package com.example.practicethree

import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CreateTaskActivity : AppCompatActivity() {

    private var selectedDate : Date = Date()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_create_task)

        val dateTv = findViewById<TextView>(R.id.addTaskDateTV)
        setCurrentDayOnInit(dateTv)

        val createTaskCross = findViewById<ImageView>(R.id.imageViewCross)
        crossButtonOnClick(createTaskCross)

        val createTaskClock = findViewById<CardView>(R.id.addTaskClockCV)
        createTaskClock.setOnClickListener{
            selectDate(dateTv)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun setCurrentDayOnInit(bigTV : TextView){
        val currentDate = Date()
        val formatter = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        val todayDay : String = formatter.format(currentDate)
        bigTV.text = todayDay
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {

        if (ev != null){
            if(ev.action == MotionEvent.ACTION_DOWN){
                val view = currentFocus
                if (view is EditText)
                {
                    val outRect = android.graphics.Rect()
                    if (!outRect.contains(ev.rawX.toInt(), ev.rawY.toInt())){
                        if (currentFocus != null) {
                            val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
                            currentFocus!!.clearFocus()
                        }
                    }
                }

            }
        }

        return super.dispatchTouchEvent(ev)
    }

    fun selectDate(dateTV : TextView){
        val dateInMS = selectedDate.time
        val datePickerBuilder = MaterialDatePicker.Builder.datePicker().setSelection(dateInMS).setTitleText("Select Date")
        val datePicker = datePickerBuilder.build()
        datePicker.addOnPositiveButtonClickListener { selection ->
            selectedDate = Date(selection)
            val formatter = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
            val todayDay : String = formatter.format(selectedDate)
            dateTV.text = todayDay
        }
        datePicker.show(supportFragmentManager, "Date Picker")

    }

    fun crossButtonOnClick(imageView : ImageView){
        imageView.setOnClickListener{
            finish()
        }
    }
}