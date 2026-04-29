package com.example.todo.data.models

import kotlinx.serialization.Serializable

@Serializable
data class TodoModel(
    val id: Int? = null,// will be used a Primary key in supabase
    val createdAt: Long? = null, // wil be automatically set to now() everytime we do a creation
    val title: String = "",
    val description: String = "",
    val media: String = "", //  store images or video
    val isComplete: Boolean = false, // default to false
    val dueDate: String = ""// store as unix timestamp
)

