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
    fun createActivity(name: String, description: String) {
        _activity.value = TodoModel(name=name, description = description)
    }






}