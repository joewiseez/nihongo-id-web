package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JapaneseTopBar(
    streak: Int,
    xp: Int,
    isPremium: Boolean,
    onOpenSearch: () -> Unit,
    onOpenPaywall: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Circular Crimson Torii/Sun emblem
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(JapanCrimson),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "日",
                        style = MaterialTheme.typography.labelLarge.copy(
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    )
                }

                Spacer(modifier = Modifier.width(10.dp))

                Column {
                    Text(
                        text = "Nihongo ID",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = SumiInk
                        )
                    )
                    Text(
                        text = "Belajar Bahasa Jepang",
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = SumiGray,
                            fontSize = 10.sp
                        )
                    )
                }
            }
        },
        actions = {
            // Streak
            StreakChip(streakDays = streak)
            Spacer(modifier = Modifier.width(6.dp))

            // XP
            XpChip(xp = xp)
            Spacer(modifier = Modifier.width(6.dp))

            // Search Icon button
            IconButton(
                onClick = onOpenSearch,
                modifier = Modifier
                    .size(38.dp)
                    .clip(CircleShape)
                    .background(WashiSurfaceVariant)
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Cari Kosakata, Kanji, Tata Bahasa",
                    tint = SumiInk,
                    modifier = Modifier.size(20.dp)
                )
            }

            Spacer(modifier = Modifier.width(6.dp))

            // Premium button or badge
            if (isPremium) {
                PremiumBadge()
            } else {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(JapanCrimsonContainer)
                        .clickable { onOpenPaywall() }
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.WorkspacePremium,
                            contentDescription = "Upgrade",
                            tint = JapanCrimson,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(2.dp))
                        Text(
                            text = "PRO",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = JapanCrimson,
                                fontSize = 11.sp
                            )
                        )
                    }
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    )
}
