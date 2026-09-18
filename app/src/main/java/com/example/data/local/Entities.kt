package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_profile")
data class UserProfileEntity(
    @PrimaryKey val id: Int = 1,
    val level: String = "BEGINNER",
    val goal: String = "JLPT",
    val dailyTargetMinutes: Int = 15,
    val dailyVocabTarget: Int = 10,
    val currentStreak: Int = 3,
    val bestStreak: Int = 7,
    val lastStudyDate: String = "",
    val totalXp: Int = 240,
    val currentLevel: Int = 2,
    val isPremium: Boolean = false,
    val onboardingCompleted: Boolean = false
)

@Entity(tableName = "review_items")
data class ReviewItemEntity(
    @PrimaryKey val id: String,
    val itemType: String, // VOCABULARY, KANJI, GRAMMAR, KANA
    val itemId: String,
    val japaneseText: String,
    val reading: String,
    val meaningId: String,
    val nextReviewTimestamp: Long,
    val intervalDays: Int,
    val repetitions: Int,
    val easeFactor: Float = 2.5f,
    val lastReviewedTimestamp: Long = System.currentTimeMillis()
)

@Entity(tableName = "daily_study_records")
data class DailyStudyRecordEntity(
    @PrimaryKey val dateString: String, // YYYY-MM-DD
    val minutesStudied: Int = 0,
    val wordsLearned: Int = 0,
    val quizzesTaken: Int = 0,
    val xpEarned: Int = 0
)

@Entity(tableName = "favorites")
data class FavoriteEntity(
    @PrimaryKey val id: String,
    val itemType: String,
    val title: String,
    val subtitle: String,
    val timestamp: Long = System.currentTimeMillis()
)
