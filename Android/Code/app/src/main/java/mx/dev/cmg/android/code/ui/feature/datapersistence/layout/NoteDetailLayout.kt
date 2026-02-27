package mx.dev.cmg.android.code.ui.feature.datapersistence.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mx.dev.cmg.android.code.R
import mx.dev.cmg.android.code.ui.feature.datapersistence.viewmodel.NoteDetailEvent
import mx.dev.cmg.android.code.ui.feature.datapersistence.viewmodel.NoteDetailUiState

@Composable
fun NoteDetailLayout(
    uiState: NoteDetailUiState,
    onEvent: (NoteDetailEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.surface)
            .border(if (uiState.error) 1.dp else 0.dp, MaterialTheme.colorScheme.error)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TextField(
            value = uiState.title,
            onValueChange = { onEvent(NoteDetailEvent.UpdateTitle(it)) },
            label = {
                Text(
                    style = MaterialTheme.typography.labelMedium,
                    text = stringResource(R.string.titulo)
                )
            },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            minLines = 5,
            value = uiState.content,
            onValueChange = { onEvent(NoteDetailEvent.UpdateContent(it)) },
            label = {
                Text(
                    style = MaterialTheme.typography.labelMedium,
                    text = stringResource(R.string.contenido)
                )
            },
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextButton(
                onClick = { onEvent(NoteDetailEvent.NavigateBack) }
            ) {
                Text(stringResource(R.string.cancelar))
            }

            Button(
                enabled = uiState.readyToSave(),
                onClick = { onEvent(NoteDetailEvent.SaveNote) }
            ) {
                Text(stringResource(R.string.guardar))
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    NoteDetailLayout(
        modifier = Modifier.fillMaxSize(),
        uiState = NoteDetailUiState(),
        onEvent = {}
    )
}