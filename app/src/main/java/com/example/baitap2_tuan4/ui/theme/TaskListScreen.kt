package com.example.baitap2_tuan4.ui.theme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete

@Composable
fun TaskListScreen(navController: NavController, viewModel: TaskViewModel = viewModel()) {
    val tasks by viewModel.tasks.collectAsState()

    Scaffold(topBar = {
        TopAppBar(title = { Text("SmartTasks") })
    }) {dao ->
        LazyColumn(modifier = Modifier.padding(dao).padding(16.dp)) {
            items(tasks) { task ->
                TaskItem(task, onClick = { navController.navigate("detail/${task.id}") }, onDelete = { viewModel.deleteTask(task.id) })
            }
        }
    }
}

@Composable
fun TaskItem(task: Task, onClick: () -> Unit, onDelete: () -> Unit) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .clickable { onClick() },
        elevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(task.ten, style = MaterialTheme.typography.h6)
            Text(task.mota, style = MaterialTheme.typography.body2)
            Row {
                Text("Status: ${task.trangthai}", style = MaterialTheme.typography.body2)
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = onDelete) {
                    Icon(Icons.Default.Delete, contentDescription = "Delete Task")
                }
            }
        }
    }
}
