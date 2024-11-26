package com.example.qollab.presentation.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.qollab.presentation.bottomNav.BotomAppBarUI

@Composable
fun RoomScreen(navController: NavController) {
    Scaffold(
        bottomBar = {
            BotomAppBarUI(navController = navController)
        }
    ) {paddingValues ->
        Box(
            modifier = Modifier.fillMaxSize().padding(paddingValues)
        )
    }
}