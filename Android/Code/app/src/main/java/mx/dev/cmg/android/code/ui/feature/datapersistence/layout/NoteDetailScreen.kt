package mx.dev.cmg.android.code.ui.feature.datapersistence.layout

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import mx.dev.cmg.android.code.ui.feature.datapersistence.viewmodel.NoteDetailSideEffect
import mx.dev.cmg.android.code.ui.feature.datapersistence.viewmodel.NoteDetailViewModel
import mx.dev.cmg.android.code.ui.util.collectAsEffect
import org.koin.androidx.compose.koinViewModel

@Composable
fun NoteDetailScreen(
    onNavigation: (NoteDetailSideEffect) -> Unit,
    noteId: Int,
    modifier: Modifier = Modifier,
    viewModel: NoteDetailViewModel = koinViewModel()
) {
    viewModel.sideEffect.collectAsEffect { sideEffect ->
        onNavigation(sideEffect)
    }

    NoteDetailLayout(
        onEvent = viewModel::onEvent,
        modifier = modifier,
    )
}