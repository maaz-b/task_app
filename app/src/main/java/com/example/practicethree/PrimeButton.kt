package com.example.practicethree

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.example.practicethree.utils.dp
import java.lang.reflect.Constructor

class PrimeButton @JvmOverloads constructor(
    context: Context,
    attr: AttributeSet? =null,
    defStyle: Int = 0
): CardView(context, attr, defStyle) {
    private val textView: TextView
    init {
        LayoutInflater.from(context).inflate(R.layout.prime_button, this, true)
        textView = findViewById(R.id.primeButtonTextView)

        this.radius = 12.dp.toFloat()
        this.preventCornerOverlap = true



    }

    fun setText( txt: String){
        textView.text = txt
    }

    fun onClick(action:() -> Unit){
        this.setOnClickListener{
            action()
        }
    }
}