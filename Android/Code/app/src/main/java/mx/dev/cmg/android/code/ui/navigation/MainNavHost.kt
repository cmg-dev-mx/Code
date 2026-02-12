package mx.dev.cmg.android.code.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import kotlinx.serialization.Serializable
import mx.dev.cmg.android.code.ui.feature.main.layout.MainLayout
import mx.dev.cmg.android.code.ui.feature.main.viewmodel.MainSideEffect
import mx.dev.cmg.android.code.ui.feature.main.viewmodel.MainViewModel
import mx.dev.cmg.android.code.ui.feature.mvidemo.layout.NameListLayout
import mx.dev.cmg.android.code.ui.feature.mvidemo.viewmodel.NameListSideEffect
import mx.dev.cmg.android.code.ui.feature.mvidemo.viewmodel.NameListViewModel
import org.koin.androidx.compose.koinViewModel

@Serializable
data object Main : NavKey

@Serializable
data object NameList : NavKey

@Composable
fun MainNavHost(modifier: Modifier = Modifier) {
    val backStack = rememberNavBackStack(Main)

    NavDisplay(
        modifier = modifier,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        backStack = backStack,
        entryProvider = entryProvider {
            entry<Main> {
                val vm: MainViewModel = koinViewModel()
                val uiState by vm.uiState.collectAsStateWithLifecycle()

                LaunchedEffect(Unit) {
                    vm.sideEffect.collect { sideEffect ->
                        when (sideEffect) {
                            is MainSideEffect.NavigateToNameList -> {
                                backStack.add(NameList)
                            }
                        }
                    }
                }

                MainLayout(
                    modifier = Modifier.fillMaxSize(),
                    uiState = uiState,
                    onEvent = vm::onEvent
                )
            }

            entry<NameList> {
                val vm: NameListViewModel = koinViewModel()
                val uiState by vm.uiState.collectAsStateWithLifecycle()

                LaunchedEffect(Unit) {
                    vm.sideEffect.collect { sideEffect ->
                        when (sideEffect) {
                            is NameListSideEffect.NavigateBack -> {
                                backStack.removeLastOrNull()
                            }

                        }
                    }
                }

                NameListLayout(
                    modifier = Modifier.fillMaxSize(),
                    uiState = uiState,
                    onEvent = vm::onEvent
                )
            }
        }
    )
}