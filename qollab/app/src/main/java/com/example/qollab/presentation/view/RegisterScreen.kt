package com.example.qollab.presentation.view

import android.widget.Toast
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.qollab.presentation.component.button.ButtonUI
import com.example.qollab.presentation.component.field.TextFieldUI
import com.example.qollab.presentation.component.screen.Screen
import com.example.qollab.viewModel.AuthVM

@Composable
fun RegisterScreen(navController: NavController, authVM: AuthVM) {
    val context = LocalContext.current

    var startAnimation by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        startAnimation = true
    }

    val fadeInAnimation = animateFloatAsState(targetValue = if (startAnimation) 1f else 0f)
    val slideInAnimation = animateFloatAsState(targetValue = if (startAnimation) 0f else 200f)


    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Welcomeback to",
                    fontWeight = FontWeight.Medium,
                    fontSize = 40.sp,
                    modifier = Modifier
                        .alpha(fadeInAnimation.value)
                        .padding(bottom = 4.dp)
                )

                Text(
                    text = "Qollab",
                    fontWeight = FontWeight.Bold,
                    fontSize = 40.sp,
                    color = colorResource(id = android.R.color.holo_blue_dark),
                    modifier = Modifier
                        .alpha(fadeInAnimation.value)
                        .padding(bottom = 4.dp)
                )

                Text(
                    text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut et massa mi.",
                    fontSize = 20.sp,
                    modifier = Modifier.alpha(fadeInAnimation.value)
                )

                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.offset(y = slideInAnimation.value.dp)
                ) {
                    TextFieldUI(
                        value = authVM.fullname,
                        onValueChange = {
                            authVM.onFullNameChange(it)
                            authVM.validateFullName()
                        },
                        label = "Full Name",
                        keyboardType = KeyboardType.Text,
                        errorMessage = authVM.fullnameError
                    )

                    TextFieldUI(
                        value = authVM.username,
                        onValueChange = {
                            authVM.onUsernameChange(it)
                            authVM.validateUsername()
                        },
                        label = "Username",
                        keyboardType = KeyboardType.Text,
                        errorMessage = authVM.usernameError
                    )

                    TextFieldUI(
                        value = authVM.email,
                        onValueChange = {
                            authVM.onEmailChange(it)
                            authVM.validateEmail()
                        },
                        label = "Email",
                        keyboardType = KeyboardType.Email,
                        errorMessage = authVM.emailError
                    )

                    TextFieldUI(
                        value = authVM.password,
                        onValueChange = {
                            authVM.onPasswordChange(it)
                            authVM.validatePassword()
                        },
                        label = "Password",
                        isPassword = true,
                        keyboardType = KeyboardType.Password,
                        isPasswordVisible = authVM.isPasswordVisible,
                        onPasswordVisibilityToggle = authVM::togglePasswordVisibility,
                        errorMessage = authVM.passwordError
                    )

                    ButtonUI(
                        text = "Register",
                        onClick = {
                            Toast.makeText(context, "Register Success", Toast.LENGTH_SHORT).show()
                            authVM.register(navController)
                        }
                    )
                }



                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "already have account? ",
                        color = Color.Black,
                        fontSize = 14.sp
                    )
                    Text(
                        text = "Login",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable {
                            navController.navigate(Screen.Login.route)
                        },
                        fontSize = 14.sp
                    )
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RegisterScreenPreview() {
    val navController = rememberNavController()
    val authVM = AuthVM(navController)
    RegisterScreen(navController = navController, authVM = authVM)
}