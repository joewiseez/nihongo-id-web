package com.example.data.model

enum class JlptLevel(val displayName: String) {
    BEGINNER("Pemula"),
    N5("JLPT N5"),
    N4("JLPT N4")
}

enum class KanaType(val groupTitle: String) {
    HIRAGANA_SEION("Hiragana Dasar (Seion)"),
    HIRAGANA_DAKUTEN("Hiragana Dakuten / Handakuten"),
    HIRAGANA_YOON("Hiragana Gabungan (Yoon)"),
    KATAKANA_SEION("Katakana Dasar (Seion)"),
    KATAKANA_DAKUTEN("Katakana Dakuten / Handakuten"),
    KATAKANA_YOON("Katakana Gabungan (Yoon)")
}

data class KanaCharacter(
    val char: String,
    val romaji: String,
    val type: KanaType,
    val pronunciationTip: String,
    val exampleWord: String,
    val exampleMeaningId: String,
    val strokeCount: Int
)

data class VocabularyItem(
    val id: String,
    val japanese: String,
    val furigana: String,
    val romaji: String,
    val meaningId: String,
    val exampleJp: String,
    val exampleFurigana: String,
    val exampleRomaji: String,
    val exampleMeaningId: String,
    val jlptLevel: JlptLevel,
    val category: String,
    val difficulty: String = "N5"
)

data class KanjiCompound(
    val word: String,
    val reading: String,
    val meaningId: String
)

data class KanjiItem(
    val id: String,
    val character: String,
    val meaningId: String,
    val onyomi: String,
    val kunyomi: String,
    val strokeCount: Int,
    val jlptLevel: JlptLevel,
    val examples: List<KanjiCompound>,
    val exampleSentenceJp: String,
    val exampleSentenceFurigana: String,
    val exampleSentenceId: String
)

data class GrammarExample(
    val jp: String,
    val furigana: String,
    val meaningId: String
)

data class GrammarItem(
    val id: String,
    val pattern: String,
    val title: String,
    val explanationId: String,
    val usageWhen: String,
    val commonMistakesId: String,
    val jlptLevel: JlptLevel,
    val exampleSentences: List<GrammarExample>,
    val miniQuizQuestion: String,
    val miniQuizOptions: List<String>,
    val miniQuizCorrectIndex: Int,
    val miniQuizExplanation: String
)

data class DialogueLine(
    val speaker: String,
    val isUser: Boolean = false,
    val japanese: String,
    val furigana: String,
    val romaji: String,
    val indonesian: String
)

data class ConversationItem(
    val id: String,
    val category: String,
    val title: String,
    val descriptionId: String,
    val culturalNote: String,
    val dialogueLines: List<DialogueLine>
)

data class ListeningExercise(
    val id: String,
    val title: String,
    val jlptLevel: JlptLevel,
    val audioPrompt: String,
    val furigana: String,
    val romaji: String,
    val indonesianTranslation: String,
    val question: String,
    val options: List<String>,
    val correctIndex: Int,
    val explanation: String
)

enum class QuizType {
    JP_TO_ID,
    ID_TO_JP,
    LISTENING,
    KANJI,
    KANA,
    GRAMMAR
}

data class QuizQuestion(
    val id: String,
    val type: QuizType,
    val prompt: String,
    val subPrompt: String? = null,
    val audioText: String? = null,
    val options: List<String>,
    val correctIndex: Int,
    val explanation: String
)

data class DailyMission(
    val id: String,
    val title: String,
    val description: String,
    val targetCount: Int,
    val currentCount: Int,
    val xpReward: Int,
    val isCompleted: Boolean
)

data class AchievementBadge(
    val id: String,
    val title: String,
    val description: String,
    val iconKanji: String,
    val isUnlocked: Boolean
)

