package com.example.ui.screens.home

import androidx.compose.foundation.Image
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.local.DailyStudyRecordEntity
import com.example.data.local.UserProfileEntity
import com.example.data.model.VocabularyItem
import com.example.ui.components.JapaneseAudioButton
import com.example.ui.navigation.NavDestination
import com.example.ui.theme.*

@Composable
fun HomeScreen(
    userProfile: UserProfileEntity?,
    dueReviewCount: Int,
    weeklyRecords: List<DailyStudyRecordEntity>,
    dailyLessonVocab: VocabularyItem,
    onNavigateTab: (NavDestination) -> Unit,
    onOpenVocabDetail: (VocabularyItem) -> Unit,
    onSpeak: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(bottom = 24.dp)
    ) {
        // Hero Visual Banner
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(170.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_hero_japan),
                contentDescription = "Pemandangan Jepang",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            // Gradient scrim
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Black.copy(alpha = 0.35f),
                                Color.Black.copy(alpha = 0.75f)
                            )
                        )
                    )
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Bottom
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(JapanCrimson)
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = if (userProfile?.level == "N4") "JLPT N4" else "JLPT N5",
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 10.sp
                            )
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "Jalur Belajar Hari Ini",
                        style = MaterialTheme.typography.labelMedium.copy(
                            color = Color.White.copy(alpha = 0.9f)
                        )
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Langkah Demi Langkah Menuju N5 & N4",
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                )

                Text(
                    text = "Target: ${userProfile?.dailyTargetMinutes ?: 15} menit • ${userProfile?.dailyVocabTarget ?: 10} kosakata per hari",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = Color.White.copy(alpha = 0.85f)
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            // Spaced Repetition (SRS) Review Queue Banner
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onNavigateTab(NavDestination.REVIEW) },
                colors = CardDefaults.cardColors(
                    containerColor = if (dueReviewCount > 0) JapanCrimsonContainer else WashiSurfaceVariant
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
                        Box(
                            modifier = Modifier
                                .size(44.dp)
                                .clip(CircleShape)
                                .background(if (dueReviewCount > 0) JapanCrimson else SumiCharcoal),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Cached,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(24.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Column {
                            Text(
                                text = "Antrean Spaced Repetition",
                                style = MaterialTheme.typography.titleSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = if (dueReviewCount > 0) JapanOnCrimsonContainer else SumiInk
                                )
                            )
                            Text(
                                text = if (dueReviewCount > 0) "$dueReviewCount kartu perlu direview hari ini!" else "Semua kartu telah selesai direview!",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = if (dueReviewCount > 0) JapanCrimson else SumiGray
                                )
                            )
                        }
                    }

                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = "Buka Review",
                        tint = if (dueReviewCount > 0) JapanCrimson else SumiGray
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Daily Japanese Lesson (Pelajaran Hari Ini) Card
            Text(
                text = "Pelajaran Kosakata Hari Ini",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = SumiInk
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onOpenVocabDetail(dailyLessonVocab) },
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                shape = RoundedCornerShape(18.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(JapanCrimsonLight)
                                .padding(horizontal = 8.dp, vertical = 3.dp)
                        ) {
                            Text(
                                text = "${dailyLessonVocab.category} • ${dailyLessonVocab.jlptLevel.displayName}",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = JapanCrimson,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }

                        JapaneseAudioButton(
                            textToSpeak = dailyLessonVocab.japanese,
                            onSpeak = onSpeak,
                            size = 36.dp
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = dailyLessonVocab.furigana,
                        style = MaterialTheme.typography.labelMedium.copy(color = SumiGray)
                    )

                    Text(
                        text = dailyLessonVocab.japanese,
                        style = MaterialTheme.typography.displayMedium.copy(
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            color = SumiInk
                        )
                    )

                    Text(
                        text = dailyLessonVocab.romaji,
                        style = MaterialTheme.typography.bodySmall.copy(color = SumiCharcoal)
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "= ${dailyLessonVocab.meaningId}",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = JapanCrimson
                        )
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Divider(color = WashiBorder.copy(alpha = 0.6f))

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Contoh: \"${dailyLessonVocab.exampleJp}\"",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = SumiCharcoal,
                                fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
                            ),
                            modifier = Modifier.weight(1f)
                        )

                        TextButton(onClick = { onOpenVocabDetail(dailyLessonVocab) }) {
                            Text("Buka Kartu")
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // JLPT N5 & N4 Progress summary
            Text(
                text = "Kemajuan Silabus JLPT",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = SumiInk
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                // N5 Progress Card
                Card(
                    modifier = Modifier
                        .weight(1f)
                        .clickable { onNavigateTab(NavDestination.PROGRESS) },
                    colors = CardDefaults.cardColors(containerColor = WashiSurfaceVariant),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                "JLPT N5",
                                style = MaterialTheme.typography.titleSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = JapanCrimson
                                )
                            )
                            Text("42%", style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold))
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        LinearProgressIndicator(
                            progress = { 0.42f },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(6.dp)
                                .clip(RoundedCornerShape(3.dp)),
                            color = JapanCrimson,
                            trackColor = WashiBorder
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            "Kosakata • Kanji • Tata Bahasa",
                            style = MaterialTheme.typography.labelSmall.copy(color = SumiGray, fontSize = 10.sp)
                        )
                    }
                }

                // N4 Progress Card
                Card(
                    modifier = Modifier
                        .weight(1f)
                        .clickable { onNavigateTab(NavDestination.PROGRESS) },
                    colors = CardDefaults.cardColors(containerColor = WashiSurfaceVariant),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                "JLPT N4",
                                style = MaterialTheme.typography.titleSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = IndigoJlpt
                                )
                            )
                            Text("15%", style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold))
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        LinearProgressIndicator(
                            progress = { 0.15f },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(6.dp)
                                .clip(RoundedCornerShape(3.dp)),
                            color = IndigoJlpt,
                            trackColor = WashiBorder
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            "Lanjutan Pola Menengah",
                            style = MaterialTheme.typography.labelSmall.copy(color = SumiGray, fontSize = 10.sp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Quick Recommended Activities
            Text(
                text = "Rekomendasi Aktivitas Hari Ini",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = SumiInk
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                ActivityRow(
                    title = "Kuis Kilat Kosakata N5",
                    subtitle = "Uji daya ingat 6 kosakata pilihan",
                    icon = Icons.Default.Quiz,
                    iconBg = IndigoJlptLight,
                    iconColor = IndigoJlpt,
                    onClick = { onNavigateTab(NavDestination.PRACTICE) }
                )

                ActivityRow(
                    title = "Hiragana & Katakana Seion",
                    subtitle = "Latihan menulis dan pengenalan bunyi",
                    icon = Icons.Default.Edit,
                    iconBg = JapanCrimsonLight,
                    iconColor = JapanCrimson,
                    onClick = { onNavigateTab(NavDestination.LEARN) }
                )

                ActivityRow(
                    title = "Percakapan: Di Toko Konbini",
                    subtitle = "Latihan menyimak cara pesan bento di minimarket",
                    icon = Icons.Default.ChatBubble,
                    iconBg = MatchaGreenLight,
                    iconColor = MatchaGreen,
                    onClick = { onNavigateTab(NavDestination.LEARN) }
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Weekly Study Statistics
            Text(
                text = "Statistik Belajar Mingguan",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = SumiInk
                )
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
                        Column {
                            Text("Total Pekan Ini", style = MaterialTheme.typography.labelSmall.copy(color = SumiGray))
                            Text(
                                "84 Menit • 38 Kosakata",
                                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold, color = SumiInk)
                            )
                        }
                        Icon(Icons.Default.TrendingUp, contentDescription = null, tint = MatchaGreen)
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // 7-day Bar Visualizer
                    val days = listOf("Sen", "Sel", "Rab", "Kam", "Jum", "Sab", "Min")
                    val mockMinutes = listOf(15, 20, 10, 18, 12, 25, 14)

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        days.forEachIndexed { index, day ->
                            val mins = mockMinutes.getOrElse(index) { 10 }
                            val heightFraction = (mins / 30f).coerceIn(0.15f, 1f)

                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Bottom,
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(
                                    text = "$mins m",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        fontSize = 9.sp,
                                        color = SumiGray
                                    )
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Box(
                                    modifier = Modifier
                                        .width(18.dp)
                                        .fillMaxHeight(heightFraction)
                                        .clip(RoundedCornerShape(topStart = 6.dp, topEnd = 6.dp))
                                        .background(if (index == 6) JapanCrimson else IndigoJlptLight)
                                )
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = day,
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        fontWeight = if (index == 6) FontWeight.Bold else FontWeight.Normal,
                                        color = if (index == 6) JapanCrimson else SumiGray
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ActivityRow(
    title: String,
    subtitle: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    iconBg: Color,
    iconColor: Color,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(14.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(iconBg),
                contentAlignment = Alignment.Center
            ) {
                Icon(imageVector = icon, contentDescription = null, tint = iconColor, modifier = Modifier.size(20.dp))
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(text = title, style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold, color = SumiInk))
                Text(text = subtitle, style = MaterialTheme.typography.bodySmall.copy(color = SumiGray))
            }

            Icon(imageVector = Icons.Default.ChevronRight, contentDescription = null, tint = SumiGray)
        }
    }
}
