package com.example.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.local.UserProfileEntity
import com.example.ui.theme.*

@Composable
fun ProfileScreen(
    userProfile: UserProfileEntity?,
    isSlowSpeechRate: Boolean,
    onToggleSlowSpeech: () -> Unit,
    onOpenPaywall: () -> Unit,
    onRestartOnboarding: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        // User Profile Header Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                        .background(JapanCrimson),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "学",
                        style = MaterialTheme.typography.headlineMedium.copy(
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Pelajar Bahasa Jepang",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = SumiInk
                        )
                    )
                    Text(
                        text = "Target: ${if (userProfile?.goal == "JLPT") "Lulus JLPT" else if (userProfile?.goal == "WORK") "Bekerja di Jepang" else "Percakapan Praktis"}",
                        style = MaterialTheme.typography.bodySmall.copy(color = JapanCrimson)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Level: ${userProfile?.level ?: "BEGINNER"} • Streak: ${userProfile?.currentStreak ?: 0} Hari",
                        style = MaterialTheme.typography.labelSmall.copy(color = SumiGray)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Subscription Status Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onOpenPaywall() },
            colors = CardDefaults.cardColors(
                containerColor = if (userProfile?.isPremium == true) JapanCrimsonLight else WashiSurfaceVariant
            ),
            shape = RoundedCornerShape(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.WorkspacePremium,
                        contentDescription = null,
                        tint = JapanCrimson,
                        modifier = Modifier.size(28.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = if (userProfile?.isPremium == true) "Paket NihongoPro Aktif" else "Tingkatkan ke NihongoPro",
                            style = MaterialTheme.typography.titleSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = SumiInk
                            )
                        )
                        Text(
                            text = if (userProfile?.isPremium == true) "Akses penuh N5, N4, offline & tanpa batas" else "Akses semua bab JLPT N4 dan audio menyimak",
                            style = MaterialTheme.typography.bodySmall.copy(color = SumiGray)
                        )
                    }
                }

                Button(
                    onClick = onOpenPaywall,
                    colors = ButtonDefaults.buttonColors(containerColor = JapanCrimson),
                    shape = RoundedCornerShape(10.dp),
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = if (userProfile?.isPremium == true) "Kelola" else "Upgrade",
                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Learning Targets Section
        Text(
            text = "Target Belajar",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(8.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                ProfileSettingRow(
                    icon = Icons.Default.Timer,
                    title = "Target Waktu Harian",
                    value = "${userProfile?.dailyTargetMinutes ?: 15} Menit"
                )
                Divider(color = WashiBorder.copy(alpha = 0.5f), modifier = Modifier.padding(vertical = 8.dp))
                ProfileSettingRow(
                    icon = Icons.Default.MenuBook,
                    title = "Target Kosakata Harian",
                    value = "${userProfile?.dailyVocabTarget ?: 10} Kata"
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Preferences & Audio Settings
        Text(
            text = "Pengaturan Audio & Pelafalan",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(8.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Speed,
                            contentDescription = null,
                            tint = SumiInk
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                text = "Kecepatan Pengucapan (TTS)",
                                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
                            )
                            Text(
                                text = if (isSlowSpeechRate) "Mode Lambat (0.75x) untuk pemula" else "Kecepatan Normal (1.0x)",
                                style = MaterialTheme.typography.bodySmall.copy(color = SumiGray)
                            )
                        }
                    }

                    Switch(
                        checked = isSlowSpeechRate,
                        onCheckedChange = { onToggleSlowSpeech() },
                        colors = SwitchDefaults.colors(checkedThumbColor = JapanCrimson, checkedTrackColor = JapanCrimsonLight)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Offline & Data
        Text(
            text = "Penyimpanan & Offline",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(8.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                ProfileSettingRow(
                    icon = Icons.Default.CloudDone,
                    title = "Database Lokal Offline",
                    value = "Tersimpan (Room DB)"
                )
                Divider(color = WashiBorder.copy(alpha = 0.5f), modifier = Modifier.padding(vertical = 8.dp))
                ProfileSettingRow(
                    icon = Icons.Default.Download,
                    title = "Paket Kosakata N5 & N4",
                    value = "Tersedia Offline"
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Reset Onboarding / Reconfigure
        OutlinedButton(
            onClick = onRestartOnboarding,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Icon(Icons.Default.Refresh, contentDescription = null, modifier = Modifier.size(18.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text("Ubah Preferensi & Onboarding Ulang")
        }
    }
}

@Composable
fun ProfileSettingRow(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    value: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = icon, contentDescription = null, tint = SumiInk, modifier = Modifier.size(20.dp))
            Spacer(modifier = Modifier.width(12.dp))
            Text(text = title, style = MaterialTheme.typography.bodyMedium.copy(color = SumiInk))
        }
        Text(text = value, style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold, color = JapanCrimson))
    }
}
