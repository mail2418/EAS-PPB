package com.example.easppb.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.easppb.Destinations
import com.example.easppb.R
import com.example.easppb.data.NavigationItem


@Composable
fun CardScreen(navController: NavHostController) {
    var selectedTab by remember { mutableStateOf("PAY") }  // Default to "PAY"

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Starbucks Card", fontWeight = FontWeight.Medium) },
                backgroundColor = Color.White,
                contentColor = Color.Black
            )
        },
        bottomBar = { BottomNavigationBarCard(navController) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(Color.White)
        ) {
            TopNavigationBarCard(selectedTab, onTabSelected = { newTab -> selectedTab = newTab })
        }
    }
}
@Composable
fun BodyContentCard(modifier: Modifier) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text("Your account balance", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text("Active", color = Color.DarkGray, fontSize = 14.sp, style = MaterialTheme.typography.headlineMedium)
        }
        Spacer(modifier = Modifier.width(18.dp))
        BalanceAndStatus(balance = "Rp. 120.000")
        TopUpButton()
        Spacer(modifier = Modifier.height(25.dp))
        VirtualStarbucksCard()
        ManageCardButton()

    }
}

@Composable
fun BalanceAndStatus(balance: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(balance, style = MaterialTheme.typography.headlineMedium)
        Row {
            Spacer(modifier = Modifier.width(4.dp))
            Image(painter = painterResource(id = R.drawable.card_icon), contentDescription = "Active Status")
        }
    }
}

@Composable
fun TopUpButton() {
    Button(
        onClick = { /* TODO: Implement top-up logic */ },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
    ) {
        Text("TOP UP", color=Color.White)
    }
}

@Composable
fun VirtualStarbucksCard() {
    Image(
        painter = painterResource(id = R.drawable.card_icon),  // Assume you have a drawable resource
        contentDescription = "Starbucks Card",
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    )
}

@Composable
fun ManageCardButton() {
    Button(
        onClick = { /* TODO: Implement manage card logic */ },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF008000))
    ) {
        Text("Manage", color = Color.White)
    }
}

@Composable
fun BottomNavigationBarCard(navController: NavHostController) {
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

@Composable
fun TopNavigationBarCard(selectedTab: String, onTabSelected: (String) -> Unit) {
    StarbucksCardTopNav(selectedTab = selectedTab, onTabSelected = onTabSelected)
    when (selectedTab) {
        "PAY" -> PayContent()
        "ADD" -> AddContent()
        "VIRTUAL ACCOUNT" -> VirtualAccountContent()
    }
}

@Composable
fun StarbucksCardTopNav(
    selectedTab: String,
    onTabSelected: (String) -> Unit  // This function will be called when a tab is selected
) {
    val options = listOf("PAY", "ADD", "VIRTUAL ACCOUNT")

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        options.forEach { option ->
            Text(
                text = option,
                color = if (selectedTab == option) Color(0xFF008000) else Color.Black,
                modifier = Modifier
                    .clickable { onTabSelected(option) }
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                fontSize = 16.sp,
                fontWeight = if (selectedTab == option) androidx.compose.ui.text.font.FontWeight.Bold else androidx.compose.ui.text.font.FontWeight.Normal
            )
        }
    }
}

// Example placeholder composables for tab content
@Composable
fun PayContent() {
    Text("Content for PAY", modifier = Modifier.padding(16.dp))
    BodyContentCard(Modifier.padding(16.dp))
}

@Composable
fun AddContent() {
    Text("Content for ADD", modifier = Modifier.padding(16.dp))
}

@Composable
fun VirtualAccountContent() {
    Text("Content for VIRTUAL ACCOUNT", modifier = Modifier.padding(16.dp))
}

fun currentRouteCard(navController: NavHostController): String? {
    return navController.currentBackStackEntry?.destination?.route
}
