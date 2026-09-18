package com.example.ui.screens.progress

import androidx.compose.animation.*
import androidx.compose.foundation.background
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
import com.example.data.model.AchievementBadge
import com.example.data.model.DailyMission
import com.example.data.repository.JapaneseContentRepository
import com.example.ui.theme.*

@Composable
fun ProgressScreen(
    userProfile: UserProfileEntity?,
    onClaimMission: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    var missions by remember {
        mutableStateOf<List<DailyMission>>(JapaneseContentRepository.getDefaultDailyMissions())
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        Text(
            text = "Progres & Pencapaian",
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                color = SumiInk
            )
        )
        Text(
            text = "Pantau kesiapan Anda menuju target ujian JLPT N5 & N4.",
            style = MaterialTheme.typography.bodySmall.copy(color = SumiGray),
            modifier = Modifier.padding(top = 4.dp, bottom = 16.dp)
        )

        // JLPT Readiness Cards
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Kesiapan JLPT N5",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = SumiInk
                            )
                        )
                        Text(
                            text = "Level Pemula Standar",
                            style = MaterialTheme.typography.bodySmall.copy(color = SumiGray)
                        )
                    }

                    Box(
                        modifier = Modifier
                            .size(56.dp)
                            .clip(CircleShape)
                            .background(JapanCrimsonLight),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "68%",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = JapanCrimson
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Breakdown of N5 skills
                BreakdownRow(label = "Hiragana & Katakana", progress = 0.96f, valueLabel = "96% (Kuasai Penuh)")
                Spacer(modifier = Modifier.height(8.dp))
                BreakdownRow(label = "Kosakata N5 (Goi)", progress = 0.65f, valueLabel = "120 / 185 Kata")
                Spacer(modifier = Modifier.height(8.dp))
                BreakdownRow(label = "Kanji N5", progress = 0.50f, valueLabel = "25 / 50 Kanji")
                Spacer(modifier = Modifier.height(8.dp))
                BreakdownRow(label = "Tata Bahasa (Bunpou)", progress = 0.60f, valueLabel = "9 / 15 Pola Kalimat")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // JLPT N4 Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Kesiapan JLPT N4",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = SumiInk
                            )
                        )
                        Text(
                            text = "Level Pra-Menengah",
                            style = MaterialTheme.typography.bodySmall.copy(color = SumiGray)
                        )
                    }

                    Box(
                        modifier = Modifier
                            .size(56.dp)
                            .clip(CircleShape)
                            .background(IndigoJlptLight),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "22%",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = IndigoJlpt
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))
                LinearProgressIndicator(
                    progress = { 0.22f },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .clip(RoundedCornerShape(4.dp)),
                    color = IndigoJlpt,
                    trackColor = WashiBorder
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Daily Missions Section
        Text(
            text = "Misi Harian (Quest)",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                color = SumiInk
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        missions.forEachIndexed { index, mission ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                shape = RoundedCornerShape(14.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(if (mission.isCompleted) MatchaGreenLight else IndigoJlptLight),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = if (mission.isCompleted) Icons.Default.CheckCircle else Icons.Default.Flag,
                            contentDescription = null,
                            tint = if (mission.isCompleted) MatchaGreen else IndigoJlpt,
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = mission.title,
                            style = MaterialTheme.typography.titleSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = SumiInk
                            )
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "${mission.currentCount} / ${mission.targetCount} • +${mission.xpReward} XP",
                            style = MaterialTheme.typography.bodySmall.copy(color = SumiGray)
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        LinearProgressIndicator(
                            progress = { (mission.currentCount.toFloat() / mission.targetCount.toFloat()).coerceIn(0f, 1f) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(4.dp)
                                .clip(RoundedCornerShape(2.dp)),
                            color = if (mission.isCompleted) MatchaGreen else JapanCrimson,
                            trackColor = WashiBorder
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    if (mission.isCompleted) {
                        Button(
                            onClick = {
                                onClaimMission(mission.xpReward)
                                missions = missions.toMutableList().also {
                                    it[index] = mission.copy(currentCount = 0, isCompleted = false)
                                }
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = MatchaGreen),
                            shape = RoundedCornerShape(10.dp),
                            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp)
                        ) {
                            Text("Klaim", style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold))
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Achievement Badges Section
        Text(
            text = "Lencana Pencapaian",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                color = SumiInk
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        val badges = remember {
            listOf(
                AchievementBadge("b1", "Langkah Pertama", "Menyelesaikan pelajaran pertama", "初", true),
                AchievementBadge("b2", "Master Hiragana", "Menguasai 46 huruf dasar Hiragana", "あ", true),
                AchievementBadge("b3", "Streak 3 Hari", "Belajar 3 hari berturut-turut", "火", true),
                AchievementBadge("b4", "Penghafal 50 Kanji", "Menghafal 50 kanji dasar JLPT N5", "漢", false),
                AchievementBadge("b5", "Pendengar Ulung", "Tuntaskan 10 latihan audio", "耳", false),
                AchievementBadge("b6", "Penakluk JLPT N5", "Lulus simulasi ujian N5", "勝", false)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            badges.take(3).forEach { badge ->
                BadgeCard(badge = badge, modifier = Modifier.weight(1f))
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            badges.drop(3).take(3).forEach { badge ->
                BadgeCard(badge = badge, modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
fun BreakdownRow(label: String, progress: Float, valueLabel: String) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(label, style = MaterialTheme.typography.bodySmall.copy(color = SumiInk, fontWeight = FontWeight.Medium))
            Text(valueLabel, style = MaterialTheme.typography.labelSmall.copy(color = SumiGray))
        }
        Spacer(modifier = Modifier.height(4.dp))
        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier
                .fillMaxWidth()
                .height(6.dp)
                .clip(RoundedCornerShape(3.dp)),
            color = if (progress >= 0.8f) MatchaGreen else JapanCrimson,
            trackColor = WashiBorder
        )
    }
}

@Composable
fun BadgeCard(badge: AchievementBadge, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = if (badge.isUnlocked) WashiSurfaceVariant else Color(0xFFF3F3F5)
        ),
        shape = RoundedCornerShape(14.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(if (badge.isUnlocked) JapanCrimson else Color(0xFFCCCCCC)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = badge.iconKanji,
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = badge.title,
                style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = if (badge.isUnlocked) SumiInk else SumiLightGray,
                    fontSize = 11.sp
                ),
                maxLines = 1
            )

            Text(
                text = badge.description,
                style = MaterialTheme.typography.labelSmall.copy(
                    color = SumiLightGray,
                    fontSize = 9.sp
                ),
                maxLines = 2
            )
        }
    }
}
