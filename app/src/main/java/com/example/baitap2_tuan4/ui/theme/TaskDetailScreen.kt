package com.example.baitap2_tuan4.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material.icons.filled.ArrowBack
import androidx.navigation.NavController

@Composable
fun TaskDetailScreen(taskId: Int, navController: NavController, viewModel: TaskViewModel = viewModel()) {
    val task = remember { mutableStateOf<Task?>(null) }

    LaunchedEffect(taskId) {
        task.value = RetrofitInstance.api.getTaskDetail(taskId)
    }

    Scaffold(topBar = {
        TopAppBar(title = { Text("Task Detail") }, navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
        })
    }) {dao ->
        task.value?.let { task ->
            Column(modifier = Modifier.padding(dao).padding(16.dp)) {
                Text(task.ten, style = MaterialTheme.typography.h5)
                Text(task.mota, style = MaterialTheme.typography.body1)
                Text("Status: ${task.trangthai}", style = MaterialTheme.typography.body2)
            }
        } ?: run {
            CircularProgressIndicator(modifier = Modifier.fillMaxSize())
        }
    }
}

