package com.example.todo.data.repository

import com.example.todo.data.models.TodoModel
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.storage.Storage


class TodoRepository : TodoService {
    val supabase = createSupabaseClient(
        supabaseUrl = "https://wjurzdckpleigosiduth.supabase.co/rest/v1/",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6IndqdXJ6ZGNrcGxlaWdvc2lkdXRoIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NzczMzAwMzUsImV4cCI6MjA5MjkwNjAzNX0.vK8_al4kT-ceM6ROr424rSH8QLK7A90cUkhEatcL7N8"
    ) {
        install(Postgrest)
        install(Storage)
        install(Auth)
    }

    override suspend fun createTask(todo: TodoModel): TodoModel? {
        val task = supabase.from("tasks").insert(todo) {
            select()
        }.decodeSingle<TodoModel>()
        return task
    }

    override suspend fun getAllTasks(): List<TodoModel> {
        val tasks = supabase.from("tasks").select().decodeList<TodoModel>()
        return tasks
    }

    override suspend fun getTask(id: Int): TodoModel? {
        val todo = supabase.from("tasks").select() {
            filter {
                TodoModel::id eq id
            }
        }.decodeAsOrNull<TodoModel>()
        return todo
    }

    override suspend fun updateTask(todo: TodoModel): TodoModel? {
        val todo = supabase.from("tasks").update(
            todo
        ) {
            select()
            filter {
                eq("id", todo.id!!)
            }
        }.decodeSingle<TodoModel>()
        return todo
    }

    override suspend fun deleteTask(id: Int): Boolean {
        supabase.from("tasks").delete {
            filter {
                eq("id", id)
            }
        }
        return getTask(id) == null
    }
    override fun eq(string: String, id: Int) {
        // Implementation
    }

}

