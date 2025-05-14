package com.example.practicethree

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practicethree.data.model.TaskCategory
import com.example.practicethree.data.model.TaskModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val bigDate = findViewById<TextView>(R.id.textViewBigDate)
        setCurrentDayOnInit(bigDate)

        val recyclerView = findViewById<RecyclerView>(R.id.checkboxRecycleView)
        createAndSetTaskList(recyclerView, this)


        val addFAB = findViewById<FloatingActionButton>(R.id.floatingActionButton2)
        addFAB.setOnClickListener{
            val intent = Intent(this, CreateTaskActivity::class.java)
        startActivity(intent)}



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}



fun setCurrentDayOnInit(bigTV : TextView){
    val currentDate = Date()
    val formatter = SimpleDateFormat("dd MMM", Locale.getDefault())
    val todayDay : String = formatter.format(currentDate)
    bigTV.text = todayDay
}

fun createAndSetTaskList(homeRecyclerView : RecyclerView, context : Context){
    val tasks : List<String> = listOf("Buy grocery", "Get work done", "Walk the dog", "Do dishes", "Get away with a bank robbery", "Write some code", "Make breakfast", "Learn native android development", "Repair motorcycle", "Make dinner", "Play some video games")
    val cDate = LocalDate.now()
    val taskList : List<TaskModel> = tasks.map { task-> TaskModel(task, if(task.length % 2 == 0) true else false, cDate, TaskCategory.Other) }
    homeRecyclerView.layoutManager = LinearLayoutManager(context)
    homeRecyclerView.adapter = TaskCheckboxAdapter(taskList)
}