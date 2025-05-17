package com.example.practicethree

import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CreateTaskActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_create_task)

        val createTaskCross = findViewById<ImageView>(R.id.imageViewCross)
        crossButtonOnClick(createTaskCross)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
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

    fun crossButtonOnClick(imageView : ImageView){
        imageView.setOnClickListener{
            finish()
        }
    }
}