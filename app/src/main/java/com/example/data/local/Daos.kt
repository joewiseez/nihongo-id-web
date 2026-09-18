package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM user_profile WHERE id = 1")
    fun getUserProfile(): Flow<UserProfileEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(user: UserProfileEntity)

    @Query("UPDATE user_profile SET totalXp = totalXp + :xpToAdd WHERE id = 1")
    suspend fun addXp(xpToAdd: Int)

    @Query("UPDATE user_profile SET currentStreak = :streak, lastStudyDate = :today WHERE id = 1")
    suspend fun updateStreak(streak: Int, today: String)

    @Query("UPDATE user_profile SET isPremium = :premium WHERE id = 1")
    suspend fun setPremiumStatus(premium: Boolean)

    @Query("UPDATE user_profile SET onboardingCompleted = 1, level = :level, goal = :goal, dailyTargetMinutes = :minutes, dailyVocabTarget = :vocabTarget WHERE id = 1")
    suspend fun completeOnboarding(level: String, goal: String, minutes: Int, vocabTarget: Int)
}

@Dao
interface ReviewDao {
    @Query("SELECT * FROM review_items ORDER BY nextReviewTimestamp ASC")
    fun getAllReviewItems(): Flow<List<ReviewItemEntity>>

    @Query("SELECT * FROM review_items WHERE nextReviewTimestamp <= :currentTimeMillis ORDER BY nextReviewTimestamp ASC")
    fun getDueReviewItems(currentTimeMillis: Long): Flow<List<ReviewItemEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: ReviewItemEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItems(items: List<ReviewItemEntity>)

    @Update
    suspend fun updateItem(item: ReviewItemEntity)

    @Query("DELETE FROM review_items WHERE id = :id")
    suspend fun deleteItem(id: String)

    @Query("SELECT COUNT(*) FROM review_items WHERE nextReviewTimestamp <= :currentTimeMillis")
    fun getDueCount(currentTimeMillis: Long): Flow<Int>
}

@Dao
interface StudyRecordDao {
    @Query("SELECT * FROM daily_study_records ORDER BY dateString DESC LIMIT 7")
    fun getRecentWeeklyRecords(): Flow<List<DailyStudyRecordEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(record: DailyStudyRecordEntity)

    @Query("SELECT * FROM daily_study_records WHERE dateString = :dateString LIMIT 1")
    suspend fun getRecordForDate(dateString: String): DailyStudyRecordEntity?
}

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM favorites ORDER BY timestamp DESC")
    fun getAllFavorites(): Flow<List<FavoriteEntity>>

    @Query("SELECT EXISTS(SELECT 1 FROM favorites WHERE id = :id)")
    fun isFavorite(id: String): Flow<Boolean>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(favorite: FavoriteEntity)

    @Query("DELETE FROM favorites WHERE id = :id")
    suspend fun removeFavorite(id: String)
}
