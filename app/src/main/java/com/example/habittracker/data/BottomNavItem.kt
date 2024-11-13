package com.example.habittracker.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val label: String, val icon: ImageVector, val route: String) {
    object Home : BottomNavItem("Home", Icons.Filled.Home, "home")
    object Analytic : BottomNavItem("Analytic", Icons.Filled.Person, "analytic")
    object Profile : BottomNavItem("Profile", Icons.Filled.Settings, "profile")
}