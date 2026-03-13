package mx.dev.cmg.android.code.ui.feature.web.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mx.dev.cmg.android.code.R
import mx.dev.cmg.android.code.ui.atomicdesign.particle.Title
import mx.dev.cmg.android.code.ui.feature.web.viewmodel.WebMenuEvent

@Composable
fun WebMenuLayout(
    onEvent: (WebMenuEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Title(
            modifier = Modifier.fillMaxWidth(),
            title = stringResource(R.string.web_menu),
            icon = R.drawable.ic_arrow_back,
            onIconClick = { onEvent(WebMenuEvent.NavigateBack) }
        )

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { onEvent(WebMenuEvent.OpenWebInLayout) }
        ) {
            Text(
                text = stringResource(R.string.open_web_in_layout)
            )
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { onEvent(WebMenuEvent.OpenCustomTab) }
        ) {
            Text(
                text = stringResource(R.string.open_web_in_custom_tab)
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    WebMenuLayout(
        modifier = Modifier.fillMaxSize(),
        onEvent = {}
    )
}