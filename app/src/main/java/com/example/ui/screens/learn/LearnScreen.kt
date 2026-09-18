package com.example.ui.screens.learn

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.*
import com.example.data.repository.JapaneseContentRepository
import com.example.ui.components.JapaneseAudioButton
import com.example.ui.theme.*

@Composable
fun LearnScreen(
    onOpenKanaDetail: (KanaCharacter) -> Unit,
    onOpenVocabDetail: (VocabularyItem) -> Unit,
    onOpenKanjiDetail: (KanjiItem) -> Unit,
    onOpenGrammarDetail: (GrammarItem) -> Unit,
    onOpenConversationDetail: (ConversationItem) -> Unit,
    onSpeak: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedCategoryIndex by remember { mutableIntStateOf(0) }
    val categories = listOf("Huruf Kana", "Kosakata", "Kanji", "Tata Bahasa", "Percakapan")

    Column(modifier = modifier.fillMaxSize()) {
        // Scrollable Category Tabs
        ScrollableTabRow(
            selectedTabIndex = selectedCategoryIndex,
            edgePadding = 16.dp,
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = JapanCrimson
        ) {
            categories.forEachIndexed { index, title ->
                Tab(
                    selected = selectedCategoryIndex == index,
                    onClick = { selectedCategoryIndex = index },
                    text = {
                        Text(
                            text = title,
                            style = MaterialTheme.typography.labelLarge.copy(
                                fontWeight = if (selectedCategoryIndex == index) FontWeight.Bold else FontWeight.Medium
                            )
                        )
                    }
                )
            }
        }

        when (selectedCategoryIndex) {
            0 -> KanaSection(onOpenKanaDetail = onOpenKanaDetail, onSpeak = onSpeak)
            1 -> VocabSection(onOpenVocabDetail = onOpenVocabDetail, onSpeak = onSpeak)
            2 -> KanjiSection(onOpenKanjiDetail = onOpenKanjiDetail, onSpeak = onSpeak)
            3 -> GrammarSection(onOpenGrammarDetail = onOpenGrammarDetail)
            4 -> ConversationSection(onOpenConversationDetail = onOpenConversationDetail, onSpeak = onSpeak)
        }
    }
}

@Composable
fun KanaSection(
    onOpenKanaDetail: (KanaCharacter) -> Unit,
    onSpeak: (String) -> Unit
) {
    var kanaTypeFilter by remember { mutableIntStateOf(0) } // 0: Hiragana Seion, 1: Hiragana Dakuten/Yoon, 2: Katakana

    val displayList = when (kanaTypeFilter) {
        0 -> JapaneseContentRepository.hiraganaList.filter { it.type == KanaType.HIRAGANA_SEION }
        1 -> JapaneseContentRepository.hiraganaList.filter { it.type != KanaType.HIRAGANA_SEION }
        else -> JapaneseContentRepository.katakanaList
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // Sub-filter chips
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            listOf("Hiragana Dasar", "Dakuten & Yoon", "Katakana").forEachIndexed { index, title ->
                val isSelected = kanaTypeFilter == index
                FilterChip(
                    selected = isSelected,
                    onClick = { kanaTypeFilter = index },
                    label = { Text(title, style = MaterialTheme.typography.labelSmall) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = JapanCrimsonLight,
                        selectedLabelColor = JapanCrimson
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Grid of Kana
        LazyVerticalGrid(
            columns = GridCells.Fixed(5),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(displayList) { kana ->
                Card(
                    modifier = Modifier
                        .aspectRatio(1f)
                        .clickable { onOpenKanaDetail(kana) },
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(4.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = kana.char,
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = SumiInk
                            )
                        )
                        Text(
                            text = kana.romaji,
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = JapanCrimson,
                                fontSize = 11.sp
                            )
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun VocabSection(
    onOpenVocabDetail: (VocabularyItem) -> Unit,
    onSpeak: (String) -> Unit
) {
    var selectedCategory by remember { mutableStateOf("Semua") }
    var selectedLevel by remember { mutableStateOf("SEMUA") } // SEMUA, N5, N4

    val allCategories = listOf("Semua") + JapaneseContentRepository.vocabularyList.map { it.category }.distinct()

    val filteredList = JapaneseContentRepository.vocabularyList.filter {
        (selectedCategory == "Semua" || it.category == selectedCategory) &&
        (selectedLevel == "SEMUA" || (selectedLevel == "N5" && it.jlptLevel == JlptLevel.N5) || (selectedLevel == "N4" && it.jlptLevel == JlptLevel.N4))
    }

    Column(modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp)) {
        Spacer(modifier = Modifier.height(12.dp))

        // Level selector
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            listOf("SEMUA" to "Semua Level", "N5" to "JLPT N5", "N4" to "JLPT N4").forEach { (lvl, title) ->
                val isSelected = selectedLevel == lvl
                FilterChip(
                    selected = isSelected,
                    onClick = { selectedLevel = lvl },
                    label = { Text(title, style = MaterialTheme.typography.labelSmall) }
                )
            }
        }

        // Category scroll
        LazyRow(
            modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            items(allCategories) { cat ->
                val isSelected = selectedCategory == cat
                InputChip(
                    selected = isSelected,
                    onClick = { selectedCategory = cat },
                    label = { Text(cat, style = MaterialTheme.typography.labelSmall) }
                )
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            items(filteredList) { vocab ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onOpenVocabDetail(vocab) },
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    shape = RoundedCornerShape(14.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = vocab.japanese,
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = SumiInk
                                    )
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = vocab.furigana,
                                    style = MaterialTheme.typography.bodySmall.copy(color = SumiGray)
                                )
                            }
                            Text(
                                text = vocab.romaji,
                                style = MaterialTheme.typography.labelSmall.copy(color = SumiCharcoal)
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = vocab.meaningId,
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = JapanCrimson,
                                    fontWeight = FontWeight.Medium
                                )
                            )
                        }

                        JapaneseAudioButton(
                            textToSpeak = vocab.japanese,
                            onSpeak = onSpeak,
                            size = 38.dp
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun KanjiSection(
    onOpenKanjiDetail: (KanjiItem) -> Unit,
    onSpeak: (String) -> Unit
) {
    val kanjiList = JapaneseContentRepository.kanjiList

    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(kanjiList) { kanji ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onOpenKanjiDetail(kanji) },
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                shape = RoundedCornerShape(16.dp),
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
                            .size(56.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(JapanCrimsonLight),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = kanji.character,
                            style = MaterialTheme.typography.displaySmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = JapanCrimson
                            )
                        )
                    }

                    Spacer(modifier = Modifier.width(14.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = kanji.meaningId,
                            style = MaterialTheme.typography.titleSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = SumiInk
                            )
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Onyomi: ${kanji.onyomi} • Kun: ${kanji.kunyomi}",
                            style = MaterialTheme.typography.bodySmall.copy(color = SumiGray)
                        )
                        Text(
                            text = "${kanji.strokeCount} Goresan • ${kanji.jlptLevel.displayName}",
                            style = MaterialTheme.typography.labelSmall.copy(color = SumiCharcoal)
                        )
                    }

                    JapaneseAudioButton(
                        textToSpeak = kanji.character,
                        onSpeak = onSpeak,
                        size = 36.dp
                    )
                }
            }
        }
    }
}

@Composable
fun GrammarSection(
    onOpenGrammarDetail: (GrammarItem) -> Unit
) {
    val grammarList = JapaneseContentRepository.grammarList

    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(grammarList) { grammar ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onOpenGrammarDetail(grammar) },
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
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(JapanCrimsonLight)
                                .padding(horizontal = 8.dp, vertical = 3.dp)
                        ) {
                            Text(
                                text = grammar.jlptLevel.displayName,
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = JapanCrimson,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }

                        Icon(
                            imageVector = Icons.Default.ChevronRight,
                            contentDescription = null,
                            tint = SumiGray
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = grammar.pattern,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = SumiInk
                        )
                    )

                    Text(
                        text = grammar.title,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = JapanCrimson,
                            fontWeight = FontWeight.Medium
                        )
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = grammar.explanationId,
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = SumiGray,
                            lineHeight = 18.sp
                        ),
                        maxLines = 2
                    )
                }
            }
        }
    }
}

@Composable
fun ConversationSection(
    onOpenConversationDetail: (ConversationItem) -> Unit,
    onSpeak: (String) -> Unit
) {
    val conversations = JapaneseContentRepository.conversationList

    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(conversations) { item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onOpenConversationDetail(item) },
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(46.dp)
                            .clip(CircleShape)
                            .background(JapanCrimsonContainer),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.RecordVoiceOver,
                            contentDescription = null,
                            tint = JapanCrimson,
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(14.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = item.category,
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = JapanCrimson,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Text(
                            text = item.title,
                            style = MaterialTheme.typography.titleSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = SumiInk
                            )
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "${item.dialogueLines.size} Kalimat Dialog • Audio Asli",
                            style = MaterialTheme.typography.bodySmall.copy(color = SumiGray)
                        )
                    }

                    Icon(
                        imageVector = Icons.Default.ChevronRight,
                        contentDescription = null,
                        tint = SumiGray
                    )
                }
            }
        }
    }
}
