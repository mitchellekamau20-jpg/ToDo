package com.example.todo.ui.theme.screens.todo

import androidx.lifecycle.ViewModel
import com.example.todo.data.models.TodoModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class TodoViewModel: ViewModel() {
//    state
private val _activity = MutableStateFlow(TodoModel())
    val activity = _activity.asStateFlow()

    //    "CRUD" - methods
    //create
    fun createActivity(id:Int? =null,
                       createdAt:Long?=null,
                       title:String,
                       description:String,
                       media:String,
                       isComplete: Boolean=false,
                       dueDate: String
    )
    { _activity.value = TodoModel(
            id= id,
            createdAt=createdAt,
            title=title,
            description = description,
            media=media,
            isComplete=isComplete,
            dueDate=dueDate,
        )
    }
    //read

// To "read" a specific item into the state, you'd typically fetch it from a DB,

//    fun setActivity(item: TodoModel) {
//        _activity.value = item
//    }

    // Update
    //to show changes in a certain field

    fun updateActivity(
        title: String? = null,
        description: String? = null,
        isComplete: Boolean? = null,
        media: String?=null

    ) {
        _activity.value = _activity.value.copy(
            title = title ?: _activity.value.title,
            description = description ?: _activity.value.description,
            isComplete = isComplete ?: _activity.value.isComplete,
            media = media ?:_activity.value.media
        )
    }

    // Delete
// This resets the state back to an empty TodoModel instance.
    fun deleteActivity(

    ) {
        _activity.value = TodoModel()
    }


}
