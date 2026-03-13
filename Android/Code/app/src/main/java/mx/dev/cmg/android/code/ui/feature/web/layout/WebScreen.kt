package mx.dev.cmg.android.code.ui.feature.web.layout

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import mx.dev.cmg.android.code.ui.feature.web.viewmodel.WebEvent
import mx.dev.cmg.android.code.ui.feature.web.viewmodel.WebSideEffect
import mx.dev.cmg.android.code.ui.feature.web.viewmodel.WebViewModel
import mx.dev.cmg.android.code.ui.util.collectAsEffect
import org.koin.androidx.compose.koinViewModel

@Composable
fun WebScreen(
    initialUrl: String,
    onNavigation: (WebSideEffect) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: WebViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.onEvent(WebEvent.SetInitialUrl(initialUrl))
    }

    viewModel.sideEffect.collectAsEffect { sideEffect ->
        onNavigation(sideEffect)
    }

    WebLayout(
        modifier = modifier,
        uiState = uiState,
        onEvent = viewModel::onEvent
    )
}