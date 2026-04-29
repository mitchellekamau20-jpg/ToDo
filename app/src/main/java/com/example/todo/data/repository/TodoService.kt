package com.example.todo.data.repository

import com.example.todo.data.models.TodoModel

interface TodoService {
    suspend fun createTask(todo: TodoModel):TodoModel? // create task
    suspend fun getAllTasks(): List<TodoModel> // read all tasks
    suspend fun getTask(id:Int): TodoModel? // read one task
    suspend fun updateTask(todo: TodoModel): TodoModel? // update task
    suspend fun deleteTask(id:Int): Boolean // delete task and return true or false based on success
    fun eq(string: String, id: Int)
}
