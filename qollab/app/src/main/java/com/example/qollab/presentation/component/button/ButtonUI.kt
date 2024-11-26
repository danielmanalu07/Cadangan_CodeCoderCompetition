package com.example.qollab.presentation.component.button

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.qollab.R

@Composable
fun ButtonUI(
    text: String,
    backgroundColor: Color = colorResource(id = android.R.color.holo_blue_dark),
    textColor: Color = Color.White,
    textStyle: androidx.compose.ui.text.TextStyle = MaterialTheme.typography.titleMedium,
    fontSize: Int = 18,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = textColor,
        ),
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = text,
            fontSize = fontSize.sp,
            style = textStyle,
        )
    }
}

@Preview
@Composable
fun ButtonLogin() {
    ButtonUI(
        text = "Login",
    ) { }
}

@Preview
@Composable
fun ButtonRegister() {
    ButtonUI(
        text = "Register"
    ) { }
}
