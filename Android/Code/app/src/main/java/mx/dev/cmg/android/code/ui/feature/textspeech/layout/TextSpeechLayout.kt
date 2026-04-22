package mx.dev.cmg.android.code.ui.feature.textspeech.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import mx.dev.cmg.android.code.R
import mx.dev.cmg.android.code.ui.atomicdesign.atom.CodeButton
import mx.dev.cmg.android.code.ui.atomicdesign.atom.CodeTextField
import mx.dev.cmg.android.code.ui.atomicdesign.particle.Title
import mx.dev.cmg.android.code.ui.atomicdesign.subatomic.CodeCustomTheme
import mx.dev.cmg.android.code.ui.feature.textspeech.viewmodel.TextSpeechEvent
import mx.dev.cmg.android.code.ui.feature.textspeech.viewmodel.TextSpeechUiState

@Composable
fun TextSpeechLayout(
    uiState: TextSpeechUiState,
    onEvent: (TextSpeechEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(CodeCustomTheme.Color.background)
            .padding(CodeCustomTheme.Spacing.s),
        verticalArrangement = Arrangement.spacedBy(CodeCustomTheme.Spacing.s)
    ) {
        Title(
            modifier = Modifier.fillMaxWidth(),
            title = stringResource(R.string.text_speech),
            icon = R.drawable.ic_arrow_back,
            onIconClick = { onEvent(TextSpeechEvent.NavigateBack) }
        )

        CodeTextField(
            modifier = Modifier.fillMaxWidth(),
            value = uiState.text,
            onValueChange = { onEvent(TextSpeechEvent.OnTextChange(it)) },
            label = {
                Text(
                    text = stringResource(R.string.enter_text_to_speak)
                )
            }
        )

        CodeButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = { onEvent(TextSpeechEvent.OnTextToSpeechClick) },
            enabled = uiState.text.isNotBlank()
        ) {
            Text(stringResource(R.string.speak))
        }
    }
}

@Preview
@Composable
private fun Preview() {
    TextSpeechLayout(
        modifier = Modifier.fillMaxSize(),
        uiState = TextSpeechUiState(),
        onEvent = {}
    )
}