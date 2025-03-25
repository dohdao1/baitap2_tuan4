package com.example.baitap2_tuan4.ui.theme

import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val viewModel: TaskViewModel = viewModel()

    NavHost(navController, startDestination = "home") {
        composable("home") { TaskListScreen(navController, viewModel) }
        composable("detail/{taskId}") { backStackEntry ->
            val taskId = backStackEntry.arguments?.getString("taskId")?.toInt() ?: 0
            TaskDetailScreen(taskId, navController, viewModel)
        }
    }
}
