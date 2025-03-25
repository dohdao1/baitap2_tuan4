package com.example.baitap2_tuan4.ui.theme

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit

class TaskViewModel : ViewModel(){
    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks : StateFlow<List<Task>> = _tasks

    init {
        fetchTask()
    }
    private fun fetchTask(){
        viewModelScope.launch {
            try {
                _tasks.value = RetrofitInstance.api.getTask()
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
    fun deleteTask(id: Int){
        viewModelScope.launch {
            try {
                RetrofitInstance.api.deleteTask(id)
                _tasks.value = _tasks.value.filter { it.id != id}
            }catch (e : Exception){
                e.printStackTrace()
            }
        }
    }
}