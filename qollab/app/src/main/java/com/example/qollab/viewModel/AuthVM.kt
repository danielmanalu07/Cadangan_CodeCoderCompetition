package com.example.qollab.viewModel

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.qollab.presentation.component.screen.Screen
import com.example.qollab.utils.AuthUtils
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthVM(navController: NavController) : ViewModel() {

    var fullname by  mutableStateOf("")
    var fullnameError by mutableStateOf<String?>(null)
    var username by  mutableStateOf("")
    var usernameError by mutableStateOf<String?>(null)
    var email by  mutableStateOf("")
    var emailError by mutableStateOf<String?>(null)
    var password by mutableStateOf("")
    var passwordError by mutableStateOf<String?>(null)
    var isPasswordVisible by mutableStateOf(false)


    fun isValidEmail(): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun onFullNameChange(input_fullname: String){
        fullname = input_fullname
    }

    fun onUsernameChange(input_username: String){
        username = input_username
    }

    fun onEmailChange(input_email: String){
        email = input_email
    }

    fun onPasswordChange(input_password: String){
        password = input_password
    }

    fun togglePasswordVisibility() {
        isPasswordVisible = !isPasswordVisible
    }


    fun validateFullName(){
        fullnameError = when{
            fullname.isEmpty() -> "Full Name is required"
            else -> null
        }
    }

    fun validateUsername(){
        usernameError = when{
            username.isEmpty() -> "Username is required"
            else -> null
        }
    }

    fun validateEmail(){
        emailError = when{
            email.isEmpty() -> "Email is required"
            !isValidEmail() -> "Invalid email format"
            else -> null
        }
    }

    fun validatePassword(){
        passwordError = when{
            password.isEmpty() -> "Password is required"
            password.length < 6 -> "Password must be 6 character"
            else -> null
        }
    }

    fun validateFieldRegister(): Boolean{
        validateUsername()
        validateEmail()
        validateFullName()
        validatePassword()

        return listOf(
            usernameError,
            emailError,
            fullnameError,
            passwordError
        ).all { it == null }
    }

    fun validateFieldLogin(): Boolean{
        validateEmail()
        validatePassword()

        return listOf(
            emailError,
            passwordError
        ).all { it == null }
    }

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> get() = _authState

    fun register(navController: NavController){
        _authState.value = AuthState.Loading
        viewModelScope.launch {
            if(!validateFieldRegister()){
                _authState.value = AuthState.Error("Field is required")
                return@launch
            }
            delay(1000)
            navController.navigate(Screen.Login.route){
                popUpTo(Screen.Register.route) { inclusive = true}
            }
        }
    }

    fun login(navController: NavController){
        _authState.value = AuthState.Loading
        viewModelScope.launch {
            if (!validateFieldLogin()){
                _authState.value = AuthState.Error("Field is Required")
                return@launch
            }
            delay(1000)
            navController.navigate(Screen.Room.route){
                popUpTo(0) {inclusive = true}
            }
        }
    }
}

sealed class AuthState(){
    object Idle: AuthState()
    object Loading: AuthState()
    data class Success (val msg: String): AuthState()
    data class Error (val msg: String): AuthState()
}