package mx.dev.cmg.android.code.ui.feature.sharedpreferences.layout

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import mx.dev.cmg.android.code.R
import mx.dev.cmg.android.code.ui.feature.sharedpreferences.viewmodel.SharedPreferencesSideEffect
import mx.dev.cmg.android.code.ui.feature.sharedpreferences.viewmodel.SharedPreferencesViewModel
import mx.dev.cmg.android.code.ui.util.collectAsEffect
import org.koin.androidx.compose.koinViewModel

@Composable
fun SharedPreferencesScreen(
    onNavigation: (SharedPreferencesSideEffect) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SharedPreferencesViewModel = koinViewModel()
) {
    val context = LocalContext.current
    val valueSavedMessage = stringResource(R.string.value_saved)

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    viewModel.sideEffect.collectAsEffect { sideEffect ->
        when (sideEffect) {
            is SharedPreferencesSideEffect.SaveSuccess -> {
                Toast.makeText(context, valueSavedMessage, Toast.LENGTH_SHORT)
                    .show()
            }

            else -> onNavigation(sideEffect)
        }
    }

    SharedPreferencesLayout(
        modifier = modifier,
        uiState = uiState,
        onEvent = viewModel::onEvent
    )
}
