package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.data.model.*
import com.example.data.repository.JapaneseContentRepository
import com.example.ui.MainViewModel
import com.example.ui.components.JapaneseTopBar
import com.example.ui.components.PaywallDialog
import com.example.ui.navigation.NavDestination
import com.example.ui.screens.home.HomeScreen
import com.example.ui.screens.learn.LearnScreen
import com.example.ui.screens.learn.dialogs.*
import com.example.ui.screens.onboarding.OnboardingScreen
import com.example.ui.screens.practice.PracticeScreen
import com.example.ui.screens.profile.ProfileScreen
import com.example.ui.screens.progress.ProgressScreen
import com.example.ui.screens.review.ReviewScreen
import com.example.ui.screens.search.SearchScreen
import com.example.ui.theme.JapanCrimson
import com.example.ui.theme.NihongoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        setContent {
            NihongoTheme {
                val userProfile by viewModel.userProfile.collectAsStateWithLifecycle()
                val dueItems by viewModel.dueReviewItems.collectAsStateWithLifecycle()
                val dueCount by viewModel.dueCount.collectAsStateWithLifecycle()
                val weeklyRecords by viewModel.weeklyRecords.collectAsStateWithLifecycle()
                val isSlowSpeech by viewModel.isSlowSpeechRate.collectAsStateWithLifecycle()

                // If onboarding not completed yet, show OnboardingScreen
                val isOnboardingNeeded = userProfile != null && !userProfile!!.onboardingCompleted

                if (isOnboardingNeeded) {
                    OnboardingScreen(
                        onComplete = { level, goal, minutes, vocabTarget ->
                            viewModel.completeOnboarding(level, goal, minutes, vocabTarget)
                        }
                    )
                } else {
                    NihongoMainApp(
                        viewModel = viewModel,
                        userProfile = userProfile,
                        dueItems = dueItems,
                        dueCount = dueCount,
                        weeklyRecords = weeklyRecords,
                        isSlowSpeech = isSlowSpeech
                    )
                }
            }
        }
    }
}

@Composable
fun NihongoMainApp(
    viewModel: MainViewModel,
    userProfile: com.example.data.local.UserProfileEntity?,
    dueItems: List<com.example.data.local.ReviewItemEntity>,
    dueCount: Int,
    weeklyRecords: List<com.example.data.local.DailyStudyRecordEntity>,
    isSlowSpeech: Boolean
) {
    var selectedTab by remember { mutableStateOf(NavDestination.HOME) }
    var isSearchOpen by remember { mutableStateOf(false) }
    var isPaywallOpen by remember { mutableStateOf(false) }

    // Dialog inspection states
    var selectedKana by remember { mutableStateOf<KanaCharacter?>(null) }
    var selectedVocab by remember { mutableStateOf<VocabularyItem?>(null) }
    var selectedKanji by remember { mutableStateOf<KanjiItem?>(null) }
    var selectedGrammar by remember { mutableStateOf<GrammarItem?>(null) }
    var selectedConversation by remember { mutableStateOf<ConversationItem?>(null) }

    val dailyVocab = remember {
        JapaneseContentRepository.vocabularyList.firstOrNull() ?: VocabularyItem(
            id = "default_v",
            japanese = "水",
            furigana = "みず",
            romaji = "mizu",
            meaningId = "Air",
            category = "Minuman & Makanan",
            jlptLevel = JlptLevel.N5,
            exampleJp = "水を飲みます。",
            exampleFurigana = "みず を のみます。",
            exampleRomaji = "Mizu o nomimasu.",
            exampleMeaningId = "Saya minum air."
        )
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            JapaneseTopBar(
                streak = userProfile?.currentStreak ?: 1,
                xp = userProfile?.totalXp ?: 0,
                isPremium = userProfile?.isPremium ?: false,
                onOpenSearch = { isSearchOpen = true },
                onOpenPaywall = { isPaywallOpen = true }
            )
        },
        bottomBar = {
            NavigationBar(
                modifier = Modifier.testTag("bottom_nav_bar"),
                containerColor = MaterialTheme.colorScheme.surface,
                tonalElevation = 6.dp
            ) {
                NavDestination.entries.forEach { destination ->
                    val isSelected = selectedTab == destination
                    NavigationBarItem(
                        selected = isSelected,
                        onClick = { selectedTab = destination },
                        icon = {
                            Icon(
                                imageVector = if (isSelected) destination.selectedIcon else destination.unselectedIcon,
                                contentDescription = destination.title
                            )
                        },
                        label = {
                            Text(
                                text = destination.title,
                                style = MaterialTheme.typography.labelSmall
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = JapanCrimson,
                            selectedTextColor = JapanCrimson,
                            indicatorColor = com.example.ui.theme.JapanCrimsonLight
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (selectedTab) {
                NavDestination.HOME -> HomeScreen(
                    userProfile = userProfile,
                    dueReviewCount = dueCount,
                    weeklyRecords = weeklyRecords,
                    dailyLessonVocab = dailyVocab,
                    onNavigateTab = { selectedTab = it },
                    onOpenVocabDetail = { selectedVocab = it },
                    onSpeak = { viewModel.speak(it) }
                )

                NavDestination.LEARN -> LearnScreen(
                    onOpenKanaDetail = { selectedKana = it },
                    onOpenVocabDetail = { selectedVocab = it },
                    onOpenKanjiDetail = { selectedKanji = it },
                    onOpenGrammarDetail = { selectedGrammar = it },
                    onOpenConversationDetail = { selectedConversation = it },
                    onSpeak = { viewModel.speak(it) }
                )

                NavDestination.REVIEW -> ReviewScreen(
                    dueItems = dueItems,
                    onRateItem = { item, rating ->
                        viewModel.rateReviewItem(item, rating)
                    },
                    onSpeak = { viewModel.speak(it) }
                )

                NavDestination.PRACTICE -> PracticeScreen(
                    onAddXp = { viewModel.addXp(it) },
                    onSpeak = { viewModel.speak(it) }
                )

                NavDestination.PROGRESS -> ProgressScreen(
                    userProfile = userProfile,
                    onClaimMission = { xp -> viewModel.addXp(xp) }
                )

                NavDestination.PROFILE -> ProfileScreen(
                    userProfile = userProfile,
                    isSlowSpeechRate = isSlowSpeech,
                    onToggleSlowSpeech = { viewModel.toggleSlowSpeech() },
                    onOpenPaywall = { isPaywallOpen = true },
                    onRestartOnboarding = { viewModel.resetOnboarding() }
                )
            }
        }
    }

    // Search Fullscreen Modal
    if (isSearchOpen) {
        SearchScreen(
            onClose = { isSearchOpen = false },
            onOpenVocabDetail = { selectedVocab = it },
            onOpenKanjiDetail = { selectedKanji = it },
            onOpenGrammarDetail = { selectedGrammar = it },
            onSpeak = { viewModel.speak(it) }
        )
    }

    // Freemium Paywall Dialog
    if (isPaywallOpen) {
        PaywallDialog(
            isCurrentlyPremium = userProfile?.isPremium ?: false,
            onDismiss = { isPaywallOpen = false },
            onTogglePremium = { viewModel.togglePremium(it) }
        )
    }

    // Detail Popups
    selectedKana?.let { kana ->
        KanaDetailDialog(
            kana = kana,
            onDismiss = { selectedKana = null },
            onSpeak = { viewModel.speak(it) }
        )
    }

    selectedVocab?.let { vocab ->
        VocabDetailDialog(
            vocab = vocab,
            isFavorite = false,
            onToggleFavorite = {
                viewModel.toggleFavorite(vocab.id, "VOCAB", vocab.japanese, vocab.meaningId, false)
            },
            onDismiss = { selectedVocab = null },
            onSpeak = { viewModel.speak(it) }
        )
    }

    selectedKanji?.let { kanji ->
        KanjiDetailDialog(
            kanji = kanji,
            onDismiss = { selectedKanji = null },
            onSpeak = { viewModel.speak(it) }
        )
    }

    selectedGrammar?.let { grammar ->
        GrammarDetailDialog(
            grammar = grammar,
            onDismiss = { selectedGrammar = null },
            onSpeak = { viewModel.speak(it) }
        )
    }

    selectedConversation?.let { conversation ->
        ConversationDetailDialog(
            conversation = conversation,
            onDismiss = { selectedConversation = null },
            onSpeak = { viewModel.speak(it) }
        )
    }
}
