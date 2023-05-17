package com.theathletic.interview.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.theathletic.interview.R
import com.theathletic.interview.articles.ui.ArticlesViewModel
import com.theathletic.interview.articles.ui.composable.MainScreenHost
import com.theathletic.interview.ui.theme.AthleticTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val articlesViewModel: ArticlesViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AthleticTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    MainScreenView()
                }
            }
        }
    }

    @Composable
    fun MainScreenView() {
        var selectedScreen by remember { mutableStateOf(Screen.Articles as Screen) }
        Scaffold(bottomBar = {
            BottomNavigation(
                selectedScreen,
            ) { screen -> selectedScreen = screen }
        }) { paddingValues ->
            Column(modifier = Modifier.padding(paddingValues)) {
                when (selectedScreen) {
                    Screen.Articles -> {
                        MainScreenHost(articlesViewModel)
                    }
                    Screen.Leagues -> Text(
                        modifier = Modifier
                            .padding(10.dp)
                            .align(Alignment.CenterHorizontally),
                        text = "League List"
                    )
                }
            }
        }
    }

    @Composable
    fun BottomNavigation(selectedScreen: Screen, onScreenSelected: (Screen) -> Unit) {
        val items = listOf(Screen.Articles, Screen.Leagues)
        BottomNavigation {
            items.forEachIndexed { index, item ->
                BottomNavigationItem(selected = item == selectedScreen,
                    icon = {
                        Icon(
                            painterResource(id = item.resourceIcon), contentDescription = getString(item.resourceTitle)
                        )
                    },
                    label = { Text(text = getString(item.resourceTitle), fontSize = 10.sp) },
                    onClick = { onScreenSelected(item) })
            }
        }
    }

    sealed class Screen(
        @StringRes val resourceTitle: Int, @DrawableRes val resourceIcon: Int
    ) {
        object Articles : Screen(R.string.title_articles, R.drawable.ic_articles)
        object Leagues : Screen(R.string.title_leagues, R.drawable.ic_leagues)
    }
}
