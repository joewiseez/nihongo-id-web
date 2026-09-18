package com.example.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Cached
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.outlined.BarChart
import androidx.compose.material.icons.outlined.Cached
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Quiz
import androidx.compose.ui.graphics.vector.ImageVector

enum class NavDestination(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
) {
    HOME("Home", Icons.Filled.Home, Icons.Outlined.Home),
    LEARN("Belajar", Icons.Filled.MenuBook, Icons.Outlined.MenuBook),
    REVIEW("Review", Icons.Filled.Cached, Icons.Outlined.Cached),
    PRACTICE("Latihan", Icons.Filled.Quiz, Icons.Outlined.Quiz),
    PROGRESS("Progres", Icons.Filled.BarChart, Icons.Outlined.BarChart),
    PROFILE("Profil", Icons.Filled.Person, Icons.Outlined.Person)
}
