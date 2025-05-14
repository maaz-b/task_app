package com.example.practicethree

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.practicethree.data.model.TaskCategory
import com.example.practicethree.data.model.TaskModel

class TaskCheckboxAdapter(val tasks: List<TaskModel>) : RecyclerView.Adapter<TaskCheckboxAdapter.TaskCheckboxViewHolder>() {

    class TaskCheckboxViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val textView : TextView = itemView.findViewById(R.id.textView10)
        val checkbox : CheckBox = itemView.findViewById<CheckBox>(R.id.checkBoxTask)
        val categoryTextView : TextView = itemView.findViewById<TextView>(R.id.textViewTaskCategory)
        val cardViewCategory : CardView = itemView.findViewById<CardView>(R.id.cardViewTaskCategory)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskCheckboxViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_checkbox_layout, parent, false)
        return TaskCheckboxViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskCheckboxViewHolder, position: Int) {
      assignValuesToView(holder, position)
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    fun assignValuesToView(holder: TaskCheckboxViewHolder, position: Int){
        holder.textView.text = tasks[position].name
        holder.checkbox.isChecked = tasks[position].done
        holder.categoryTextView.text = tasks[position].category.name.uppercase()
        checkAndStyleCategory(holder,position)

    }

    fun checkAndStyleCategory(holder: TaskCheckboxViewHolder, position: Int){
        var textColor = ContextCompat.getColor(holder.itemView.context, R.color.textColor)
        var backColor = ContextCompat.getColor(holder.itemView.context, R.color.bluish)

        when (tasks[position].category){
            TaskCategory.Health -> {
                textColor = ContextCompat.getColor(holder.itemView.context, R.color.blue)
                backColor = ContextCompat.getColor(holder.itemView.context, R.color.bluish)
            }
            TaskCategory.Work -> {
                textColor = ContextCompat.getColor(holder.itemView.context, R.color.green)
                backColor = ContextCompat.getColor(holder.itemView.context, R.color.greenish)
            }
            TaskCategory.Personal -> {
                textColor = ContextCompat.getColor(holder.itemView.context, R.color.pink)
                backColor = ContextCompat.getColor(holder.itemView.context, R.color.pinkish)
            }
            TaskCategory.Other -> {
                textColor = ContextCompat.getColor(holder.itemView.context, R.color.brown)
                backColor = ContextCompat.getColor(holder.itemView.context, R.color.brownish)
            }

        }

        holder.categoryTextView.setTextColor(textColor)
        holder.cardViewCategory.setCardBackgroundColor(backColor)


    }
}