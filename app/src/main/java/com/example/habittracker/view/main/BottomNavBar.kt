package com.example.habittracker.view.main

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.habittracker.data.BottomNavItem

@Composable
fun BottomNavBar(navController: NavHostController,modifier: Modifier) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Analytic,
        BottomNavItem.Profile
    )

    BottomNavigation (
        backgroundColor = Color.White,
        contentColor = Color.Black
    ){
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
        items.forEach { item->
            BottomNavigationItem(
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}