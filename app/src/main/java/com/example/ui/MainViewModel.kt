package com.example.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.audio.JapaneseAudioHelper
import com.example.data.local.AppDatabase
import com.example.data.local.DailyStudyRecordEntity
import com.example.data.local.ReviewItemEntity
import com.example.data.local.UserProfileEntity
import com.example.data.repository.UserPreferencesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val database = AppDatabase.getDatabase(application)
    val preferencesRepository = UserPreferencesRepository(
        userDao = database.userDao(),
        reviewDao = database.reviewDao(),
        studyRecordDao = database.studyRecordDao(),
        favoriteDao = database.favoriteDao()
    )

    val audioHelper = JapaneseAudioHelper(application)

    val userProfile: StateFlow<UserProfileEntity?> = preferencesRepository.userProfile
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    val dueReviewItems: StateFlow<List<ReviewItemEntity>> = preferencesRepository.getDueReviewItems()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val dueCount: StateFlow<Int> = preferencesRepository.getDueCount()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    val weeklyRecords: StateFlow<List<DailyStudyRecordEntity>> = preferencesRepository.recentWeeklyRecords
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val isSlowSpeechRate = audioHelper.isSlowRate

    init {
        viewModelScope.launch {
            preferencesRepository.checkAndInitialize()
        }
    }

    fun completeOnboarding(level: String, goal: String, minutes: Int, vocabTarget: Int) {
        viewModelScope.launch {
            preferencesRepository.completeOnboarding(level, goal, minutes, vocabTarget)
        }
    }

    fun resetOnboarding() {
        viewModelScope.launch {
            val current = userProfile.value
            if (current != null) {
                database.userDao().insertOrUpdate(current.copy(onboardingCompleted = false))
            }
        }
    }

    fun rateReviewItem(item: ReviewItemEntity, rating: Int) {
        viewModelScope.launch {
            preferencesRepository.processReviewRating(item, rating)
        }
    }

    fun addXp(amount: Int) {
        viewModelScope.launch {
            preferencesRepository.addXp(amount)
        }
    }

    fun togglePremium(isPremium: Boolean) {
        viewModelScope.launch {
            preferencesRepository.togglePremium(isPremium)
        }
    }

    fun toggleFavorite(id: String, type: String, title: String, subtitle: String, isCurrent: Boolean) {
        viewModelScope.launch {
            preferencesRepository.toggleFavorite(id, type, title, subtitle, isCurrent)
        }
    }

    fun toggleSlowSpeech() {
        audioHelper.toggleSlowRate()
    }

    fun speak(text: String) {
        audioHelper.speak(text)
    }

    override fun onCleared() {
        super.onCleared()
        audioHelper.shutdown()
    }
}
