package com.example.todo.ui.theme.screens.todo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.data.models.TodoModel
import com.example.todo.data.repository.TodoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TodoViewModel : ViewModel() {
    private val repository = TodoRepository()

    // State for a single activity (e.g., for the creation form or displaying details)
    private val _activity = MutableStateFlow<TodoModel>(TodoModel(dueDate = ""))
    val activity = _activity.asStateFlow()

    // State for the list of todos
    private val _todos = MutableStateFlow<List<TodoModel>>(emptyList())
    val todos = _todos.asStateFlow()

    init {
        getAllTodos()
    }

    // -------------------------
    // ✅ CREATE
    // -------------------------
    fun createActivity(
        id: Int? = null,
        createdAt: Long? = null,
        title: String,
        description: String,
        media: String,
        isComplete: Boolean = false,
        dueDate: String
    ) {
        val newTodo = TodoModel(
            id = id,
            createdAt = createdAt,
            title = title,
            description = description,
            media = media,
            isComplete = isComplete,
            dueDate = dueDate
        )
        
        viewModelScope.launch {
            val created = repository.createTask(newTodo)
            if (created != null) {
                _activity.value = created
                getAllTodos() // Refresh the list
            }
        }
    }

    // -------------------------
    // 📖 READ
    // -------------------------
    fun getAllTodos() {
        viewModelScope.launch {
            try {
                val taskList = repository.getAllTasks()
                _todos.value = taskList
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    fun getTodoById(id: Int) {
        viewModelScope.launch {
            val task = repository.getTask(id)
            if (task != null) {
                _activity.value = task
            }
        }
    }

    // -------------------------
    // ✏️ UPDATE
    // -------------------------
    fun updateTodo(todo: TodoModel) {
        viewModelScope.launch {
            val updated = repository.updateTask(todo)
            if (updated != null) {
                _activity.value = updated
                getAllTodos()
            }
        }
    }

    // -------------------------
    // ❌ DELETE
    // -------------------------
    fun deleteTodo(id: Int) {
        viewModelScope.launch {
            val success = repository.deleteTask(id)
            if (success) {
                getAllTodos()
            }
        }
    }
}
