package mx.dev.cmg.android.code.ui.feature.datapersistence.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mx.dev.cmg.android.code.R
import mx.dev.cmg.android.code.domain.Note
import mx.dev.cmg.android.code.ui.atomicdesign.particle.Title
import mx.dev.cmg.android.code.ui.feature.datapersistence.viewmodel.DataPersistenceEvent
import mx.dev.cmg.android.code.ui.feature.datapersistence.viewmodel.DataPersistenceUiState
import mx.dev.cmg.android.code.ui.util.toFormattedDate

@Composable
fun DataPersistenceLayout(
    uiState: DataPersistenceUiState,
    onEvent: (DataPersistenceEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.background),
        topBar = {
            Title(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                title = stringResource(R.string.notas),
                icon = R.drawable.ic_arrow_back,
                onIconClick = { onEvent(DataPersistenceEvent.NavigateBack) }
            )
        },
        floatingActionButton = {
            IconButton(
                onClick = { onEvent(DataPersistenceEvent.CreateNote) },
                modifier = Modifier.background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = MaterialTheme.shapes.small
                )
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_add),
                    contentDescription = stringResource(R.string.agregar_nota),
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(vertical = 4.dp)
        ) {
            items(uiState.notes) { note ->
                NoteItem(
                    modifier = Modifier.fillMaxWidth(),
                    note = note,
                    onClick = { onEvent(DataPersistenceEvent.EditNote(note.id)) },
                    onDelete = { onEvent(DataPersistenceEvent.DeleteNote(note.id)) }
                )
            }
        }
    }
}

@Composable
fun NoteItem(
    modifier: Modifier = Modifier,
    note: Note,
    onClick: () -> Unit,
    onDelete: () -> Unit
) {
    Card(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClick() }
            .background(color = MaterialTheme.colorScheme.surface),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(48.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = note.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = note.timestamp.toFormattedDate(),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            IconButton(
                onClick = onDelete,
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_delete),
                    contentDescription = stringResource(R.string.eliminar_nota),
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DataPersistenceLayoutPreview() {

    val sampleNotes = listOf(
        Note(
            id = 1,
            title = "Nota 1",
            content = "Contenido de la nota 1",
            timestamp = System.currentTimeMillis()
        ),
        Note(
            id = 2,
            title = "Nota 2",
            content = "Contenido de la nota 2",
            timestamp = System.currentTimeMillis()
        ),
        Note(
            id = 3,
            title = "Nota 3",
            content = "Contenido de la nota 3",
            timestamp = System.currentTimeMillis()
        ),
        Note(
            id = 4,
            title = "Nota 4",
            content = "Contenido de la nota 4",
            timestamp = System.currentTimeMillis()
        )
    )

    DataPersistenceLayout(
        modifier = Modifier.fillMaxSize(),
        uiState = DataPersistenceUiState(
            notes = sampleNotes
        ),
        onEvent = {}
    )
}
