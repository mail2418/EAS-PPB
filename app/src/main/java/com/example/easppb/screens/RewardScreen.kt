package com.example.easppb.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Slider
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.easppb.Destinations
import com.example.easppb.R
import com.example.easppb.data.NavigationItem

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RewardScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Starbucks Rewards", fontWeight = FontWeight.Medium) },
                backgroundColor = Color.White,
                contentColor = Color.Black
            )
        },
        bottomBar = { BottomNavigationBarReward(navController) } // Assuming you have a BottomNavigationBar Composable
    ) {
        LazyColumn(
            modifier = Modifier.padding(it).padding(16.dp)
        ) {
            item { RewardsProgress() }
            item { MembershipStatus() }
            item { RewardDetailsButton() }
        }
    }
}

@Composable
fun RewardsProgress() {
    Column {
        Text("Rewards Progress", style = MaterialTheme.typography.titleMedium)
        Slider(value = 0.3f, onValueChange = {}, modifier = Modifier.padding(vertical = 16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Star balance")
            Text("30 stars to next reward")
        }
    }
}

@Composable
fun MembershipStatus() {
    Column(modifier = Modifier.padding(vertical = 16.dp)) {
        Text("Membership Status", style = MaterialTheme.typography.titleMedium)
        Text("Green Level", fontWeight = FontWeight.Bold, color = Color.DarkGray)
        Text("300 Stars to Gold Tier")
        Text("Member Since 26 June 2024")
    }
}

@Composable
fun RewardDetailsButton() {
    Button(
        onClick = { /* Navigate to detailed rewards */ },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors= ButtonDefaults.buttonColors(backgroundColor = Color(0xFF008000))
    ) {
        Text("More about your reward benefits", color=Color.White)
    }
}


@Composable
fun BottomNavigationBarReward(navController: NavHostController) {
    val items = listOf(
        NavigationItem(Destinations.Home, R.drawable.home_icon, "Home"),  // Ensure you have 'home_icon' in drawable
        NavigationItem(Destinations.Card, R.drawable.card_icon, "Card"),  // Ensure you have 'card_icon' in drawable
        NavigationItem(Destinations.Reward, R.drawable.reward_icon, "Reward")  // Ensure you have 'reward_icon' in drawable
    )
    BottomNavigation(
        backgroundColor = Color.White,
        contentColor = Color.Black
    ) {
        val currentRoute = currentRouteHome(navController)
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.title, modifier = Modifier.size(20.dp)) },
                label = { androidx.compose.material.Text(item.title) },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationRoute!!) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                alwaysShowLabel = true
            )
        }
    }
}

fun currentRouteReward(navController: NavController): String? {
    return navController.currentBackStackEntry?.destination?.route
}
