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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.collections.immutable.toPersistentList
import mx.dev.cmg.android.code.R
import mx.dev.cmg.android.code.domain.Note
import mx.dev.cmg.android.code.ui.atomicdesign.atom.CodeCard
import mx.dev.cmg.android.code.ui.atomicdesign.particle.Title
import mx.dev.cmg.android.code.ui.atomicdesign.subatomic.CodeCustomTheme
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
        modifier = modifier,
        containerColor = CodeCustomTheme.Color.background,
        contentColor = CodeCustomTheme.Color.onBackground,
        topBar = {
            Title(
                modifier = Modifier
                    .padding(CodeCustomTheme.Spacing.s)
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
                    color = CodeCustomTheme.Color.primary,
                    shape = RoundedCornerShape(CodeCustomTheme.Shape.small)
                )
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_add),
                    contentDescription = stringResource(R.string.agregar_nota),
                    tint = CodeCustomTheme.Color.onPrimary
                )
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(CodeCustomTheme.Spacing.s),
            verticalArrangement = Arrangement.spacedBy(CodeCustomTheme.Spacing.s),
            contentPadding = PaddingValues(vertical = CodeCustomTheme.Spacing.xxxs)
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
    CodeCard(
        modifier = modifier
            .clip(RoundedCornerShape(CodeCustomTheme.Shape.large))
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(CodeCustomTheme.Spacing.s)
                .height(CodeCustomTheme.Spacing.xxl),
            horizontalArrangement = Arrangement.spacedBy(CodeCustomTheme.Spacing.xs),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    style = CodeCustomTheme.Typography.title,
                    text = note.title
                )
                Text(
                    style = CodeCustomTheme.Typography.small,
                    text = note.timestamp.toFormattedDate()
                )
            }
            IconButton(
                onClick = onDelete,
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_delete),
                    contentDescription = stringResource(R.string.eliminar_nota),
                    tint = CodeCustomTheme.Color.secondary
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
            notes = sampleNotes.toPersistentList()
        ),
        onEvent = {}
    )
}
