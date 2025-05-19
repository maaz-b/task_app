package com.example.practicethree

import android.content.Context
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practicethree.data.model.TaskCategory
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CreateTaskActivity : AppCompatActivity() {

    private var selectedDate : Date = Date()
    private var selectedCategory : TaskCategory = TaskCategory.Other


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

        val textField = findViewById<EditText>(R.id.addTaskEditText)
        val rootView = findViewById<View>(android.R.id.content)


        val saveButton = findViewById<PrimeButton>(R.id.addTaskSaveButton)
        saveButton.setOnClickListener{
            if(textField.text.trim().isEmpty()){
                Snackbar.make(rootView,"Task name cannot be empty!", Snackbar.LENGTH_SHORT).show()
            }else{
                finish()
            }

        }


        val addTaskPersonalButton = findViewById<CardView>(R.id.addTaskPersonalCV)
        val addTaskWorkButton = findViewById<CardView>(R.id.addTaskWorkCV)
        val addTaskHealthButton = findViewById<CardView>(R.id.addTaskHealthCV)
        val addTaskPersonalText = findViewById<TextView>(R.id.addTaskPersonalTV)
        val addTaskWorkText = findViewById<TextView>(R.id.addTaskWorkTV)
        val addTaskHealthText = findViewById<TextView>(R.id.addTaskHealthTV)

        setDefaultCategoryColors(addTaskPersonalButton, addTaskWorkButton, addTaskHealthButton, this)

        setCategoryFunctionalities(addTaskPersonalButton, addTaskPersonalText, addTaskWorkButton, addTaskWorkText, addTaskHealthButton, addTaskHealthText,this)



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun setDefaultCategoryColors(personalButton: CardView, workButton: CardView, healthButton: CardView, context: Context){
        val brownish = ContextCompat.getColor(context, R.color.brownish)

        personalButton.setCardBackgroundColor(brownish)
        workButton.setCardBackgroundColor(brownish)
        healthButton.setCardBackgroundColor(brownish)
    }

    fun setCategoryFunctionalities(personalButton : CardView, personalText : TextView, workButton: CardView, workText: TextView, healthButton: CardView, healthText: TextView, context:Context){

        val brown = ContextCompat.getColor(context,R.color.brown)
        val brownish = ContextCompat.getColor(context, R.color.brownish)
        val green = ContextCompat.getColor(context, R.color.green)
        val greenish = ContextCompat.getColor(context, R.color.greenish)
        val blue = ContextCompat.getColor(context, R.color.blue)
        val bluish = ContextCompat.getColor(context, R.color.bluish)
        val pink = ContextCompat.getColor(context,R.color.pink)
        val pinkish = ContextCompat.getColor(context, R.color.pinkish)

        personalButton.setOnClickListener {
            if(selectedCategory == TaskCategory.Personal){
                selectedCategory = TaskCategory.Other
                personalButton.setCardBackgroundColor(brownish)
                personalText.setTextColor(brown)
                workButton.setCardBackgroundColor(brownish)
                workText.setTextColor(brown)
                healthButton.setCardBackgroundColor(brownish)
                healthText.setTextColor(brown)
            }else{
                selectedCategory = TaskCategory.Personal
                personalButton.setCardBackgroundColor(pinkish)
                personalText.setTextColor(pink)
                workButton.setCardBackgroundColor(brownish)
                workText.setTextColor(brown)
                healthButton.setCardBackgroundColor(brownish)
                healthText.setTextColor(brown)
            }
        }

        healthButton.setOnClickListener {
            if(selectedCategory == TaskCategory.Health){
                selectedCategory = TaskCategory.Other
                personalButton.setCardBackgroundColor(brownish)
                personalText.setTextColor(brown)
                workButton.setCardBackgroundColor(brownish)
                workText.setTextColor(brown)
                healthButton.setCardBackgroundColor(brownish)
                healthText.setTextColor(brown)
            }else{
                selectedCategory = TaskCategory.Health
                personalButton.setCardBackgroundColor(brownish)
                personalText.setTextColor(brown)
                workButton.setCardBackgroundColor(brownish)
                workText.setTextColor(brown)
                healthButton.setCardBackgroundColor(bluish)
                healthText.setTextColor(blue)
            }

        }

        workButton.setOnClickListener {
            if(selectedCategory == TaskCategory.Work){
                selectedCategory = TaskCategory.Other
                personalButton.setCardBackgroundColor(brownish)
                personalText.setTextColor(brown)
                workButton.setCardBackgroundColor(brownish)
                workText.setTextColor(brown)
                healthButton.setCardBackgroundColor(brownish)
                healthText.setTextColor(brown)
            }else{
                selectedCategory = TaskCategory.Work
                personalButton.setCardBackgroundColor(brownish)
                personalText.setTextColor(brown)
                workButton.setCardBackgroundColor(greenish)
                workText.setTextColor(green)
                healthButton.setCardBackgroundColor(brownish)
                healthText.setTextColor(brown)
            }

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