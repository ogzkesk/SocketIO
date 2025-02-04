package com.example.socket.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.socket.navigation.ChatScreenRoute
import com.example.socket.navigation.HomeScreenRoute
import com.example.socket.ui.chat.ChatScreen
import com.example.socket.ui.home.HomeScreen
import com.example.socket.ui.theme.SocketTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            SocketTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = HomeScreenRoute
                ) {
                    composable<HomeScreenRoute> {
                        HomeScreen(navController = navController)
                    }
                    composable<ChatScreenRoute> {
                        ChatScreen(navController = navController)
                    }
                }
            }
        }
    }
}
