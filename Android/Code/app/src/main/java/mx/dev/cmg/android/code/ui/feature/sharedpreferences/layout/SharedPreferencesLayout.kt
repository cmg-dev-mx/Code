package mx.dev.cmg.android.code.ui.feature.sharedpreferences.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import mx.dev.cmg.android.code.R
import mx.dev.cmg.android.code.ui.atomicdesign.atom.CodeButton
import mx.dev.cmg.android.code.ui.atomicdesign.atom.CodeSwitch
import mx.dev.cmg.android.code.ui.atomicdesign.atom.CodeTextField
import mx.dev.cmg.android.code.ui.atomicdesign.particle.Title
import mx.dev.cmg.android.code.ui.atomicdesign.subatomic.CodeCustomTheme
import mx.dev.cmg.android.code.ui.feature.sharedpreferences.viewmodel.SharedPreferencesEvent
import mx.dev.cmg.android.code.ui.feature.sharedpreferences.viewmodel.SharedPreferencesUiState

@Composable
fun SharedPreferencesLayout(
    uiState: SharedPreferencesUiState,
    onEvent: (SharedPreferencesEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(color = CodeCustomTheme.Color.background)
            .padding(CodeCustomTheme.Spacing.s),
        verticalArrangement = Arrangement.spacedBy(CodeCustomTheme.Spacing.xs)
    ) {
        Title(
            modifier = Modifier.fillMaxWidth(),
            title = stringResource(R.string.shared_preferences),
            icon = R.drawable.ic_arrow_back,
            onIconClick = { onEvent(SharedPreferencesEvent.NavigateBack) }
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(CodeCustomTheme.Shape.medium))
                .background(CodeCustomTheme.Color.surface)
                .padding(CodeCustomTheme.Spacing.s),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                color = CodeCustomTheme.Color.onSurface,
                style = CodeCustomTheme.Typography.title,
                text = stringResource(R.string.enable_edition),
                modifier = Modifier.weight(1f)
            )

            CodeSwitch(
                checked = uiState.editEnabled,
                onCheckedChange = { onEvent(SharedPreferencesEvent.ToggleEdit(it)) }
            )
        }

        CodeTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = CodeCustomTheme.Spacing.s),
            value = uiState.modifiedValue,
            onValueChange = { onEvent(SharedPreferencesEvent.OnValueChange(it)) },
            label = {
                Text(
                    style = CodeCustomTheme.Typography.body,
                    text = stringResource(R.string.text_to_edit)
                )
            },
            enabled = uiState.editEnabled
        )

        CodeButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = CodeCustomTheme.Spacing.s),
            onClick = { onEvent(SharedPreferencesEvent.SaveValue) }
        ) {
            Text(
                style = CodeCustomTheme.Typography.body,
                text = stringResource(R.string.guardar)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    SharedPreferencesLayout(
        modifier = Modifier.fillMaxSize(),
        uiState = SharedPreferencesUiState(),
        onEvent = {}
    )
}
