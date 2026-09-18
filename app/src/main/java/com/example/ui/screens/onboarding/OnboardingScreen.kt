package com.example.ui.screens.onboarding

import androidx.compose.animation.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.ui.theme.*

@Composable
fun OnboardingScreen(
    onComplete: (level: String, goal: String, minutes: Int, vocabTarget: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var step by remember { mutableIntStateOf(1) } // 1: Level, 2: Goal, 3: Target, 4: Personalized Plan Result

    var selectedLevel by remember { mutableStateOf("BEGINNER") } // BEGINNER, N5, N4
    var selectedGoal by remember { mutableStateOf("JLPT") } // JLPT, CONVERSATION, WORK, TRAVEL
    var selectedMinutes by remember { mutableIntStateOf(15) } // 10, 20, 30
    var selectedVocabTarget by remember { mutableIntStateOf(10) } // 10, 20, 30

    val scrollState = rememberScrollState()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            Surface(
                color = MaterialTheme.colorScheme.surface,
                tonalElevation = 8.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .windowInsetsPadding(WindowInsets.navigationBars)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (step > 1 && step < 4) {
                        OutlinedButton(
                            onClick = { step-- },
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text("Kembali")
                        }
                    } else {
                        Spacer(modifier = Modifier.width(1.dp))
                    }

                    Button(
                        onClick = {
                            if (step < 4) {
                                step++
                            } else {
                                onComplete(selectedLevel, selectedGoal, selectedMinutes, selectedVocabTarget)
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = JapanCrimson),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.height(48.dp)
                    ) {
                        Text(
                            text = if (step == 4) "Mulai Belajar Sekarang" else "Lanjutkan",
                            style = MaterialTheme.typography.labelLarge.copy(
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(scrollState)
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header with App Icon/Illustration
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(JapanCrimsonLight),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_hero_japan),
                    contentDescription = "Hero Jepang",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.35f))
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "ようこそ！ Selamat Datang",
                        style = MaterialTheme.typography.titleMedium.copy(
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        text = "Aplikasi Belajar Bahasa Jepang untuk Penutur Indonesia",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = Color.White.copy(alpha = 0.9f),
                            textAlign = TextAlign.Center
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Step Progress Indicator
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                (1..4).forEach { i ->
                    val isActive = i <= step
                    Box(
                        modifier = Modifier
                            .height(6.dp)
                            .weight(1f)
                            .padding(horizontal = 3.dp)
                            .clip(RoundedCornerShape(3.dp))
                            .background(if (isActive) JapanCrimson else WashiBorder)
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Step Content
            AnimatedContent(
                targetState = step,
                transitionSpec = {
                    fadeIn() togetherWith fadeOut()
                },
                label = "OnboardingSteps"
            ) { currentStep ->
                when (currentStep) {
                    1 -> StepLevelSelection(
                        selected = selectedLevel,
                        onSelect = { selectedLevel = it }
                    )
                    2 -> StepGoalSelection(
                        selected = selectedGoal,
                        onSelect = { selectedGoal = it }
                    )
                    3 -> StepTargetSelection(
                        minutes = selectedMinutes,
                        onMinutesSelect = { selectedMinutes = it },
                        vocab = selectedVocabTarget,
                        onVocabSelect = { selectedVocabTarget = it }
                    )
                    4 -> StepPersonalizedPathSummary(
                        level = selectedLevel,
                        goal = selectedGoal,
                        minutes = selectedMinutes,
                        vocab = selectedVocabTarget
                    )
                }
            }
        }
    }
}

@Composable
fun StepLevelSelection(
    selected: String,
    onSelect: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Sejauh mana kemampuan bahasa Jepang Anda saat ini?",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                color = SumiInk
            )
        )
        Text(
            text = "Kami akan menyesuaikan materi awal yang paling tepat untuk Anda.",
            style = MaterialTheme.typography.bodySmall.copy(color = SumiGray),
            modifier = Modifier.padding(top = 4.dp, bottom = 16.dp)
        )

        OptionCard(
            title = "Pemula Total (Nol)",
            subtitle = "Belum hafal Hiragana & Katakana sama sekali, baru ingin mulai belajar dasar.",
            japanese = "初めて",
            icon = Icons.Default.ChildCare,
            isSelected = selected == "BEGINNER",
            onClick = { onSelect("BEGINNER") }
        )

        Spacer(modifier = Modifier.height(10.dp))

        OptionCard(
            title = "Persiapan JLPT N5",
            subtitle = "Sudah hafal sebagian Kana, ingin fokus tata bahasa dasar, kosakata N5, dan kanji.",
            japanese = "N5 レベル",
            icon = Icons.Default.School,
            isSelected = selected == "N5",
            onClick = { onSelect("N5") }
        )

        Spacer(modifier = Modifier.height(10.dp))

        OptionCard(
            title = "Melangkah ke JLPT N4",
            subtitle = "Sudah lulus/menguasai N5, ingin meningkatkan percakapan dan pola kalimat kompleks N4.",
            japanese = "N4 レベル",
            icon = Icons.Default.TrendingUp,
            isSelected = selected == "N4",
            onClick = { onSelect("N4") }
        )
    }
}

@Composable
fun StepGoalSelection(
    selected: String,
    onSelect: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Apa tujuan utama Anda belajar bahasa Jepang?",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                color = SumiInk
            )
        )
        Text(
            text = "Ini membantu kami memprioritaskan kosakata dan situasi percakapan relevan.",
            style = MaterialTheme.typography.bodySmall.copy(color = SumiGray),
            modifier = Modifier.padding(top = 4.dp, bottom = 16.dp)
        )

        OptionCard(
            title = "Lulus Ujian JLPT (N5 / N4)",
            subtitle = "Fokus materi silabus resmi JLPT, latihan soal, kanji, dan pemahaman tata bahasa.",
            japanese = "JLPT 合格",
            icon = Icons.Default.Assignment,
            isSelected = selected == "JLPT",
            onClick = { onSelect("JLPT") }
        )

        Spacer(modifier = Modifier.height(10.dp))

        OptionCard(
            title = "Bekerja di Jepang (Kenshuu / Tokutei Ginou)",
            subtitle = "Ungkapan kerja di pabrik/kantor, etika sopan (Keigo), dan percakapan bersama atasan.",
            japanese = "日本で就職",
            icon = Icons.Default.Work,
            isSelected = selected == "WORK",
            onClick = { onSelect("WORK") }
        )

        Spacer(modifier = Modifier.height(10.dp))

        OptionCard(
            title = "Liburan & Traveling ke Jepang",
            subtitle = "Percakapan praktis di stasiun, pesan makanan di restoran, belanja, dan tanya arah jalan.",
            japanese = "日本旅行",
            icon = Icons.Default.FlightTakeoff,
            isSelected = selected == "TRAVEL",
            onClick = { onSelect("TRAVEL") }
        )

        Spacer(modifier = Modifier.height(10.dp))

        OptionCard(
            title = "Percakapan Sehari-hari & Hobi",
            subtitle = "Menonton anime tanpa subtitle, mengobrol dengan teman Jepang, dan berbicara santai.",
            japanese = "日常会話",
            icon = Icons.Default.ChatBubble,
            isSelected = selected == "CONVERSATION",
            onClick = { onSelect("CONVERSATION") }
        )
    }
}

@Composable
fun StepTargetSelection(
    minutes: Int,
    onMinutesSelect: (Int) -> Unit,
    vocab: Int,
    onVocabSelect: (Int) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Tentukan Target Belajar Harian Anda",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                color = SumiInk
            )
        )
        Text(
            text = "Konsistensi setiap hari jauh lebih efektif daripada belajar berjam-jam sekali seminggu.",
            style = MaterialTheme.typography.bodySmall.copy(color = SumiGray),
            modifier = Modifier.padding(top = 4.dp, bottom = 16.dp)
        )

        Text(
            text = "1. Target Waktu Belajar Per Hari",
            style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            listOf(10 to "Santai", 20 to "Standar", 30 to "Intensif").forEach { (m, label) ->
                val isSel = minutes == m
                Card(
                    modifier = Modifier
                        .weight(1f)
                        .clickable { onMinutesSelect(m) }
                        .border(
                            border = if (isSel) BorderStroke(2.dp, JapanCrimson) else BorderStroke(1.dp, WashiBorder),
                            shape = RoundedCornerShape(12.dp)
                        ),
                    colors = CardDefaults.cardColors(
                        containerColor = if (isSel) JapanCrimsonLight else WashiSurfaceVariant
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "$m Menit",
                            style = MaterialTheme.typography.titleSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = if (isSel) JapanCrimson else SumiInk
                            )
                        )
                        Text(
                            text = label,
                            style = MaterialTheme.typography.labelSmall.copy(color = SumiGray)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "2. Target Kosakata Baru Per Hari",
            style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            listOf(10 to "10 Kata", 20 to "20 Kata", 30 to "30 Kata").forEach { (v, label) ->
                val isSel = vocab == v
                Card(
                    modifier = Modifier
                        .weight(1f)
                        .clickable { onVocabSelect(v) }
                        .border(
                            border = if (isSel) BorderStroke(2.dp, JapanCrimson) else BorderStroke(1.dp, WashiBorder),
                            shape = RoundedCornerShape(12.dp)
                        ),
                    colors = CardDefaults.cardColors(
                        containerColor = if (isSel) JapanCrimsonLight else WashiSurfaceVariant
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = label,
                            style = MaterialTheme.typography.titleSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = if (isSel) JapanCrimson else SumiInk
                            )
                        )
                        Text(
                            text = if (v == 10) "Direkomendasikan" else if (v == 20) "Cepat" else "Super Cepat",
                            style = MaterialTheme.typography.labelSmall.copy(color = SumiGray, fontSize = 10.sp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun StepPersonalizedPathSummary(
    level: String,
    goal: String,
    minutes: Int,
    vocab: Int
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .background(MatchaGreenLight),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = null,
                tint = MatchaGreen,
                modifier = Modifier.size(36.dp)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Jalur Belajar Khusus Anda Siap!",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        )

        Text(
            text = "Berdasarkan preferensi Anda, kami telah menyusun roadmap harian terstruktur:",
            style = MaterialTheme.typography.bodySmall.copy(
                color = SumiGray,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = WashiSurfaceVariant),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                PlanItemRow("Tingkat Mulai", if (level == "BEGINNER") "Pemula (Hiragana & Katakana)" else if (level == "N5") "Persiapan JLPT N5" else "Lanjut JLPT N4")
                Divider(modifier = Modifier.padding(vertical = 8.dp), color = WashiBorder)
                PlanItemRow("Fokus Utama", if (goal == "JLPT") "Silabus Ujian Resmi JLPT N5/N4" else if (goal == "WORK") "Bahasa Jepang Kerja & Etika" else if (goal == "TRAVEL") "Percakapan Praktis Traveling" else "Percakapan Santai & Budaya")
                Divider(modifier = Modifier.padding(vertical = 8.dp), color = WashiBorder)
                PlanItemRow("Target Harian", "$minutes Menit / $vocab Kosakata Baru")
                Divider(modifier = Modifier.padding(vertical = 8.dp), color = WashiBorder)
                PlanItemRow("Spaced Repetition", "Review otomatis aktif setiap hari")
            }
        }
    }
}

@Composable
fun PlanItemRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = label, style = MaterialTheme.typography.bodySmall.copy(color = SumiGray))
        Text(text = value, style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold, color = SumiInk))
    }
}

@Composable
fun OptionCard(
    title: String,
    subtitle: String,
    japanese: String,
    icon: ImageVector,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .border(
                border = if (isSelected) BorderStroke(2.dp, JapanCrimson) else BorderStroke(1.dp, WashiBorder),
                shape = RoundedCornerShape(16.dp)
            ),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) JapanCrimsonLight else WashiSurfaceVariant
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(42.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(if (isSelected) JapanCrimson else WashiBorder),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = if (isSelected) Color.White else SumiCharcoal,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = SumiInk
                        )
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = japanese,
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = JapanCrimson,
                            fontWeight = FontWeight.Medium
                        )
                    )
                }
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = SumiGray,
                        fontSize = 12.sp,
                        lineHeight = 16.sp
                    )
                )
            }

            if (isSelected) {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = null,
                    tint = JapanCrimson,
                    modifier = Modifier.size(22.dp)
                )
            }
        }
    }
}
