package com.example.practicethree

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.checkboxRecycleView)
        val tasks : List<String> = listOf("Buy grocery", "Get work done", "Walk the dog", "Do dishes", "Get away with a bank robbery", "Write some code", "Make breakfast", "Learn native android development", "Repair motorcycle", "Make dinner", "Play some video games")
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = TaskCheckboxAdapter(tasks)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}