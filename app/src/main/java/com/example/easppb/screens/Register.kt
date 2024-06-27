package com.example.easppb.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.easppb.Destinations
import com.example.easppb.R
import com.example.easppb.service.Authentication

@Composable
fun Register(navController: NavHostController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    // Include other fields as necessary

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.background), // Use your own image
            contentDescription = "Background",
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(36.dp)
                .alpha(0.85f)
                .align(Alignment.Center),
            elevation = 16.dp,
        ) {
            Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
                Spacer(modifier = Modifier.height(50.dp))
                Text("Sign Up", style = MaterialTheme.typography.h4)
                Spacer(modifier = Modifier.height(20.dp))
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo",
                    modifier = Modifier.size(200.dp)  // Logo size
                )
                Spacer(modifier = Modifier.height(20.dp))
                // Implement fields like in the screenshot
                OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") }, )
                OutlinedTextField(value = password, onValueChange = { password = it }, label = { Text("Password") },visualTransformation = PasswordVisualTransformation())
                OutlinedTextField(value = confirmPassword, onValueChange = { confirmPassword = it }, label = { Text("Confirm Password") },visualTransformation = PasswordVisualTransformation())
                Spacer(modifier = Modifier.height(20.dp))
                Button(onClick = {
                    if (password == confirmPassword) {
                        Authentication.registerUser(email, password)
                        navController.navigate(Destinations.Login)
                    }
                }, colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF008000))) {
                    Text("Sign Up",color = Color.White)
                }
                Spacer(modifier = Modifier.height(10.dp))
                TextButton(onClick = {navController.navigate(Destinations.Login)}) {
                    Text("Already Have Account",color = Color.DarkGray)
                }
            }
        }
    }
}