package com.example.todo.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.ui.model.Todo
import com.example.todo.ui.model.TodosApi
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateListOf
import android.util.Log

class TodoViewModel : ViewModel() {

    var todos = mutableStateListOf<Todo>()
        private set

    init {
        getTodosList()
    }

    private fun getTodosList() {
        viewModelScope.launch {
            var todosApi: TodosApi? = null
            try {
                todosApi = TodosApi.getInstance()

                todos.clear()

                todos.addAll(todosApi.getTodos())
            } catch (e: Exception) {
                Log.d("TODOVIEWMODEL", e.message.toString()) // Log any exceptions
            }
        }
    }
}
