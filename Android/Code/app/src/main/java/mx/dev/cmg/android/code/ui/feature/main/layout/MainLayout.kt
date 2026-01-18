package mx.dev.cmg.android.code.ui.feature.main.layout

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mx.dev.cmg.android.code.ui.feature.main.viewmodel.MainEvent
import mx.dev.cmg.android.code.ui.feature.main.viewmodel.MainUiState

@Composable
fun MainLayout(
    uiState: MainUiState,
    onEvent: (MainEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    LaunchedEffect(Unit) {
        onEvent(MainEvent.OnLoad)
    }

    Box(modifier = modifier) {

        if (uiState.isLoading) {
            LinearProgressIndicator(
                modifier = Modifier.fillMaxWidth().height(4.dp)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize(1f)
                .padding(16.dp)
        ) {
            Text(text = "Main layout")
            Text(text = "Name: ${uiState.name}")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MainLayoutPreview() {
    MainLayout(
        uiState = MainUiState(
            isLoading = true,
            name = "Code"
        ),
        onEvent = {},
        modifier = Modifier.fillMaxSize()
    )
}