package com.example.easppb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.easppb.screens.CardScreen
import com.example.easppb.screens.HomeScreen
import com.example.easppb.screens.Login
import com.example.easppb.screens.Register
import com.example.easppb.screens.RewardScreen
import com.example.easppb.screens.SplashScreen
import com.example.easppb.ui.theme.EASPPBTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EASPPBTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Destinations.Splash) {
                    composable(Destinations.Home) { HomeScreen(navController) }
                    composable(Destinations.Card) { CardScreen(navController) }
                    composable(Destinations.Reward) { RewardScreen(navController) }
                    composable(Destinations.Splash) { SplashScreen(navController) }
                    composable(Destinations.Login) { Login(navController) }
                    composable(Destinations.Register) { Register(navController) }

                }
            }
        }
    }
}