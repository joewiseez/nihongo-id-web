package com.example.ui.screens.review

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.local.ReviewItemEntity
import com.example.ui.components.JapaneseAudioButton
import com.example.ui.theme.*

@Composable
fun ReviewScreen(
    dueItems: List<ReviewItemEntity>,
    onRateItem: (ReviewItemEntity, Int) -> Unit,
    onSpeak: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var currentIndex by remember { mutableIntStateOf(0) }
    var isAnswerRevealed by remember { mutableStateOf(false) }
    var totalReviewedThisSession by remember { mutableIntStateOf(0) }

    val currentItem = dueItems.getOrNull(currentIndex)

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Spaced Repetition (SRS)",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = SumiInk
                    )
                )
                Text(
                    text = if (dueItems.isNotEmpty()) "Tersisa ${dueItems.size - currentIndex} kartu hari ini" else "Tidak ada antrean kartu",
                    style = MaterialTheme.typography.bodySmall.copy(color = SumiGray)
                )
            }

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(JapanCrimsonLight)
                    .padding(horizontal = 10.dp, vertical = 4.dp)
            ) {
                Text(
                    text = "${totalReviewedThisSession * 15} XP Didapat",
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = JapanCrimson,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (currentItem != null) {
            // Linear Progress of review session
            val progressFraction = if (dueItems.isNotEmpty()) {
                (currentIndex.toFloat() / dueItems.size.toFloat()).coerceIn(0f, 1f)
            } else 0f

            LinearProgressIndicator(
                progress = { progressFraction },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .clip(RoundedCornerShape(3.dp)),
                color = JapanCrimson,
                trackColor = WashiBorder
            )

            Spacer(modifier = Modifier.height(24.dp))

            // The Flashcard
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .clickable { isAnswerRevealed = !isAnswerRevealed },
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                shape = RoundedCornerShape(24.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    // Tag and Audio
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(WashiSurfaceVariant)
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = currentItem.itemType,
                                style = MaterialTheme.typography.labelSmall.copy(color = SumiGray)
                            )
                        }

                        JapaneseAudioButton(
                            textToSpeak = currentItem.japaneseText,
                            onSpeak = onSpeak,
                            size = 42.dp
                        )
                    }

                    // Main Japanese Character / Word
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(vertical = 16.dp)
                    ) {
                        Text(
                            text = currentItem.japaneseText,
                            style = MaterialTheme.typography.displayLarge.copy(
                                fontSize = if (currentItem.japaneseText.length > 2) 48.sp else 68.sp,
                                fontWeight = FontWeight.Bold,
                                color = SumiInk
                            )
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        if (isAnswerRevealed) {
                            AnimatedVisibility(
                                visible = isAnswerRevealed,
                                enter = fadeIn() + expandVertically()
                            ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text(
                                        text = currentItem.reading,
                                        style = MaterialTheme.typography.titleMedium.copy(
                                            color = SumiGray,
                                            fontWeight = FontWeight.Medium
                                        )
                                    )
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text(
                                        text = currentItem.meaningId,
                                        style = MaterialTheme.typography.headlineSmall.copy(
                                            fontWeight = FontWeight.Bold,
                                            color = JapanCrimson,
                                            textAlign = TextAlign.Center
                                        )
                                    )
                                }
                            }
                        } else {
                            Text(
                                text = "Ketuk untuk melihat cara baca & arti",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = SumiLightGray,
                                    fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
                                )
                            )
                        }
                    }

                    // Bottom info in card
                    Text(
                        text = "Interval saat ini: ${currentItem.intervalDays} hari • Ulangan ke-${currentItem.repetitions}",
                        style = MaterialTheme.typography.labelSmall.copy(color = SumiLightGray)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Rating Controls
            if (!isAnswerRevealed) {
                Button(
                    onClick = { isAnswerRevealed = true },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = JapanCrimson),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Icon(imageVector = Icons.Default.Visibility, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Tampilkan Jawaban",
                        style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold)
                    )
                }
            } else {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Seberapa mudah Anda mengingat kartu ini?",
                        style = MaterialTheme.typography.labelSmall.copy(color = SumiGray),
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        // Again (Lupa)
                        RatingButton(
                            title = "Lupa",
                            interval = "< 10 m",
                            color = Color(0xFFE53935),
                            onClick = {
                                onRateItem(currentItem, 0)
                                isAnswerRevealed = false
                                totalReviewedThisSession++
                                if (currentIndex + 1 < dueItems.size) currentIndex++
                            },
                            modifier = Modifier.weight(1f)
                        )

                        // Hard (Sulit)
                        RatingButton(
                            title = "Sulit",
                            interval = "1 hari",
                            color = AmberGold,
                            onClick = {
                                onRateItem(currentItem, 1)
                                isAnswerRevealed = false
                                totalReviewedThisSession++
                                if (currentIndex + 1 < dueItems.size) currentIndex++
                            },
                            modifier = Modifier.weight(1f)
                        )

                        // Good (Baik)
                        RatingButton(
                            title = "Baik",
                            interval = "3 hari",
                            color = MatchaGreen,
                            onClick = {
                                onRateItem(currentItem, 2)
                                isAnswerRevealed = false
                                totalReviewedThisSession++
                                if (currentIndex + 1 < dueItems.size) currentIndex++
                            },
                            modifier = Modifier.weight(1f)
                        )

                        // Easy (Mudah)
                        RatingButton(
                            title = "Mudah",
                            interval = "5 hari",
                            color = IndigoJlpt,
                            onClick = {
                                onRateItem(currentItem, 3)
                                isAnswerRevealed = false
                                totalReviewedThisSession++
                                if (currentIndex + 1 < dueItems.size) currentIndex++
                            },
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        } else {
            // Completion / Empty Queue Screen
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                shape = RoundedCornerShape(24.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                            .background(MatchaGreenLight),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = null,
                            tint = MatchaGreen,
                            modifier = Modifier.size(48.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Hebat! Semua Review Selesai",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Anda telah menuntaskan seluruh antrean pengulangan berkala hari ini. Memori jangka panjang Anda semakin kuat!",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = SumiGray,
                            textAlign = TextAlign.Center
                        )
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Card(
                        colors = CardDefaults.cardColors(containerColor = WashiSurfaceVariant),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(Icons.Default.Bolt, contentDescription = null, tint = AmberGold)
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "+${totalReviewedThisSession * 15} XP Ditambahkan ke Akun",
                                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun RatingButton(
    title: String,
    interval: String,
    color: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .clickable { onClick() }
            .border(1.dp, color.copy(alpha = 0.4f), RoundedCornerShape(12.dp)),
        colors = CardDefaults.cardColors(containerColor = color.copy(alpha = 0.12f)),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.labelMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = color
                )
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = interval,
                style = MaterialTheme.typography.labelSmall.copy(
                    fontSize = 10.sp,
                    color = SumiGray
                )
            )
        }
    }
}
