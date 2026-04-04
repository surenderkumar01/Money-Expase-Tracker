package com.example.moneyexpanse.core.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val label: String, val icon: ImageVector) {
    object Home : BottomNavItem("home","Home", Icons.Default.Home)
    object Expanse : BottomNavItem("Expanse","Expanse", Icons.Default.Add)
    object Transaction : BottomNavItem("transaction","Transaction", Icons.Default.Info)
    object Profile : BottomNavItem("profile","Profile", Icons.Default.Person)
    object HelpCenter : BottomNavItem("HelpCenter","HelpCenter", Icons.Default.Person)
}