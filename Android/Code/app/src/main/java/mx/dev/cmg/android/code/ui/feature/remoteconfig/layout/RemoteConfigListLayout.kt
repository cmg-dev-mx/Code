package mx.dev.cmg.android.code.ui.feature.remoteconfig.layout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun RemoteConfigListLayout(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(16.dp)
    ) {
        Text("Remote Config List")
    }
}

@Preview
@Composable
private fun Preview() {
    RemoteConfigListLayout(modifier = Modifier.fillMaxSize())
}