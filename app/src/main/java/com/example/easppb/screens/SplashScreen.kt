package com.example.easppb.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.easppb.Destinations
import com.example.easppb.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController){
    val isLoading = remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        delay(2000) // Tunda selama 2 detik
        isLoading.value = false
        navController.navigate(Destinations.Login)
    }

    AnimatedVisibility(
        visible = isLoading.value,
        enter = slideInVertically(initialOffsetY = { -40 }),
        exit = slideOutVertically(targetOffsetY = { -40 })
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.DarkGray),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.logo),
                modifier = Modifier.size(300.dp),
                contentDescription = "Logo"
            )
        }
    }
}