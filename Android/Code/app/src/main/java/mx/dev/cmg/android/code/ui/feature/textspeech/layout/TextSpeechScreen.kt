package mx.dev.cmg.android.code.ui.feature.textspeech.layout

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import mx.dev.cmg.android.code.ui.feature.textspeech.viewmodel.TextSpeechSideEffect
import mx.dev.cmg.android.code.ui.feature.textspeech.viewmodel.TextSpeechViewModel
import mx.dev.cmg.android.code.ui.util.collectAsEffect
import org.koin.androidx.compose.koinViewModel

@Composable
fun TextSpeechScreen(
    onNavigation: (TextSpeechSideEffect) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: TextSpeechViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    viewModel.sideEffect.collectAsEffect { effect ->
        onNavigation(effect)
    }

    TextSpeechLayout(
        modifier = modifier,
        uiState = uiState,
        onEvent = viewModel::onEvent
    )
}