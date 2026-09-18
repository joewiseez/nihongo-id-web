package com.example.ui.screens.learn.dialogs

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.data.model.*
import com.example.ui.components.JapaneseAudioButton
import com.example.ui.theme.*

@Composable
fun KanaDetailDialog(
    kana: KanaCharacter,
    onDismiss: () -> Unit,
    onSpeak: (String) -> Unit
) {
    var isPracticeMode by remember { mutableStateOf(false) }
    var userStrokes by remember { mutableStateOf(listOf<List<Offset>>()) }
    var currentStroke by remember { mutableStateOf(listOf<Offset>()) }

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
                    .verticalScroll(rememberScrollState())
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Top row with close
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(JapanCrimsonLight)
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = kana.type.groupTitle,
                            style = MaterialTheme.typography.labelSmall.copy(color = JapanCrimson, fontWeight = FontWeight.Bold)
                        )
                    }

                    IconButton(onClick = onDismiss, modifier = Modifier.size(32.dp)) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = "Tutup")
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Character Banner with audio
                Box(
                    modifier = Modifier
                        .size(130.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(WashiSurfaceVariant)
                        .border(1.dp, WashiBorder, RoundedCornerShape(20.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = kana.char,
                        style = MaterialTheme.typography.displayLarge.copy(
                            fontSize = 72.sp,
                            fontWeight = FontWeight.Bold,
                            color = JapanCrimson
                        )
                    )

                    JapaneseAudioButton(
                        textToSpeak = kana.char,
                        onSpeak = onSpeak,
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(6.dp),
                        size = 36.dp
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Romaji & Pronunciation
                Text(
                    text = kana.romaji,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = SumiInk
                    )
                )

                Text(
                    text = kana.pronunciationTip,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = SumiGray,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
                )

                Spacer(modifier = Modifier.height(14.dp))

                // Example word card
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = WashiSurfaceVariant),
                    shape = RoundedCornerShape(14.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(
                                text = "Contoh Kata:",
                                style = MaterialTheme.typography.labelSmall.copy(color = SumiGray)
                            )
                            Text(
                                text = kana.exampleWord,
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = SumiInk
                                )
                            )
                            Text(
                                text = "= ${kana.exampleMeaningId}",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = JapanCrimson,
                                    fontWeight = FontWeight.Medium
                                )
                            )
                        }

                        JapaneseAudioButton(
                            textToSpeak = kana.exampleWord,
                            onSpeak = onSpeak,
                            size = 40.dp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Interactive Writing Practice Toggle
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Latihan Menulis (${kana.strokeCount} Goresan)",
                        style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold)
                    )
                    TextButton(onClick = {
                        isPracticeMode = !isPracticeMode
                        userStrokes = emptyList()
                    }) {
                        Text(if (isPracticeMode) "Selesai Latihan" else "Buka Kanvas Tulis")
                    }
                }

                if (isPracticeMode) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color(0xFFF9F9F9))
                            .border(1.dp, WashiBorder, RoundedCornerShape(16.dp))
                            .pointerInput(Unit) {
                                detectDragGestures(
                                    onDragStart = { offset ->
                                        currentStroke = listOf(offset)
                                    },
                                    onDrag = { change, _ ->
                                        currentStroke = currentStroke + change.position
                                    },
                                    onDragEnd = {
                                        if (currentStroke.isNotEmpty()) {
                                            userStrokes = userStrokes + listOf(currentStroke)
                                            currentStroke = emptyList()
                                        }
                                    }
                                )
                            }
                    ) {
                        // Background guide character watermark
                        Text(
                            text = kana.char,
                            style = MaterialTheme.typography.displayLarge.copy(
                                fontSize = 120.sp,
                                color = Color(0xFFE2E2E6).copy(alpha = 0.6f)
                            ),
                            modifier = Modifier.align(Alignment.Center)
                        )

                        // User Drawing Canvas
                        Canvas(modifier = Modifier.fillMaxSize()) {
                            // Render completed strokes
                            userStrokes.forEach { stroke ->
                                if (stroke.size > 1) {
                                    val path = Path().apply {
                                        moveTo(stroke.first().x, stroke.first().y)
                                        for (i in 1 until stroke.size) {
                                            lineTo(stroke[i].x, stroke[i].y)
                                        }
                                    }
                                    drawPath(
                                        path = path,
                                        color = SumiInk,
                                        style = Stroke(width = 12f, cap = StrokeCap.Round, join = StrokeJoin.Round)
                                    )
                                }
                            }
                            // Render current active stroke
                            if (currentStroke.size > 1) {
                                val path = Path().apply {
                                    moveTo(currentStroke.first().x, currentStroke.first().y)
                                    for (i in 1 until currentStroke.size) {
                                        lineTo(currentStroke[i].x, currentStroke[i].y)
                                    }
                                }
                                drawPath(
                                    path = path,
                                    color = JapanCrimson,
                                    style = Stroke(width = 12f, cap = StrokeCap.Round, join = StrokeJoin.Round)
                                )
                            }
                        }

                        // Clear Canvas Button
                        IconButton(
                            onClick = {
                                userStrokes = emptyList()
                                currentStroke = emptyList()
                            },
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .padding(8.dp)
                                .size(36.dp)
                                .background(Color.White.copy(alpha = 0.9f), CircleShape)
                        ) {
                            Icon(imageVector = Icons.Default.Refresh, contentDescription = "Hapus Gambar", tint = SumiGray)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun VocabDetailDialog(
    vocab: VocabularyItem,
    isFavorite: Boolean,
    onToggleFavorite: () -> Unit,
    onDismiss: () -> Unit,
    onSpeak: (String) -> Unit
) {
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
                    .verticalScroll(rememberScrollState())
                    .padding(20.dp)
            ) {
                // Top actions
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(JapanCrimsonLight)
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = vocab.jlptLevel.displayName,
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = JapanCrimson,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }
                        Spacer(modifier = Modifier.width(6.dp))
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(WashiSurfaceVariant)
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = vocab.category,
                                style = MaterialTheme.typography.labelSmall.copy(color = SumiGray)
                            )
                        }
                    }

                    Row {
                        IconButton(onClick = onToggleFavorite) {
                            Icon(
                                imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                contentDescription = "Simpan Favorit",
                                tint = if (isFavorite) JapanCrimson else SumiGray
                            )
                        }
                        IconButton(onClick = onDismiss) {
                            Icon(imageVector = Icons.Default.Close, contentDescription = "Tutup")
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Japanese main word & Furigana
                if (vocab.furigana.isNotBlank() && vocab.furigana != vocab.japanese) {
                    Text(
                        text = vocab.furigana,
                        style = MaterialTheme.typography.labelMedium.copy(color = SumiGray, letterSpacing = 1.sp)
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = vocab.japanese,
                        style = MaterialTheme.typography.displayMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = SumiInk
                        )
                    )

                    JapaneseAudioButton(
                        textToSpeak = vocab.japanese,
                        onSpeak = onSpeak,
                        size = 44.dp
                    )
                }

                Text(
                    text = vocab.romaji,
                    style = MaterialTheme.typography.bodyMedium.copy(color = SumiCharcoal)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = vocab.meaningId,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = JapanCrimson
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Example Sentence Card
                Text(
                    text = "Contoh Kalimat:",
                    style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold)
                )

                Spacer(modifier = Modifier.height(6.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = WashiSurfaceVariant),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.Top
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                if (vocab.exampleFurigana.isNotBlank()) {
                                    Text(
                                        text = vocab.exampleFurigana,
                                        style = MaterialTheme.typography.labelSmall.copy(color = SumiGray)
                                    )
                                }
                                Text(
                                    text = vocab.exampleJp,
                                    style = MaterialTheme.typography.titleSmall.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = SumiInk
                                    )
                                )
                                Text(
                                    text = vocab.exampleRomaji,
                                    style = MaterialTheme.typography.bodySmall.copy(color = SumiCharcoal)
                                )
                            }

                            JapaneseAudioButton(
                                textToSpeak = vocab.exampleJp,
                                onSpeak = onSpeak,
                                size = 38.dp
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "= ${vocab.exampleMeaningId}",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Medium,
                                color = MatchaGreen
                            )
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun KanjiDetailDialog(
    kanji: KanjiItem,
    onDismiss: () -> Unit,
    onSpeak: (String) -> Unit
) {
    var userStrokes by remember { mutableStateOf(listOf<List<Offset>>()) }
    var currentStroke by remember { mutableStateOf(listOf<Offset>()) }

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
                    .verticalScroll(rememberScrollState())
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(JapanCrimsonLight)
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "${kanji.jlptLevel.displayName} • ${kanji.strokeCount} Goresan",
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = JapanCrimson,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }

                    IconButton(onClick = onDismiss, modifier = Modifier.size(32.dp)) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = "Tutup")
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Kanji Visual Box with Canvas Practice
                Box(
                    modifier = Modifier
                        .size(150.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(WashiSurfaceVariant)
                        .border(1.dp, WashiBorder, RoundedCornerShape(20.dp))
                        .pointerInput(Unit) {
                            detectDragGestures(
                                onDragStart = { offset ->
                                    currentStroke = listOf(offset)
                                },
                                onDrag = { change, _ ->
                                    currentStroke = currentStroke + change.position
                                },
                                onDragEnd = {
                                    if (currentStroke.isNotEmpty()) {
                                        userStrokes = userStrokes + listOf(currentStroke)
                                        currentStroke = emptyList()
                                    }
                                }
                            )
                        },
                    contentAlignment = Alignment.Center
                ) {
                    // Ghost Kanji Guide
                    Text(
                        text = kanji.character,
                        style = MaterialTheme.typography.displayLarge.copy(
                            fontSize = 88.sp,
                            fontWeight = FontWeight.Bold,
                            color = JapanCrimson.copy(alpha = 0.35f)
                        )
                    )

                    // User strokes
                    Canvas(modifier = Modifier.fillMaxSize()) {
                        userStrokes.forEach { stroke ->
                            if (stroke.size > 1) {
                                val path = Path().apply {
                                    moveTo(stroke.first().x, stroke.first().y)
                                    for (i in 1 until stroke.size) {
                                        lineTo(stroke[i].x, stroke[i].y)
                                    }
                                }
                                drawPath(
                                    path = path,
                                    color = SumiInk,
                                    style = Stroke(width = 10f, cap = StrokeCap.Round, join = StrokeJoin.Round)
                                )
                            }
                        }
                        if (currentStroke.size > 1) {
                            val path = Path().apply {
                                moveTo(currentStroke.first().x, currentStroke.first().y)
                                for (i in 1 until currentStroke.size) {
                                    lineTo(currentStroke[i].x, currentStroke[i].y)
                                }
                            }
                            drawPath(
                                path = path,
                                color = JapanCrimson,
                                style = Stroke(width = 10f, cap = StrokeCap.Round, join = StrokeJoin.Round)
                            )
                        }
                    }

                    // Reset brush
                    IconButton(
                        onClick = {
                            userStrokes = emptyList()
                            currentStroke = emptyList()
                        },
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(6.dp)
                            .size(32.dp)
                            .background(Color.White.copy(alpha = 0.85f), CircleShape)
                    ) {
                        Icon(imageVector = Icons.Default.Refresh, contentDescription = "Hapus Goresan", tint = SumiGray)
                    }

                    JapaneseAudioButton(
                        textToSpeak = kanji.character,
                        onSpeak = onSpeak,
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(6.dp),
                        size = 32.dp
                    )
                }

                Text(
                    text = "Tuliskan goresan kanji di atas",
                    style = MaterialTheme.typography.labelSmall.copy(color = SumiLightGray),
                    modifier = Modifier.padding(top = 4.dp)
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Meaning in Indonesian
                Text(
                    text = kanji.meaningId,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = JapanCrimson,
                        textAlign = TextAlign.Center
                    )
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Onyomi & Kunyomi Card
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Card(
                        modifier = Modifier.weight(1f),
                        colors = CardDefaults.cardColors(containerColor = WashiSurfaceVariant),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Column(modifier = Modifier.padding(10.dp)) {
                            Text("Onyomi (China)", style = MaterialTheme.typography.labelSmall.copy(color = SumiGray))
                            Text(
                                text = kanji.onyomi,
                                style = MaterialTheme.typography.titleSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = SumiInk
                                )
                            )
                        }
                    }

                    Card(
                        modifier = Modifier.weight(1f),
                        colors = CardDefaults.cardColors(containerColor = WashiSurfaceVariant),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Column(modifier = Modifier.padding(10.dp)) {
                            Text("Kunyomi (Jepang)", style = MaterialTheme.typography.labelSmall.copy(color = SumiGray))
                            Text(
                                text = kanji.kunyomi,
                                style = MaterialTheme.typography.titleSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = SumiInk
                                )
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Example Compound Words
                Text(
                    text = "Kosakata Terkait (Jukugo):",
                    style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.align(Alignment.Start)
                )

                Spacer(modifier = Modifier.height(6.dp))

                kanji.examples.forEach { compound ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = compound.word,
                                style = MaterialTheme.typography.titleSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = SumiInk
                                )
                            )
                            Text(
                                text = compound.reading,
                                style = MaterialTheme.typography.bodySmall.copy(color = SumiGray)
                            )
                            Text(
                                text = "= ${compound.meaningId}",
                                style = MaterialTheme.typography.bodySmall.copy(color = JapanCrimson)
                            )
                        }

                        JapaneseAudioButton(
                            textToSpeak = compound.word,
                            onSpeak = onSpeak,
                            size = 36.dp
                        )
                    }
                    Divider(color = WashiBorder.copy(alpha = 0.5f))
                }
            }
        }
    }
}

@Composable
fun GrammarDetailDialog(
    grammar: GrammarItem,
    onDismiss: () -> Unit,
    onSpeak: (String) -> Unit
) {
    var selectedQuizOption by remember { mutableIntStateOf(-1) }
    var quizAnswered by remember { mutableStateOf(false) }

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
                    .verticalScroll(rememberScrollState())
                    .padding(20.dp)
            ) {
                // Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(JapanCrimsonLight)
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = grammar.jlptLevel.displayName,
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = JapanCrimson,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }

                    IconButton(onClick = onDismiss, modifier = Modifier.size(32.dp)) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = "Tutup")
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Grammar Pattern
                Text(
                    text = grammar.pattern,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = JapanCrimson
                    )
                )

                Text(
                    text = grammar.title,
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = SumiInk
                    )
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Indonesian Explanation
                Text(
                    text = "Penjelasan:",
                    style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold)
                )
                Text(
                    text = grammar.explanationId,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = SumiCharcoal,
                        lineHeight = 22.sp
                    ),
                    modifier = Modifier.padding(top = 4.dp, bottom = 10.dp)
                )

                // When to use
                Text(
                    text = "Kapan Digunakan:",
                    style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold)
                )
                Text(
                    text = grammar.usageWhen,
                    style = MaterialTheme.typography.bodyMedium.copy(color = SumiCharcoal),
                    modifier = Modifier.padding(top = 4.dp, bottom = 12.dp)
                )

                // Common mistakes card
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF4E5)),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Warning,
                                contentDescription = null,
                                tint = AmberGold,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Kesalahan Umum Pemula:",
                                style = MaterialTheme.typography.labelMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFFB45309)
                                )
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = grammar.commonMistakesId,
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = Color(0xFF78350F),
                                lineHeight = 18.sp
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Example sentences
                Text(
                    text = "Contoh Kalimat:",
                    style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.height(6.dp))

                grammar.exampleSentences.forEach { eg ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        colors = CardDefaults.cardColors(containerColor = WashiSurfaceVariant),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(eg.furigana, style = MaterialTheme.typography.labelSmall.copy(color = SumiGray))
                                Text(
                                    eg.jp,
                                    style = MaterialTheme.typography.bodyLarge.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = SumiInk
                                    )
                                )
                                Text(
                                    eg.meaningId,
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        color = JapanCrimson,
                                        fontWeight = FontWeight.Medium
                                    )
                                )
                            }
                            JapaneseAudioButton(textToSpeak = eg.jp, onSpeak = onSpeak, size = 36.dp)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Interactive Mini Quiz
                Text(
                    text = "Mini Kuis Pemahaman:",
                    style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold)
                )
                Text(
                    text = grammar.miniQuizQuestion,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = SumiInk
                    ),
                    modifier = Modifier.padding(top = 4.dp, bottom = 8.dp)
                )

                grammar.miniQuizOptions.forEachIndexed { index, option ->
                    val isSelected = selectedQuizOption == index
                    val isCorrect = index == grammar.miniQuizCorrectIndex

                    val cardBg = when {
                        !quizAnswered -> if (isSelected) JapanCrimsonLight else WashiSurfaceVariant
                        isCorrect -> MatchaGreenLight
                        isSelected && !isCorrect -> Color(0xFFFFEBEE)
                        else -> WashiSurfaceVariant
                    }

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .clickable(enabled = !quizAnswered) {
                                selectedQuizOption = index
                                quizAnswered = true
                            },
                        colors = CardDefaults.cardColors(containerColor = cardBg),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = option,
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                    color = SumiInk
                                )
                            )
                            if (quizAnswered) {
                                if (isCorrect) {
                                    Icon(Icons.Default.CheckCircle, contentDescription = null, tint = MatchaGreen, modifier = Modifier.size(20.dp))
                                } else if (isSelected) {
                                    Icon(Icons.Default.Cancel, contentDescription = null, tint = JapanCrimson, modifier = Modifier.size(20.dp))
                                }
                            }
                        }
                    }
                }

                if (quizAnswered) {
                    Text(
                        text = "Penjelasan: ${grammar.miniQuizExplanation}",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = MatchaGreen,
                            fontWeight = FontWeight.Medium
                        ),
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun ConversationDetailDialog(
    conversation: ConversationItem,
    onDismiss: () -> Unit,
    onSpeak: (String) -> Unit
) {
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
                    .verticalScroll(rememberScrollState())
                    .padding(20.dp)
            ) {
                // Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(JapanCrimsonLight)
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = conversation.category,
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = JapanCrimson,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }

                    IconButton(onClick = onDismiss, modifier = Modifier.size(32.dp)) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = "Tutup")
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = conversation.title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = SumiInk
                    )
                )

                Text(
                    text = conversation.descriptionId,
                    style = MaterialTheme.typography.bodySmall.copy(color = SumiGray),
                    modifier = Modifier.padding(top = 2.dp, bottom = 12.dp)
                )

                // Cultural Note box
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MatchaGreenLight),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Lightbulb,
                            contentDescription = null,
                            tint = MatchaGreen,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = conversation.culturalNote,
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = Color(0xFF1B5E20),
                                fontSize = 12.sp,
                                lineHeight = 16.sp
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = "Ketuk baris untuk mendengar pengucapan audio:",
                    style = MaterialTheme.typography.labelSmall.copy(color = SumiLightGray)
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Dialogue Lines
                conversation.dialogueLines.forEach { line ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .clickable { onSpeak(line.japanese) },
                        colors = CardDefaults.cardColors(
                            containerColor = if (line.isUser) JapanCrimsonLight.copy(alpha = 0.5f) else WashiSurfaceVariant
                        ),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(34.dp)
                                    .clip(CircleShape)
                                    .background(if (line.isUser) JapanCrimson else SumiCharcoal),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = line.speaker.firstOrNull()?.toString() ?: "A",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                            }

                            Spacer(modifier = Modifier.width(10.dp))

                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = line.speaker,
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = if (line.isUser) JapanCrimson else SumiCharcoal
                                    )
                                )
                                Text(
                                    text = line.japanese,
                                    style = MaterialTheme.typography.bodyLarge.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = SumiInk
                                    )
                                )
                                Text(
                                    text = line.romaji,
                                    style = MaterialTheme.typography.bodySmall.copy(color = SumiGray)
                                )
                                Text(
                                    text = line.indonesian,
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        color = SumiInk,
                                        fontWeight = FontWeight.Medium
                                    )
                                )
                            }

                            IconButton(
                                onClick = { onSpeak(line.japanese) },
                                modifier = Modifier.size(32.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.VolumeUp,
                                    contentDescription = "Dengarkan",
                                    tint = if (line.isUser) JapanCrimson else SumiCharcoal,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
