package com.example.data.repository

import com.example.data.local.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class UserPreferencesRepository(
    private val userDao: UserDao,
    private val reviewDao: ReviewDao,
    private val studyRecordDao: StudyRecordDao,
    private val favoriteDao: FavoriteDao
) {
    val userProfile: Flow<UserProfileEntity?> = userDao.getUserProfile()
    val allReviewItems: Flow<List<ReviewItemEntity>> = reviewDao.getAllReviewItems()
    val recentWeeklyRecords: Flow<List<DailyStudyRecordEntity>> = studyRecordDao.getRecentWeeklyRecords()
    val allFavorites: Flow<List<FavoriteEntity>> = favoriteDao.getAllFavorites()

    fun getDueReviewItems(currentTime: Long = System.currentTimeMillis()): Flow<List<ReviewItemEntity>> {
        return reviewDao.getDueReviewItems(currentTime)
    }

    fun getDueCount(currentTime: Long = System.currentTimeMillis()): Flow<Int> {
        return reviewDao.getDueCount(currentTime)
    }

    suspend fun checkAndInitialize() {
        val existingUser = userDao.getUserProfile().firstOrNull()
        if (existingUser == null) {
            val defaultUser = UserProfileEntity(
                id = 1,
                level = "BEGINNER",
                goal = "JLPT",
                dailyTargetMinutes = 15,
                dailyVocabTarget = 10,
                currentStreak = 3,
                bestStreak = 7,
                lastStudyDate = getTodayDateString(),
                totalXp = 380,
                currentLevel = 2,
                isPremium = false,
                onboardingCompleted = false
            )
            userDao.insertOrUpdate(defaultUser)
        }

        // Initialize starter review queue if empty
        val existingReviews = reviewDao.getAllReviewItems().firstOrNull()
        if (existingReviews.isNullOrEmpty()) {
            val starterReviews = listOf(
                ReviewItemEntity(
                    id = "rev_v1",
                    itemType = "VOCABULARY",
                    itemId = "v1",
                    japaneseText = "一",
                    reading = "いち (ichi)",
                    meaningId = "Satu (1)",
                    nextReviewTimestamp = System.currentTimeMillis() - 1000,
                    intervalDays = 1,
                    repetitions = 1,
                    easeFactor = 2.5f
                ),
                ReviewItemEntity(
                    id = "rev_v6",
                    itemType = "VOCABULARY",
                    itemId = "v6",
                    japaneseText = "今日",
                    reading = "きょう (kyou)",
                    meaningId = "Hari ini",
                    nextReviewTimestamp = System.currentTimeMillis() - 1000,
                    intervalDays = 1,
                    repetitions = 2,
                    easeFactor = 2.5f
                ),
                ReviewItemEntity(
                    id = "rev_k1",
                    itemType = "KANJI",
                    itemId = "k1",
                    japaneseText = "日",
                    reading = "ひ / ニチ",
                    meaningId = "Matahari, Hari",
                    nextReviewTimestamp = System.currentTimeMillis() - 1000,
                    intervalDays = 2,
                    repetitions = 1,
                    easeFactor = 2.4f
                ),
                ReviewItemEntity(
                    id = "rev_v15",
                    itemType = "VOCABULARY",
                    itemId = "v15",
                    japaneseText = "ご飯",
                    reading = "ごはん (gohan)",
                    meaningId = "Nasi / Makanan",
                    nextReviewTimestamp = System.currentTimeMillis() - 1000,
                    intervalDays = 1,
                    repetitions = 0,
                    easeFactor = 2.5f
                ),
                ReviewItemEntity(
                    id = "rev_v40",
                    itemType = "VOCABULARY",
                    itemId = "v40",
                    japaneseText = "ありがとう",
                    reading = "ありがとう (arigatou)",
                    meaningId = "Terima kasih",
                    nextReviewTimestamp = System.currentTimeMillis() - 1000,
                    intervalDays = 3,
                    repetitions = 2,
                    easeFactor = 2.6f
                )
            )
            reviewDao.insertItems(starterReviews)
        }

        // Initialize weekly records if empty
        val today = getTodayDateString()
        if (studyRecordDao.getRecordForDate(today) == null) {
            studyRecordDao.insertOrUpdate(
                DailyStudyRecordEntity(
                    dateString = today,
                    minutesStudied = 12,
                    wordsLearned = 8,
                    quizzesTaken = 2,
                    xpEarned = 60
                )
            )
        }
    }

    suspend fun completeOnboarding(level: String, goal: String, minutes: Int, vocabTarget: Int) {
        userDao.completeOnboarding(level, goal, minutes, vocabTarget)
    }

    suspend fun addXp(amount: Int) {
        userDao.addXp(amount)
        recordStudyActivity(minutes = 3, words = 1, quiz = 1, xp = amount)
    }

    suspend fun recordStudyActivity(minutes: Int, words: Int, quiz: Int, xp: Int) {
        val today = getTodayDateString()
        val current = studyRecordDao.getRecordForDate(today) ?: DailyStudyRecordEntity(today)
        studyRecordDao.insertOrUpdate(
            current.copy(
                minutesStudied = current.minutesStudied + minutes,
                wordsLearned = current.wordsLearned + words,
                quizzesTaken = current.quizzesTaken + quiz,
                xpEarned = current.xpEarned + xp
            )
        )
    }

    suspend fun togglePremium(isPremium: Boolean) {
        userDao.setPremiumStatus(isPremium)
    }

    suspend fun toggleFavorite(id: String, itemType: String, title: String, subtitle: String, isCurrentFav: Boolean) {
        if (isCurrentFav) {
            favoriteDao.removeFavorite(id)
        } else {
            favoriteDao.addFavorite(
                FavoriteEntity(
                    id = id,
                    itemType = itemType,
                    title = title,
                    subtitle = subtitle
                )
            )
        }
    }

    suspend fun isFavorite(id: String): Flow<Boolean> = favoriteDao.isFavorite(id)

    // Spaced Repetition (SRS) rating process
    suspend fun processReviewRating(item: ReviewItemEntity, rating: Int) {
        // rating: 0 = Again (Lupa), 1 = Hard (Sulit), 2 = Good (Baik), 3 = Easy (Mudah)
        val now = System.currentTimeMillis()
        val dayMillis = 24L * 60 * 60 * 1000

        val newRepetitions: Int
        val newIntervalDays: Int
        var newEase = item.easeFactor

        when (rating) {
            0 -> { // Again
                newRepetitions = 0
                newIntervalDays = 1
                newEase = maxOf(1.3f, item.easeFactor - 0.2f)
            }
            1 -> { // Hard
                newRepetitions = item.repetitions + 1
                newIntervalDays = maxOf(1, (item.intervalDays * 1.2f).toInt())
                newEase = maxOf(1.3f, item.easeFactor - 0.15f)
            }
            2 -> { // Good
                newRepetitions = item.repetitions + 1
                newIntervalDays = when (item.repetitions) {
                    0 -> 1
                    1 -> 3
                    else -> maxOf(2, (item.intervalDays * item.easeFactor).toInt())
                }
            }
            else -> { // Easy
                newRepetitions = item.repetitions + 1
                newIntervalDays = when (item.repetitions) {
                    0 -> 3
                    1 -> 6
                    else -> maxOf(4, (item.intervalDays * item.easeFactor * 1.3f).toInt())
                }
                newEase = item.easeFactor + 0.15f
            }
        }

        val nextTimestamp = now + (newIntervalDays * dayMillis)
        val updated = item.copy(
            intervalDays = newIntervalDays,
            repetitions = newRepetitions,
            easeFactor = newEase,
            nextReviewTimestamp = nextTimestamp,
            lastReviewedTimestamp = now
        )

        reviewDao.updateItem(updated)
        addXp(15)
    }

    fun getTodayDateString(): String {
        return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
    }
}
