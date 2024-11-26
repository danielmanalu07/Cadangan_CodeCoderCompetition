package com.example.qollab.presentation.component.screen

sealed class Screen(val route: String) {
    object Login: Screen("home")
    object Register: Screen("register")
    object Room: Screen("room")
    object Analytics: Screen("analytics")
    object History: Screen("history")
}