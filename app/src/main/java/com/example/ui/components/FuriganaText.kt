package com.example.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.SumiCharcoal
import com.example.ui.theme.SumiGray
import com.example.ui.theme.SumiInk

@Composable
fun FuriganaDisplay(
    japanese: String,
    furigana: String,
    romaji: String,
    indonesian: String,
    modifier: Modifier = Modifier,
    alignCenter: Boolean = false
) {
    val alignment = if (alignCenter) Alignment.CenterHorizontally else Alignment.Start

    Column(
        modifier = modifier,
        horizontalAlignment = alignment
    ) {
        // Furigana reading if different from main text
        if (furigana.isNotBlank() && furigana != japanese) {
            Text(
                text = furigana,
                style = MaterialTheme.typography.labelSmall.copy(
                    fontSize = 13.sp,
                    color = SumiGray,
                    letterSpacing = 1.sp
                )
            )
        }

        // Japanese main text
        Text(
            text = japanese,
            style = MaterialTheme.typography.displayMedium.copy(
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = SumiInk
            )
        )

        Spacer(modifier = Modifier.height(2.dp))

        // Romaji
        Text(
            text = romaji,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontSize = 14.sp,
                color = SumiCharcoal,
                fontWeight = FontWeight.Medium
            )
        )

        Spacer(modifier = Modifier.height(4.dp))

        // Indonesian translation
        Text(
            text = indonesian,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 15.sp,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.SemiBold
            )
        )
    }
}
