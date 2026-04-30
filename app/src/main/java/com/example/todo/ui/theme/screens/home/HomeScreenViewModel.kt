package com.example.todo.ui.theme.screens.home

import androidx.lifecycle.ViewModel
import com.example.todo.data.models.TodoModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeScreenViewModel: ViewModel() {
//    states - data
private val _activity = MutableStateFlow<TodoModel>(TodoModel())
    val activity = _activity.asStateFlow()

    //    "CRUD" - methods
    fun createActivity(id:Int? =null,createdAt:Long?=null,title:String,description:String,media:String,isComplete: Boolean=false,dueDate: String) {
        _activity.value = TodoModel(
            id = id,
            createdAt = createdAt,
            title = title,
            description = description,
            media = media,
            isComplete = isComplete,,
        )
    }





}