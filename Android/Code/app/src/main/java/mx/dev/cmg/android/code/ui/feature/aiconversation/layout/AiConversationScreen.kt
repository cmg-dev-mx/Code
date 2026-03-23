package mx.dev.cmg.android.code.ui.feature.aiconversation.layout

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
    viewModel.sideEffect.collectAsEffect { sideEffect ->
        onNavigation(sideEffect)
    }

    AiConversationLayout(
        modifier = modifier,
        onEvent = viewModel::onEvent
    )
}