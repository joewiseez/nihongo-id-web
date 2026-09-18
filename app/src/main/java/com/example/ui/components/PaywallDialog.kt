package com.example.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Star
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
import androidx.compose.ui.window.Dialog
import com.example.ui.theme.*

@Composable
fun PaywallDialog(
    isCurrentlyPremium: Boolean,
    onDismiss: () -> Unit,
    onTogglePremium: (Boolean) -> Unit
) {
    var selectedPlan by remember { mutableStateOf("YEARLY") } // "MONTHLY" or "YEARLY"

    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Close button top right
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .background(JapanCrimsonContainer)
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "NIHONGOPRO",
                            style = MaterialTheme.typography.labelMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = JapanCrimson
                            )
                        )
                    }

                    IconButton(onClick = onDismiss, modifier = Modifier.size(32.dp)) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = "Tutup")
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = AmberGold,
                    modifier = Modifier.size(48.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Akses Penuh Kurikulum JLPT N5 & N4",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                )

                Text(
                    text = "Kuasai bahasa Jepang lebih cepat dengan latihan menyimak tanpa batas dan spaced repetition canggih.",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = SumiGray,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier.padding(top = 4.dp, bottom = 16.dp)
                )

                // Feature Checklist
                val benefits = listOf(
                    "Kurikulum Lengkap N5 & N4 (Semua Bab)",
                    "Latihan Menyimak & Audio Asli Tanpa Batas",
                    "Kuis Harian & Pengenalan Huruf Tanpa Batas",
                    "Spaced Repetition Tingkat Lanjut (SRS)",
                    "Unduh Materi untuk Belajar Offline",
                    "Bebas Gangguan Iklan Selamanya"
                )

                Column(modifier = Modifier.fillMaxWidth()) {
                    benefits.forEach { benefit ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 3.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = null,
                                tint = MatchaGreen,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = benefit,
                                style = MaterialTheme.typography.bodySmall.copy(
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 13.sp
                                )
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Subscription Plan Selector
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Monthly Plan
                    val isMonthlySelected = selectedPlan == "MONTHLY"
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .clickable { selectedPlan = "MONTHLY" }
                            .border(
                                border = if (isMonthlySelected) BorderStroke(2.dp, JapanCrimson) else BorderStroke(1.dp, WashiBorder),
                                shape = RoundedCornerShape(16.dp)
                            ),
                        colors = CardDefaults.cardColors(
                            containerColor = if (isMonthlySelected) JapanCrimsonLight else WashiSurfaceVariant
                        ),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(12.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text("Bulanan", style = MaterialTheme.typography.labelMedium)
                            Text(
                                "Rp 39.000",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = JapanCrimson
                                )
                            )
                            Text("/ bulan", style = MaterialTheme.typography.labelSmall.copy(color = SumiGray))
                        }
                    }

                    // Yearly Plan (Best Value)
                    val isYearlySelected = selectedPlan == "YEARLY"
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .clickable { selectedPlan = "YEARLY" }
                            .border(
                                border = if (isYearlySelected) BorderStroke(2.dp, JapanCrimson) else BorderStroke(1.dp, WashiBorder),
                                shape = RoundedCornerShape(16.dp)
                            ),
                        colors = CardDefaults.cardColors(
                            containerColor = if (isYearlySelected) JapanCrimsonLight else WashiSurfaceVariant
                        ),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(12.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text("Tahunan", style = MaterialTheme.typography.labelMedium)
                            Text(
                                "Rp 299.000",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = JapanCrimson
                                )
                            )
                            Text("Hemat 35%", style = MaterialTheme.typography.labelSmall.copy(color = MatchaGreen, fontWeight = FontWeight.Bold))
                        }
                    }
                }

                Spacer(modifier = Modifier.height(18.dp))

                // Action Button
                Button(
                    onClick = {
                        onTogglePremium(!isCurrentlyPremium)
                        onDismiss()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = JapanCrimson),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = if (isCurrentlyPremium) "Batalkan Langganan (Kembali ke Gratis)" else "Aktifkan Paket Premium Sekarang",
                        style = MaterialTheme.typography.labelLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Dapat dibatalkan kapan saja melalui Pengaturan Akun Play Store.",
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = SumiLightGray,
                        textAlign = TextAlign.Center,
                        fontSize = 10.sp
                    )
                )
            }
        }
    }
}
