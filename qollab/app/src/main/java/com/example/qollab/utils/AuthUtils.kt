package com.example.qollab.utils

import android.content.Context

class AuthUtils(private val context: Context) {
    private val sharedPreferences = context.getSharedPreferences("login", Context.MODE_PRIVATE)

    fun isLoggedIn(): Boolean{
        return sharedPreferences.getBoolean("complete", false)
    }

    fun setLoggedIn(){
        return sharedPreferences.edit()
            .putBoolean("complete", true)
            .apply()
    }
}