package mx.dev.cmg.android.code.ui.feature.datapersistence.layout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import mx.dev.cmg.android.code.R
import mx.dev.cmg.android.code.ui.atomicdesign.atom.CodeButton
import mx.dev.cmg.android.code.ui.atomicdesign.atom.CodeTextButton
import mx.dev.cmg.android.code.ui.atomicdesign.atom.CodeTextField
import mx.dev.cmg.android.code.ui.atomicdesign.atom.codeButtonDefaults
import mx.dev.cmg.android.code.ui.atomicdesign.atom.codeTextButtonDefaults
import mx.dev.cmg.android.code.ui.atomicdesign.subatomic.CodeCustomTheme
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
            .padding(CodeCustomTheme.Spacing.s),
        verticalArrangement = Arrangement.spacedBy(CodeCustomTheme.Spacing.s)
    ) {
        CodeTextField(
            value = uiState.title,
            onValueChange = { onEvent(NoteDetailEvent.UpdateTitle(it)) },
            label = {
                Text(
                    style = CodeCustomTheme.Typography.body,
                    text = stringResource(R.string.titulo)
                )
            },
            modifier = Modifier.fillMaxWidth()
        )
        CodeTextField(
            minLines = 5,
            value = uiState.content,
            onValueChange = { onEvent(NoteDetailEvent.UpdateContent(it)) },
            label = {
                Text(
                    style = CodeCustomTheme.Typography.body,
                    text = stringResource(R.string.contenido)
                )
            },
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CodeTextButton(
                colors = codeTextButtonDefaults().copy(
                    contentColor = CodeCustomTheme.Color.onSurface,
                    disabledContentColor = CodeCustomTheme.Color.onSurface.copy(alpha = 0.5f)
                ),
                onClick = { onEvent(NoteDetailEvent.NavigateBack) }
            ) {
                Text(
                    style = CodeCustomTheme.Typography.body,
                    text = stringResource(R.string.cancelar)
                )
            }

            CodeButton(
                colors = codeButtonDefaults().copy(
                    containerColor = CodeCustomTheme.Color.secondary,
                    disabledContainerColor = CodeCustomTheme.Color.secondary.copy(alpha = 0.5f),
                    contentColor = CodeCustomTheme.Color.onSecondary,
                    disabledContentColor = CodeCustomTheme.Color.onSecondary.copy(alpha = 0.5f)
                ),
                enabled = uiState.readyToSave(),
                onClick = { onEvent(NoteDetailEvent.SaveNote) }
            ) {
                Text(
                    style = CodeCustomTheme.Typography.body,
                    text = stringResource(R.string.guardar)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    NoteDetailLayout(
        modifier = Modifier.fillMaxWidth(),
        uiState = NoteDetailUiState(),
        onEvent = {}
    )
}