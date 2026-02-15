package mx.dev.cmg.android.code.ui.feature.mvidemo.layout

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import mx.dev.cmg.android.code.ui.feature.mvidemo.viewmodel.NameListSideEffect
import mx.dev.cmg.android.code.ui.feature.mvidemo.viewmodel.NameListViewModel
import mx.dev.cmg.android.code.ui.util.collectAsEffect
import org.koin.androidx.compose.koinViewModel

@Composable
fun NameListScreen(
    onNavigation:(NameListSideEffect) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: NameListViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    viewModel.sideEffect.collectAsEffect { sideEffect ->
        onNavigation(sideEffect)
    }

    NameListLayout(
        modifier = modifier,
        uiState = uiState,
        onEvent = viewModel::onEvent
    )
}