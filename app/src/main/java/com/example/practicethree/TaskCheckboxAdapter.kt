package com.example.practicethree

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskCheckboxAdapter(val tasks: List<String>) : RecyclerView.Adapter<TaskCheckboxAdapter.TaskCheckboxViewHolder>() {

    class TaskCheckboxViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val textView : TextView = itemView.findViewById(R.id.textView10)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskCheckboxViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_checkbox_layout, parent, false)
        return TaskCheckboxViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskCheckboxViewHolder, position: Int) {
        holder.textView.text = tasks[position]
    }

    override fun getItemCount(): Int {
        return tasks.size
    }
}