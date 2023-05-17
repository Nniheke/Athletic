package com.theathletic.interview.articles.ui.composable

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.theathletic.interview.articles.ui.ArticlesViewModel

/**
 * Composable function representing the main screen of the app.
 *
 * @param viewModel The view model used for data operations.
 */
@Composable
fun MainScreenHost(viewModel: ArticlesViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "startDestination"
    ) {
        // Start destination
        composable("startDestination") {
            ArticlesScreen(viewModel, navController)
        }

        // Article page destination
        composable("articlePageDestination/{id}") { backStackEntry ->
            backStackEntry.arguments?.getString("id")?.let { id ->
                ArticlePage(id, viewModel)
            }
        }
    }
}

