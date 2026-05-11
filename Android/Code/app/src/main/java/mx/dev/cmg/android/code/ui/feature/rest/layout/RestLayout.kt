package mx.dev.cmg.android.code.ui.feature.rest.layout

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import mx.dev.cmg.android.code.R
import mx.dev.cmg.android.code.ui.atomicdesign.atom.CodeButton
import mx.dev.cmg.android.code.ui.atomicdesign.particle.Title
import mx.dev.cmg.android.code.ui.atomicdesign.subatomic.CodeCustomTheme
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
            .background(color = CodeCustomTheme.Color.background)
            .padding(CodeCustomTheme.Spacing.s),
        verticalArrangement = Arrangement.spacedBy(CodeCustomTheme.Spacing.s)
    ) {
        Title(
            modifier = Modifier.fillMaxWidth(),
            title = stringResource(R.string.rest_api),
            icon = R.drawable.ic_arrow_back,
            onIconClick = { onEvent(RestEvent.NavigateBack) }
        )

        CodeButton(
            enabled = !uiState.isLoading,
            modifier = Modifier.fillMaxWidth(),
            onClick = { onEvent(RestEvent.GetQuote) }
        ) {

            AnimatedVisibility(uiState.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(CodeCustomTheme.Icon.s)
                )
            }

            AnimatedVisibility(!uiState.isLoading) {
                Text(
                    style = CodeCustomTheme.Typography.body,
                    text = stringResource(R.string.obtener_respuesta)
                )
            }
        }

        AnimatedVisibility(uiState.apiResponse != null) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                style = CodeCustomTheme.Typography.title,
                color = CodeCustomTheme.Color.onBackground,
                textAlign = TextAlign.Center,
                text = uiState.apiResponse ?: ""
            )
        }

        AnimatedVisibility(uiState.apiResponse == null) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                style = CodeCustomTheme.Typography.body,
                color = CodeCustomTheme.Color.primary.copy(alpha = 0.5f),
                textAlign = TextAlign.Center,
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