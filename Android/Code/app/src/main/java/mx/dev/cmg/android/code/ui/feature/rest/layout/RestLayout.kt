package mx.dev.cmg.android.code.ui.feature.rest.layout

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mx.dev.cmg.android.code.R
import mx.dev.cmg.android.code.ui.atomicdesign.particle.Title
import mx.dev.cmg.android.code.ui.feature.rest.viewmodel.RestEvent
import mx.dev.cmg.android.code.ui.feature.rest.viewmodel.RestUiState

@Composable
fun RestLayout(
    uiState: RestUiState,
    onEvent: (RestEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Title(
            modifier = Modifier.fillMaxWidth(),
            title = stringResource(R.string.rest_api),
            icon = R.drawable.ic_arrow_back,
            onIconClick = { onEvent(RestEvent.NavigateBack) }
        )

        Button(
            enabled = !uiState.isLoading,
            modifier = Modifier.fillMaxWidth(),
            onClick = { onEvent(RestEvent.GetQuote) }
        ) {

            AnimatedVisibility(uiState.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp)
                )
            }

            AnimatedVisibility(!uiState.isLoading) {
                Text(
                    text = stringResource(R.string.obtener_respuesta)
                )
            }
        }

        AnimatedVisibility(uiState.apiResponse != null) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                text = uiState.apiResponse ?: ""
            )
        }

        AnimatedVisibility(uiState.apiResponse == null) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f),
                text = stringResource(R.string.presiona_para_obtener_respuesta)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    val uiState = RestUiState(
        isLoading = false,
        apiResponse = null
    )

    RestLayout(
        modifier = Modifier.fillMaxSize(),
        uiState = uiState,
        onEvent = {}
    )
}