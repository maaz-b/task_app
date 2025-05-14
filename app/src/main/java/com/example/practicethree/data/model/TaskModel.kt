package com.example.practicethree.data.model

import java.time.LocalDate

data class TaskModel(
    val name: String,
    val done: Boolean,
    val date: LocalDate,
    val category: TaskCategory
)