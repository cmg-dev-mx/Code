package mx.dev.cmg.android.code.ui.feature.remoteconfig.layout

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mx.dev.cmg.android.code.R
import mx.dev.cmg.android.code.core.model.RemoteConfigItem
import mx.dev.cmg.android.code.ui.atomicdesign.particle.Title
import mx.dev.cmg.android.code.ui.feature.remoteconfig.viewmodel.RemoteConfigListEvent
import mx.dev.cmg.android.code.ui.feature.remoteconfig.viewmodel.RemoteConfigListUiState

@Composable
fun RemoteConfigListLayout(
    uiState: RemoteConfigListUiState,
    onEvent: (RemoteConfigListEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Title(
            modifier = Modifier.fillMaxWidth(),
            title = stringResource(R.string.remote_configuration_items),
            icon = R.drawable.ic_arrow_back,
            iconDescription = stringResource(R.string.return_back),
            onIconClick = { onEvent(RemoteConfigListEvent.OnBackClick) }
        )

        AnimatedVisibility(uiState.isLoading) {
            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp)
            )
        }

        AnimatedVisibility(uiState.remoteConfigItems.isEmpty()) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                text = stringResource(R.string.no_remote_config_items)
            )
        }

        AnimatedVisibility(uiState.remoteConfigItems.isNotEmpty()){
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(uiState.remoteConfigItems.size) { index ->
                    val item = uiState.remoteConfigItems[index]
                    RemoteConfigListItem(
                        modifier = Modifier.fillMaxWidth(),
                        title = item.name,
                        enabled = item.enabled,
                        onClick = { onEvent(RemoteConfigListEvent.OnToggleRemoteConfig(item.id)) }
                    )
                }
            }
        }
    }
}

@Composable
private fun RemoteConfigListItem(
    title: String,
    enabled: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f),
                shape = RoundedCornerShape(16.dp)
            )
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface,
            text = title
        )

        Switch(
            checked = enabled,
            onCheckedChange = { onClick() }
        )
    }
}

@Preview
@Composable
private fun Preview() {
    RemoteConfigListLayout(
        modifier = Modifier.fillMaxSize(),
        uiState = RemoteConfigListUiState(
            isLoading = true,
            remoteConfigItems = listOf(
                RemoteConfigItem(id = "1", name = "Feature A", enabled = true),
                RemoteConfigItem(id = "2", name = "Feature B", enabled = false),
                RemoteConfigItem(id = "3", name = "Feature C", enabled = true),
            )
        ),
        onEvent = {}
    )
}

@Preview
@Composable
private fun PreviewEmpty() {
    RemoteConfigListLayout(
        modifier = Modifier.fillMaxSize(),
        uiState = RemoteConfigListUiState(
            isLoading = false,
            remoteConfigItems = emptyList()
        ),
        onEvent = {}
    )
}