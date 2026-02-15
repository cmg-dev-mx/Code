package mx.dev.cmg.android.code.ui.feature.crash.layout

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import mx.dev.cmg.android.code.ui.feature.crash.viewmodel.CrashSideEffect
import mx.dev.cmg.android.code.ui.feature.crash.viewmodel.CrashViewModel
import mx.dev.cmg.android.code.ui.util.collectAsEffect
import org.koin.androidx.compose.koinViewModel

@Composable
fun CrashScreen(
    onNavigation:(CrashSideEffect) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CrashViewModel = koinViewModel()
) {
    viewModel.sideEffect.collectAsEffect { sideEffect ->
        onNavigation(sideEffect)
    }

    CrashLayout(
        modifier = modifier,
        onEvent = viewModel::onEvent
    )
}