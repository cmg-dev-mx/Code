package mx.dev.cmg.android.code.ui.feature.sharedpreferences.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mx.dev.cmg.android.code.R
import mx.dev.cmg.android.code.ui.atomicdesign.particle.Title
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
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
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
                .clip(RoundedCornerShape(8.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                style = MaterialTheme.typography.titleMedium,
                text = stringResource(R.string.enable_edition),
                modifier = Modifier.weight(1f)
            )

            Switch(
                checked = uiState.editEnabled,
                onCheckedChange = { onEvent(SharedPreferencesEvent.ToggleEdit(it)) }
            )
        }

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            value = uiState.modifiedValue,
            onValueChange = { onEvent(SharedPreferencesEvent.OnValueChange(it)) },
            label = { Text(stringResource(R.string.text_to_edit)) },
            enabled = uiState.editEnabled
        )

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            onClick = { onEvent(SharedPreferencesEvent.SaveValue) }
        ) {
            Text(stringResource(R.string.guardar))
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
