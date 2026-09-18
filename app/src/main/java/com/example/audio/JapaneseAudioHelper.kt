package com.example.audio

import android.content.Context
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Locale

class JapaneseAudioHelper(context: Context) {
    private var textToSpeech: TextToSpeech? = null
    private var isInitialized = false

    private val _isSpeaking = MutableStateFlow(false)
    val isSpeaking: StateFlow<Boolean> = _isSpeaking.asStateFlow()

    private val _isSlowRate = MutableStateFlow(false)
    val isSlowRate: StateFlow<Boolean> = _isSlowRate.asStateFlow()

    init {
        textToSpeech = TextToSpeech(context.applicationContext) { status ->
            if (status == TextToSpeech.SUCCESS) {
                val result = textToSpeech?.setLanguage(Locale.JAPANESE)
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    // Try generic Locale.JAPAN
                    textToSpeech?.setLanguage(Locale.JAPAN)
                    Log.w("AudioHelper", "Japanese TTS missing data or not supported")
                } else {
                    isInitialized = true
                }
            } else {
                Log.e("AudioHelper", "TextToSpeech init failed with status: $status")
            }
        }

        textToSpeech?.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
            override fun onStart(utteranceId: String?) {
                _isSpeaking.value = true
            }

            override fun onDone(utteranceId: String?) {
                _isSpeaking.value = false
            }

            @Deprecated("Deprecated in Java")
            override fun onError(utteranceId: String?) {
                _isSpeaking.value = false
            }

            override fun onError(utteranceId: String?, errorCode: Int) {
                _isSpeaking.value = false
            }
        })
    }

    fun toggleSlowRate() {
        _isSlowRate.value = !_isSlowRate.value
    }

    fun speak(text: String, slow: Boolean = _isSlowRate.value) {
        if (textToSpeech == null) return
        try {
            val speechRate = if (slow) 0.7f else 0.95f
            textToSpeech?.setSpeechRate(speechRate)
            textToSpeech?.setPitch(1.0f)
            val utteranceId = System.currentTimeMillis().toString()
            textToSpeech?.speak(text, TextToSpeech.QUEUE_FLUSH, null, utteranceId)
        } catch (e: Exception) {
            Log.e("AudioHelper", "Failed to speak", e)
            _isSpeaking.value = false
        }
    }

    fun stop() {
        textToSpeech?.stop()
        _isSpeaking.value = false
    }

    fun shutdown() {
        textToSpeech?.stop()
        textToSpeech?.shutdown()
        textToSpeech = null
        isInitialized = false
    }
}
