package com.example.qollab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.qollab.presentation.component.screen.Screen
import com.example.qollab.presentation.view.AnalyticsScreen
import com.example.qollab.presentation.view.HistoryScreen
import com.example.qollab.presentation.view.LoginScreen
import com.example.qollab.presentation.view.RegisterScreen
import com.example.qollab.presentation.view.RoomScreen
import com.example.qollab.ui.theme.QollabTheme
import com.example.qollab.utils.AuthUtils
import com.example.qollab.viewModel.AuthVM

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        installSplashScreen()
        setContent {
            QollabTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    MainNavigation()
                }
            }
        }
    }

    private val authUtils by lazy { AuthUtils(this) }


    @Composable
    fun MainNavigation() {
        val navController = rememberNavController()
        val startPage = if (authUtils.isLoggedIn()){
            Screen.Room.route
        } else {
            Screen.Login.route
        }

        NavHost(
            navController = navController,
            startDestination = startPage
        ) {
            composable(Screen.Login.route){
                val authVM = AuthVM(navController)
                LoginScreen(navController = navController, authVM =  authVM)
            }

            composable(Screen.Register.route){
                val authVM = AuthVM(navController)
                RegisterScreen(navController = navController, authVM = authVM)
            }

            composable(Screen.Room.route){
                RoomScreen(navController = navController)
            }
            composable(Screen.Analytics.route){
                AnalyticsScreen(navController = navController)
            }

            composable(Screen.History.route){
                HistoryScreen(navController = navController)
            }
        }
    }
}