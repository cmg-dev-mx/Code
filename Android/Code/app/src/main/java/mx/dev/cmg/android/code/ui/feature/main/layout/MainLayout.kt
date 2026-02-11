package mx.dev.cmg.android.code.ui.feature.main.layout

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mx.dev.cmg.android.code.R
import mx.dev.cmg.android.code.ui.atomicdesign.particle.Title
import mx.dev.cmg.android.code.ui.feature.main.viewmodel.MainEvent
import mx.dev.cmg.android.code.ui.feature.main.viewmodel.MainUiState

@Composable
fun MainLayout(
    uiState: MainUiState,
    onEvent: (MainEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Title(
            modifier = Modifier.fillMaxWidth(),
            title = stringResource(R.string.app_name),
            icon = R.drawable.ic_launcher_foreground
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                MainItem(
                    modifier = Modifier.fillMaxWidth(),
                    title = stringResource(R.string.remote_configuration_items),
                    icon = R.drawable.ic_remote_config,
                    onClick = { onEvent(MainEvent.NavigateToRemoteConfigList) }
                )
            }
        }
    }
}

@Composable
private fun MainItem(
    title: String,
    @DrawableRes icon: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .clip(shape = RoundedCornerShape(16.dp))
            .background(color = MaterialTheme.colorScheme.surfaceVariant)
            .clickable { onClick() }
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            text = title,
        )

        Icon(
            modifier = Modifier.size(20.dp),
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            painter = painterResource(icon),
            contentDescription = null
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    MainLayout(
        uiState = MainUiState(
            isLoading = true,
            name = "Code"
        ),
        onEvent = {},
        modifier = Modifier.fillMaxSize()
    )
}