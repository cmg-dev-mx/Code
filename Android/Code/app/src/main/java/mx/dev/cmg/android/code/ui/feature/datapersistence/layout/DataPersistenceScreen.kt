package mx.dev.cmg.android.code.ui.feature.datapersistence.layout

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import mx.dev.cmg.android.code.ui.feature.datapersistence.viewmodel.DataPersistenceSideEffect
import mx.dev.cmg.android.code.ui.feature.datapersistence.viewmodel.DataPersistenceViewModel
import mx.dev.cmg.android.code.ui.util.collectAsEffect
import org.koin.androidx.compose.koinViewModel

@Composable
fun DataPersistenceScreen(
    onNavigation: (DataPersistenceSideEffect) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DataPersistenceViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    viewModel.sideEffect.collectAsEffect { sideEffect ->
        onNavigation(sideEffect)
    }

    DataPersistenceLayout(
        modifier = modifier,
        uiState = uiState,
        onEvent = viewModel::onEvent
    )
}
