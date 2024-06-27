package com.example.easppb.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.easppb.Destinations
import com.example.easppb.R
import com.example.easppb.data.NavigationItem

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { androidx.compose.material3.Text("Home Page", fontWeight = FontWeight.Medium) },
                backgroundColor = Color.White,
                contentColor = Color.Black
            )
        },
        bottomBar = { BottomNavigationBarHome(navController) }
    ) {
        BodyContent()
    }
}

@Composable
fun BodyContent() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)  // Takes as much space as it can
            ) {
                Text(
                    "Good Afternoon, Muhammad",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Normal,
                        fontSize = 18.sp
                    ),
                    color = Color.Black
                )
            }
            Text(
                "Green Level",
                color = Color(0xFF008000),  // A shade of green, adjust hex as needed
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            // Profile Icon
            IconAndText(icon = Icons.Filled.Person, text = "Profile")
            // Inbox Icon
            IconAndText(icon = Icons.Filled.Email, text = "Inbox")
            // E-Code Icon
            IconAndText(icon = Icons.Filled.Notifications, text = "Notifications")
        }
        Spacer(modifier = Modifier.height(20.dp))
        Divider(color = Color.Black, thickness = 2.dp)
        Spacer(modifier = Modifier.height(20.dp))
        RewardsProgressBar()
        Spacer(modifier = Modifier.height(20.dp))
        PromoBanner()
        Spacer(modifier = Modifier.height(20.dp))
        PromoDetailsSection()
        Spacer(modifier = Modifier.height(50.dp))

    }
}

@Composable
fun IconAndText(icon: ImageVector, text: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.clickable { /* Handle click */ }) {
        Icon(
            imageVector = icon,
            contentDescription = text,
            modifier = Modifier.size(24.dp),
            tint = Color.Gray
        )
        Text(text = text, fontSize = 12.sp, color = Color.Gray)
    }
}

@Composable
fun RewardsProgressBar() {
    Text("Star balance", style = MaterialTheme.typography.bodyLarge)
    Slider(value = 0.3f, onValueChange = {}, modifier = Modifier.fillMaxWidth())
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(onClick = { /* TODO */ },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF008000))) {
            Text("Details", color = Color.White)
        }
        Button(onClick = { /* TODO */ },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF008000))) {
            Text("Redeem", color = Color.White)
        }
    }
}

@Composable
fun PromoBanner() {
    val imageList = listOf(
        R.drawable.home,  // Replace these resource IDs with actual drawable resource IDs
        R.drawable.home,
        R.drawable.home,
        R.drawable.home
    )

    LazyRow(
        modifier = Modifier
            .height(100.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(imageList) { image ->
            Image(
                painter = painterResource(id = image),
                contentDescription = "Promotional Banner",
                modifier = Modifier
                    .height(150.dp)
                    .clip(RoundedCornerShape(10.dp)
                )
            )
        }
    }
}

@Composable
fun PromoDetailsSection() {
    Column(modifier = Modifier.padding(16.dp)) {
        PromoBanner()  // Assuming PromoBanner is defined as shown previously
        Spacer(modifier = Modifier.height(16.dp))  // Adds space between the banner and the text
        Text(
            "Explore the Latest Offers",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Justify
        )
    }
}

@Composable
fun BottomNavigationBarHome(navController: NavHostController) {
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
                label = { Text(item.title) },
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

fun currentRouteHome(navController: NavController): String? {
    return navController.currentBackStackEntry?.destination?.route
}