package mx.dev.cmg.android.code.ui.feature.web.layout

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import mx.dev.cmg.android.code.ui.feature.web.viewmodel.WebMenuSideEffect
import mx.dev.cmg.android.code.ui.feature.web.viewmodel.WebMenuViewModel
import mx.dev.cmg.android.code.ui.util.collectAsEffect
import org.koin.androidx.compose.koinViewModel

@Composable
fun WebMenuScreen(
    onNavigation: (WebMenuSideEffect) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: WebMenuViewModel = koinViewModel()
) {
    viewModel.sideEffect.collectAsEffect { sideEffect ->
        onNavigation(sideEffect)
    }

    WebMenuLayout(
        modifier = modifier,
        onEvent = viewModel::onEvent
    )
}