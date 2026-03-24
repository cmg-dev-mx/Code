package mx.dev.cmg.android.code.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.scene.DialogSceneStrategy
import androidx.navigation3.ui.NavDisplay
import kotlinx.serialization.Serializable
import mx.dev.cmg.android.code.ui.feature.aiconversation.layout.AiConversationScreen
import mx.dev.cmg.android.code.ui.feature.aiconversation.viewmodel.AiConversationSideEffect
import mx.dev.cmg.android.code.ui.feature.crash.layout.CrashScreen
import mx.dev.cmg.android.code.ui.feature.crash.viewmodel.CrashSideEffect
import mx.dev.cmg.android.code.ui.feature.datapersistence.layout.DataPersistenceScreen
import mx.dev.cmg.android.code.ui.feature.datapersistence.layout.NoteDetailDialog
import mx.dev.cmg.android.code.ui.feature.datapersistence.viewmodel.DataPersistenceSideEffect
import mx.dev.cmg.android.code.ui.feature.datapersistence.viewmodel.NoteDetailSideEffect
import mx.dev.cmg.android.code.ui.feature.main.layout.MainScreen
import mx.dev.cmg.android.code.ui.feature.main.viewmodel.MainSideEffect
import mx.dev.cmg.android.code.ui.feature.mvidemo.layout.NameListScreen
import mx.dev.cmg.android.code.ui.feature.mvidemo.viewmodel.NameListSideEffect
import mx.dev.cmg.android.code.ui.feature.rest.layout.RestScreen
import mx.dev.cmg.android.code.ui.feature.rest.viewmodel.RestSideEffect
import mx.dev.cmg.android.code.ui.feature.sharedpreferences.layout.SharedPreferencesScreen
import mx.dev.cmg.android.code.ui.feature.sharedpreferences.viewmodel.SharedPreferencesSideEffect
import mx.dev.cmg.android.code.ui.feature.textspeech.layout.TextSpeechScreen
import mx.dev.cmg.android.code.ui.feature.textspeech.viewmodel.TextSpeechSideEffect
import mx.dev.cmg.android.code.ui.feature.web.layout.WebMenuScreen
import mx.dev.cmg.android.code.ui.feature.web.layout.WebScreen
import mx.dev.cmg.android.code.ui.feature.web.viewmodel.WebMenuSideEffect
import mx.dev.cmg.android.code.ui.feature.web.viewmodel.WebSideEffect
import mx.dev.cmg.android.code.ui.util.launchCustomTab

@Serializable
data object Main : NavKey

@Serializable
data object NameList : NavKey

@Serializable
data object Crashlytics : NavKey

@Serializable
data object DataPersistence : NavKey

@Serializable
data class NoteDetail(val id: Int) : NavKey

@Serializable
data object SharedPreferences : NavKey

@Serializable
data object RestApi : NavKey

@Serializable
data object Web : NavKey

@Serializable
data class InternalWeb(val url: String) : NavKey

@Serializable
data object AiConversation : NavKey

@Serializable
data object TextSpeech : NavKey

@Composable
fun MainNavHost(modifier: Modifier = Modifier) {

    val context = LocalContext.current

    val backStack = rememberNavBackStack(Main)
    val dialogStrategy = remember { DialogSceneStrategy<NavKey>() }

    NavDisplay(
        modifier = modifier,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        backStack = backStack,
        sceneStrategy = dialogStrategy,
        entryProvider = entryProvider {
            entry<Main> {
                MainScreen(
                    modifier = Modifier.fillMaxSize(),
                    onNavigation = { sideEffect ->
                        when (sideEffect) {
                            MainSideEffect.NavigateToNameList ->
                                backStack.navigateTo(NameList)

                            MainSideEffect.NavigateToCrashlytics ->
                                backStack.navigateTo(Crashlytics)

                            MainSideEffect.NavigateToPersistence ->
                                backStack.navigateTo(DataPersistence)

                            MainSideEffect.NavigateToSharedPreferences ->
                                backStack.navigateTo(SharedPreferences)

                            MainSideEffect.NavigateToRestApi ->
                                backStack.navigateTo(RestApi)

                            MainSideEffect.NavigateToWebView ->
                                backStack.navigateTo(Web)

                            MainSideEffect.NavigateToAiConversation ->
                                backStack.navigateTo(AiConversation)

                            MainSideEffect.NavigateToTextSpeech ->
                                backStack.navigateTo(TextSpeech)
                        }
                    }
                )
            }

            entry<NameList> {
                NameListScreen(
                    modifier = Modifier.fillMaxSize(),
                    onNavigation = { sideEffect ->
                        when (sideEffect) {
                            NameListSideEffect.NavigateBack ->
                                backStack.navigateBack()
                        }
                    }
                )
            }

            entry<Crashlytics> {
                CrashScreen(
                    modifier = Modifier.fillMaxSize(),
                    onNavigation = { sideEffect ->
                        when (sideEffect) {
                            CrashSideEffect.NavigateBack ->
                                backStack.navigateBack()
                        }
                    }
                )
            }

            entry<DataPersistence> {
                DataPersistenceScreen(
                    modifier = Modifier.fillMaxSize(),
                    onNavigation = { sideEffect ->
                        when (sideEffect) {
                            is DataPersistenceSideEffect.NavigateBack -> backStack.navigateBack()
                            is DataPersistenceSideEffect.NoteDetail -> backStack.navigateTo(
                                NoteDetail(sideEffect.id)
                            )
                        }
                    }
                )
            }

            entry<NoteDetail>(
                metadata = DialogSceneStrategy.dialog()
            ) { entry ->
                NoteDetailDialog(
                    modifier = Modifier.fillMaxWidth(),
                    noteId = entry.id,
                    onNavigation = { sideEffect ->
                        when (sideEffect) {
                            NoteDetailSideEffect.NavigateBack -> backStack.navigateBack()
                        }
                    }
                )
            }

            entry<SharedPreferences> {
                SharedPreferencesScreen(
                    onNavigation = { sideEffect ->
                        when (sideEffect) {
                            is SharedPreferencesSideEffect.NavigateBack -> backStack.navigateBack()
                            else -> {}
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                )
            }

            entry<RestApi> {
                RestScreen(
                    onNavigation = { sideEffect ->
                        when (sideEffect) {
                            is RestSideEffect.NavigateBack -> backStack.navigateBack()
                            else -> {}
                        }

                    },
                    modifier = Modifier.fillMaxSize()
                )
            }

            entry<Web> {
                WebMenuScreen(
                    modifier = Modifier.fillMaxSize(),
                    onNavigation = { sideEffect ->
                        when (sideEffect) {
                            is WebMenuSideEffect.NavigateBack -> backStack.navigateBack()
                            is WebMenuSideEffect.OpenWebInLayout -> backStack.navigateTo(
                                InternalWeb(url = sideEffect.url)
                            )

                            is WebMenuSideEffect.OpenCustomTab -> {
                                launchCustomTab(context = context, sideEffect.url)
                            }
                        }
                    }
                )
            }

            entry<InternalWeb> { entry ->
                WebScreen(
                    modifier = Modifier.fillMaxSize(),
                    onNavigation = { sideEffect ->
                        when (sideEffect) {
                            is WebSideEffect.NavigateBack -> backStack.navigateBack()
                        }
                    },
                    initialUrl = entry.url
                )
            }

            entry<AiConversation> {
                AiConversationScreen(
                    modifier = Modifier.fillMaxSize(),
                    onNavigation = { sideEffect ->
                        when (sideEffect) {
                            is AiConversationSideEffect.NavigateBack -> backStack.navigateBack()
                            else -> {}
                        }
                    }
                )
            }

            entry<TextSpeech> {
                TextSpeechScreen(
                    modifier = Modifier.fillMaxSize(),
                    onNavigation = { sideEffect ->
                        when (sideEffect) {
                            is TextSpeechSideEffect.NavigateBack -> backStack.navigateBack()
                        }
                    }
                )
            }
        }
    )
}