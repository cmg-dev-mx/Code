package mx.dev.cmg.android.code.ui.feature.aiconversation.layout

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import mx.dev.cmg.android.code.ui.feature.aiconversation.viewmodel.AiConversationSideEffect
import mx.dev.cmg.android.code.ui.feature.aiconversation.viewmodel.AiConversationViewModel
import mx.dev.cmg.android.code.ui.util.collectAsEffect
import org.koin.androidx.compose.koinViewModel

@Composable
fun AiConversationScreen(
    onNavigation: (AiConversationSideEffect) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AiConversationViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    viewModel.sideEffect.collectAsEffect { sideEffect ->
        onNavigation(sideEffect)
    }

    AiConversationLayout(
        modifier = modifier,
        uiState = uiState,
        onEvent = viewModel::onEvent
    )
}