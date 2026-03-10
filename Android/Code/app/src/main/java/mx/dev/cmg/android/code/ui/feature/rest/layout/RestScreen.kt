package mx.dev.cmg.android.code.ui.feature.rest.layout

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import mx.dev.cmg.android.code.ui.feature.rest.viewmodel.RestSideEffect
import mx.dev.cmg.android.code.ui.feature.rest.viewmodel.RestViewModel
import mx.dev.cmg.android.code.ui.util.collectAsEffect
import org.koin.androidx.compose.koinViewModel

@Composable
fun RestScreen(
    onNavigation: (RestSideEffect) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: RestViewModel = koinViewModel()
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    viewModel.sideEffect.collectAsEffect { sideEffect ->
        when (sideEffect) {
            is RestSideEffect.ShowToast -> {
                Toast.makeText(context, sideEffect.message, Toast.LENGTH_SHORT).show()
            }
            is RestSideEffect.NavigateBack -> onNavigation(sideEffect)
        }
    }

    RestLayout(
        modifier = modifier,
        uiState = uiState,
        onEvent = viewModel::onEvent
    )
}