package com.example.ui.screens.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    onClose: () -> Unit,
    onOpenVocabDetail: (VocabularyItem) -> Unit,
    onOpenKanjiDetail: (KanjiItem) -> Unit,
    onOpenGrammarDetail: (GrammarItem) -> Unit,
    onSpeak: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var searchQuery by remember { mutableStateOf("") }

    val matchedVocab = remember(searchQuery) {
        if (searchQuery.isBlank()) emptyList()
        else {
            JapaneseContentRepository.vocabularyList.filter {
                it.japanese.contains(searchQuery, ignoreCase = true) ||
                it.furigana.contains(searchQuery, ignoreCase = true) ||
                it.romaji.contains(searchQuery, ignoreCase = true) ||
                it.meaningId.contains(searchQuery, ignoreCase = true)
            }
        }
    }

    val matchedKanji = remember(searchQuery) {
        if (searchQuery.isBlank()) emptyList()
        else {
            JapaneseContentRepository.kanjiList.filter {
                it.character.contains(searchQuery, ignoreCase = true) ||
                it.meaningId.contains(searchQuery, ignoreCase = true) ||
                it.kunyomi.contains(searchQuery, ignoreCase = true) ||
                it.onyomi.contains(searchQuery, ignoreCase = true)
            }
        }
    }

    val matchedGrammar = remember(searchQuery) {
        if (searchQuery.isBlank()) emptyList()
        else {
            JapaneseContentRepository.grammarList.filter {
                it.pattern.contains(searchQuery, ignoreCase = true) ||
                it.title.contains(searchQuery, ignoreCase = true) ||
                it.explanationId.contains(searchQuery, ignoreCase = true)
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    OutlinedTextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        placeholder = { Text("Cari kosakata, kanji, arti...") },
                        singleLine = true,
                        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                        trailingIcon = {
                            if (searchQuery.isNotEmpty()) {
                                IconButton(onClick = { searchQuery = "" }) {
                                    Icon(Icons.Default.Close, contentDescription = "Hapus")
                                }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 8.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = JapanCrimson,
                            unfocusedBorderColor = WashiBorder
                        )
                    )
                },
                actions = {
                    TextButton(onClick = onClose) {
                        Text("Tutup", style = MaterialTheme.typography.labelLarge)
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(vertical = 12.dp)
        ) {
            if (searchQuery.isBlank()) {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 40.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null,
                            tint = SumiLightGray,
                            modifier = Modifier.size(48.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Ketik kata kunci pencarian",
                            style = MaterialTheme.typography.bodyMedium.copy(color = SumiGray)
                        )
                        Text(
                            text = "Contoh: \"makan\", \"taberu\", \"食べる\", \"partikel wa\"",
                            style = MaterialTheme.typography.bodySmall.copy(color = SumiLightGray)
                        )
                    }
                }
            } else if (matchedVocab.isEmpty() && matchedKanji.isEmpty() && matchedGrammar.isEmpty()) {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 40.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Tidak ditemukan hasil untuk \"$searchQuery\"",
                            style = MaterialTheme.typography.bodyMedium.copy(color = SumiGray)
                        )
                    }
                }
            } else {
                // Vocab Results
                if (matchedVocab.isNotEmpty()) {
                    item {
                        Text(
                            text = "Kosakata (${matchedVocab.size})",
                            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold, color = JapanCrimson)
                        )
                    }
                    items(matchedVocab) { vocab ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onOpenVocabDetail(vocab) },
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(12.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = "${vocab.japanese} (${vocab.furigana})",
                                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
                                    )
                                    Text(
                                        text = "${vocab.romaji} • ${vocab.meaningId}",
                                        style = MaterialTheme.typography.bodySmall.copy(color = SumiGray)
                                    )
                                }
                                JapaneseAudioButton(textToSpeak = vocab.japanese, onSpeak = onSpeak, size = 36.dp)
                            }
                        }
                    }
                }

                // Kanji Results
                if (matchedKanji.isNotEmpty()) {
                    item {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Kanji (${matchedKanji.size})",
                            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold, color = JapanCrimson)
                        )
                    }
                    items(matchedKanji) { kanji ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onOpenKanjiDetail(kanji) },
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = kanji.character,
                                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold, color = JapanCrimson)
                                )
                                Spacer(modifier = Modifier.width(12.dp))
                                Column {
                                    Text(text = kanji.meaningId, style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold))
                                    Text(text = "Onyomi: ${kanji.onyomi} • Kun: ${kanji.kunyomi}", style = MaterialTheme.typography.bodySmall.copy(color = SumiGray))
                                }
                            }
                        }
                    }
                }

                // Grammar Results
                if (matchedGrammar.isNotEmpty()) {
                    item {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Tata Bahasa (${matchedGrammar.size})",
                            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold, color = JapanCrimson)
                        )
                    }
                    items(matchedGrammar) { grammar ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onOpenGrammarDetail(grammar) },
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text(text = grammar.pattern, style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold, color = SumiInk))
                                Text(text = grammar.title, style = MaterialTheme.typography.bodySmall.copy(color = JapanCrimson))
                                Text(text = grammar.explanationId, style = MaterialTheme.typography.bodySmall.copy(color = SumiGray), maxLines = 1)
                            }
                        }
                    }
                }
            }
        }
    }
}
