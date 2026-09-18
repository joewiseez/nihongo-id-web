package com.example.ui.screens.practice

import androidx.compose.animation.*
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.QuizQuestion
import com.example.data.model.QuizType
import com.example.data.repository.JapaneseContentRepository
import com.example.ui.components.JapaneseAudioButton
import com.example.ui.theme.*

@Composable
fun PracticeScreen(
    onAddXp: (Int) -> Unit,
    onSpeak: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var activeQuizQuestions by remember { mutableStateOf<List<QuizQuestion>?>(null) }
    var quizTitle by remember { mutableStateOf("") }

    if (activeQuizQuestions != null) {
        ActiveQuizView(
            title = quizTitle,
            questions = activeQuizQuestions!!,
            onClose = { activeQuizQuestions = null },
            onAddXp = onAddXp,
            onSpeak = onSpeak
        )
    } else {
        PracticeMenu(
            onStartQuiz = { title, questions ->
                quizTitle = title
                activeQuizQuestions = questions
            }
        )
    }
}

@Composable
fun PracticeMenu(
    onStartQuiz: (String, List<QuizQuestion>) -> Unit
) {
    val scrollState = rememberScrollState()
    val dailyQuestions = remember { JapaneseContentRepository.generateQuizQuestions() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        Text(
            text = "Pusat Latihan & Kuis",
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                color = SumiInk
            )
        )
        Text(
            text = "Uji pemahaman kosakata, huruf, tata bahasa, dan kemampuan menyimak Anda.",
            style = MaterialTheme.typography.bodySmall.copy(color = SumiGray),
            modifier = Modifier.padding(top = 4.dp, bottom = 16.dp)
        )

        // 1. Kuis Harian JLPT N5
        QuizModeCard(
            title = "Kuis Harian JLPT N5",
            subtitle = "Soal campuran kosakata, kanji, dan partikel dasar bahasa Jepang.",
            questionCount = "${dailyQuestions.size} Soal",
            icon = Icons.Default.Assignment,
            iconBg = JapanCrimsonLight,
            iconColor = JapanCrimson,
            onClick = {
                onStartQuiz("Kuis Harian JLPT N5", dailyQuestions)
            }
        )

        Spacer(modifier = Modifier.height(12.dp))

        // 2. Latihan Menyimak (Listening Drill)
        val listeningQuestions = remember {
            JapaneseContentRepository.listeningList.map {
                QuizQuestion(
                    id = it.id,
                    type = QuizType.LISTENING,
                    prompt = "Dengarkan audio percakapan di atas dan jawab pertanyaan berikut: ${it.question}",
                    subPrompt = it.title,
                    audioText = it.audioPrompt,
                    options = it.options,
                    correctIndex = it.correctIndex,
                    explanation = it.explanation
                )
            }
        }

        QuizModeCard(
            title = "Latihan Menyimak (Choukai)",
            subtitle = "Latih telinga Anda mendengarkan intonasi asli penutur bahasa Jepang.",
            questionCount = "${listeningQuestions.size} Latihan Audio",
            icon = Icons.Default.Hearing,
            iconBg = IndigoJlptLight,
            iconColor = IndigoJlpt,
            onClick = {
                onStartQuiz("Latihan Menyimak (Choukai)", listeningQuestions)
            }
        )

        Spacer(modifier = Modifier.height(12.dp))

        // 3. Pengenalan Huruf & Kanji
        val characterQuestions = remember {
            listOf(
                QuizQuestion(
                    id = "char_q1",
                    type = QuizType.KANA,
                    prompt = "Bagaimana cara membaca huruf katakana 『テレビ』?",
                    options = listOf("Terebi (Televisi)", "Terabi", "Torebi", "Tereba"),
                    correctIndex = 0,
                    explanation = "『テレビ』 dibaca 'Terebi', berasal dari kata serapan bahasa Inggris 'Television'."
                ),
                QuizQuestion(
                    id = "char_q2",
                    type = QuizType.KANJI,
                    prompt = "Kanji 『本』 memiliki arti apa dalam bahasa Indonesia?",
                    options = listOf("Buku / Asal", "Pohon", "Air", "Matahari"),
                    correctIndex = 0,
                    explanation = "『本』 (ほん / hon) artinya Buku atau Asal / Pokok."
                ),
                QuizQuestion(
                    id = "char_q3",
                    type = QuizType.KANA,
                    prompt = "Huruf hiragana manakah yang dibaca 'shi'?",
                    options = listOf("し", "つ", "ち", "さ"),
                    correctIndex = 0,
                    explanation = "『し』 adalah huruf hiragana untuk bunyi 'shi'. Sedangkan 『つ』 adalah 'tsu'."
                )
            )
        }

        QuizModeCard(
            title = "Pengenalan Huruf & Kanji",
            subtitle = "Uji kecepatan mengenali hiragana, katakana, dan kanji dasar.",
            questionCount = "${characterQuestions.size} Soal",
            icon = Icons.Default.Spellcheck,
            iconBg = MatchaGreenLight,
            iconColor = MatchaGreen,
            onClick = {
                onStartQuiz("Pengenalan Huruf & Kanji", characterQuestions)
            }
        )

        Spacer(modifier = Modifier.height(12.dp))

        // 4. Kuis Tata Bahasa (Bunpou)
        val grammarQuestions = remember {
            listOf(
                QuizQuestion(
                    id = "bunpou_q1",
                    type = QuizType.GRAMMAR,
                    prompt = "Lengkapi: 私はジャカルタ (   ) 住んでいます。(Saya tinggal di Jakarta)",
                    options = listOf("に", "で", "を", "へ"),
                    correctIndex = 0,
                    explanation = "Partikel 『に』 digunakan untuk menunjukkan tempat tinggal atau keberadaan tetap."
                ),
                QuizQuestion(
                    id = "bunpou_q2",
                    type = QuizType.GRAMMAR,
                    prompt = "Bentuk lampau sopan dari kata kerja 『行きます』(Pergi) adalah?",
                    options = listOf("行きました", "行きません", "行きたいです", "行って"),
                    correctIndex = 0,
                    explanation = "Bentuk lampau positif dari akhiran ~masu adalah ~mashita (行きました = telah pergi)."
                )
            )
        }

        QuizModeCard(
            title = "Kuis Tata Bahasa & Partikel",
            subtitle = "Latihan memasang partikel yang tepat dan konjugasi kata kerja.",
            questionCount = "${grammarQuestions.size} Soal",
            icon = Icons.Default.AutoStories,
            iconBg = AmberGoldLight,
            iconColor = AmberGold,
            onClick = {
                onStartQuiz("Kuis Tata Bahasa & Partikel", grammarQuestions)
            }
        )
    }
}

@Composable
fun QuizModeCard(
    title: String,
    subtitle: String,
    questionCount: String,
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
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(iconBg),
                contentAlignment = Alignment.Center
            ) {
                Icon(imageVector = icon, contentDescription = null, tint = iconColor, modifier = Modifier.size(26.dp))
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = SumiInk
                    )
                )
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall.copy(color = SumiGray)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = questionCount,
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = JapanCrimson,
                        fontWeight = FontWeight.Bold
                    )
                )
            }

            Icon(imageVector = Icons.Default.PlayArrow, contentDescription = "Mulai", tint = JapanCrimson)
        }
    }
}

@Composable
fun ActiveQuizView(
    title: String,
    questions: List<QuizQuestion>,
    onClose: () -> Unit,
    onAddXp: (Int) -> Unit,
    onSpeak: (String) -> Unit
) {
    var currentIndex by remember { mutableIntStateOf(0) }
    var selectedOptionIndex by remember { mutableIntStateOf(-1) }
    var isAnswered by remember { mutableStateOf(false) }
    var correctCount by remember { mutableIntStateOf(0) }
    var isQuizCompleted by remember { mutableStateOf(false) }

    val currentQuestion = questions.getOrNull(currentIndex)

    if (isQuizCompleted || currentQuestion == null) {
        QuizScoreSummary(
            title = title,
            totalQuestions = questions.size,
            correctCount = correctCount,
            onRestart = {
                currentIndex = 0
                selectedOptionIndex = -1
                isAnswered = false
                correctCount = 0
                isQuizCompleted = false
            },
            onClose = onClose
        )
        return
    }

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = onClose) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = "Tutup")
                    }

                    Text(
                        text = "Soal ${currentIndex + 1} dari ${questions.size}",
                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
                    )

                    Spacer(modifier = Modifier.size(48.dp))
                }

                LinearProgressIndicator(
                    progress = { ((currentIndex + 1).toFloat() / questions.size.toFloat()).coerceIn(0f, 1f) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp)
                        .clip(RoundedCornerShape(3.dp)),
                    color = JapanCrimson,
                    trackColor = WashiBorder
                )
            }
        },
        bottomBar = {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .windowInsetsPadding(WindowInsets.navigationBars),
                color = MaterialTheme.colorScheme.surface,
                tonalElevation = 8.dp
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    if (isAnswered) {
                        Button(
                            onClick = {
                                if (currentIndex + 1 < questions.size) {
                                    currentIndex++
                                    selectedOptionIndex = -1
                                    isAnswered = false
                                } else {
                                    val earnedXp = correctCount * 20
                                    onAddXp(earnedXp)
                                    isQuizCompleted = true
                                }
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = JapanCrimson),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp)
                        ) {
                            Text(
                                text = if (currentIndex + 1 < questions.size) "Soal Berikutnya" else "Lihat Hasil Kuis",
                                style = MaterialTheme.typography.labelLarge.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            )
                        }
                    }
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            // Audio prompt if listening quiz
            if (!currentQuestion.audioText.isNullOrBlank()) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = IndigoJlptLight),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Dengarkan Rekaman Audio:",
                                style = MaterialTheme.typography.labelMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = IndigoJlpt
                                )
                            )
                            Text(
                                text = "Ketuk tombol speaker untuk memutar audio",
                                style = MaterialTheme.typography.bodySmall.copy(color = SumiGray)
                            )
                        }

                        JapaneseAudioButton(
                            textToSpeak = currentQuestion.audioText,
                            onSpeak = onSpeak,
                            size = 48.dp,
                            tint = IndigoJlpt,
                            backgroundColor = Color.White
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
            }

            // Question Text
            Text(
                text = currentQuestion.prompt,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = SumiInk
                )
            )

            currentQuestion.subPrompt?.let { sub ->
                Text(
                    text = sub,
                    style = MaterialTheme.typography.bodySmall.copy(color = SumiGray),
                    modifier = Modifier.padding(top = 2.dp)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Options List
            currentQuestion.options.forEachIndexed { index, optionText ->
                val isSelected = selectedOptionIndex == index
                val isCorrect = index == currentQuestion.correctIndex

                val borderColor = when {
                    !isAnswered -> if (isSelected) JapanCrimson else WashiBorder
                    isCorrect -> MatchaGreen
                    isSelected && !isCorrect -> Color(0xFFE53935)
                    else -> WashiBorder
                }

                val bgColor = when {
                    !isAnswered -> if (isSelected) JapanCrimsonLight else MaterialTheme.colorScheme.surface
                    isCorrect -> MatchaGreenLight
                    isSelected && !isCorrect -> Color(0xFFFFEBEE)
                    else -> MaterialTheme.colorScheme.surface
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                        .clickable(enabled = !isAnswered) {
                            selectedOptionIndex = index
                            isAnswered = true
                            if (index == currentQuestion.correctIndex) {
                                correctCount++
                            }
                        }
                        .border(1.5.dp, borderColor, RoundedCornerShape(14.dp)),
                    colors = CardDefaults.cardColors(containerColor = bgColor),
                    shape = RoundedCornerShape(14.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = optionText,
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                color = SumiInk
                            ),
                            modifier = Modifier.weight(1f)
                        )

                        if (isAnswered) {
                            if (isCorrect) {
                                Icon(Icons.Default.CheckCircle, contentDescription = "Benar", tint = MatchaGreen)
                            } else if (isSelected) {
                                Icon(Icons.Default.Cancel, contentDescription = "Salah", tint = Color(0xFFE53935))
                            }
                        }
                    }
                }
            }

            // Explanation box when answered
            if (isAnswered) {
                Spacer(modifier = Modifier.height(16.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = if (selectedOptionIndex == currentQuestion.correctIndex) MatchaGreenLight else Color(0xFFFFF4E5)
                    ),
                    shape = RoundedCornerShape(14.dp)
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = if (selectedOptionIndex == currentQuestion.correctIndex) Icons.Default.CheckCircle else Icons.Default.Info,
                                contentDescription = null,
                                tint = if (selectedOptionIndex == currentQuestion.correctIndex) MatchaGreen else AmberGold
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = if (selectedOptionIndex == currentQuestion.correctIndex) "Jawaban Benar!" else "Penjelasan:",
                                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold)
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = currentQuestion.explanation,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = SumiInk,
                                lineHeight = 20.sp
                            )
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun QuizScoreSummary(
    title: String,
    totalQuestions: Int,
    correctCount: Int,
    onRestart: () -> Unit,
    onClose: () -> Unit
) {
    val percentage = ((correctCount.toFloat() / totalQuestions.toFloat()) * 100).toInt()
    val earnedXp = correctCount * 20

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            shape = RoundedCornerShape(24.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .background(if (percentage >= 70) MatchaGreenLight else AmberGoldLight),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = if (percentage >= 70) Icons.Default.EmojiEvents else Icons.Default.ThumbUp,
                        contentDescription = null,
                        tint = if (percentage >= 70) MatchaGreen else AmberGold,
                        modifier = Modifier.size(48.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = if (percentage >= 70) "Luar Biasa! (お疲れ様)" else "Terus Berlatih!",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                )

                Text(
                    text = title,
                    style = MaterialTheme.typography.bodySmall.copy(color = SumiGray)
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "$percentage%",
                    style = MaterialTheme.typography.displayMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = JapanCrimson
                    )
                )

                Text(
                    text = "Benar $correctCount dari $totalQuestions Soal",
                    style = MaterialTheme.typography.bodyMedium.copy(color = SumiInk)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Card(
                    colors = CardDefaults.cardColors(containerColor = IndigoJlptLight),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(imageVector = Icons.Default.Bolt, contentDescription = null, tint = IndigoJlpt)
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "+$earnedXp XP Didapatkan",
                            style = MaterialTheme.typography.labelMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = IndigoJlpt
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    OutlinedButton(
                        onClick = onRestart,
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Coba Lagi")
                    }

                    Button(
                        onClick = onClose,
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = JapanCrimson),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Selesai")
                    }
                }
            }
        }
    }
}
