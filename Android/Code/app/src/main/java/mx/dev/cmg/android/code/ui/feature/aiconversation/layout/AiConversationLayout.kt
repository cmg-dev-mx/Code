package mx.dev.cmg.android.code.ui.feature.aiconversation.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import mx.dev.cmg.android.code.R
import mx.dev.cmg.android.code.ui.atomicdesign.particle.Title
import mx.dev.cmg.android.code.ui.atomicdesign.subatomic.CodeCustomTheme
import mx.dev.cmg.android.code.ui.feature.aiconversation.viewmodel.AiConversationEvent
import mx.dev.cmg.android.code.ui.feature.aiconversation.viewmodel.AiConversationUiState

@Composable
fun AiConversationLayout(
    uiState: AiConversationUiState,
    onEvent: (AiConversationEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(color = CodeCustomTheme.Color.background)
            .padding(CodeCustomTheme.Spacing.s),
        verticalArrangement = Arrangement.spacedBy(CodeCustomTheme.Spacing.s),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Title(
            modifier = Modifier.fillMaxWidth(),
            title = stringResource(R.string.ai_conversation),
            icon = R.drawable.ic_arrow_back,
            onIconClick = { onEvent(AiConversationEvent.NavigateBack) }
        )

        IconButton(
            modifier = Modifier.wrapContentWidth(),
            colors = IconButtonDefaults.iconButtonColors(
                contentColor = CodeCustomTheme.Color.onSurface,
                containerColor = CodeCustomTheme.Color.surface
            ),
            onClick = {
                onEvent(
                    if (uiState.isListening) AiConversationEvent.StopListening
                    else AiConversationEvent.StartListening
                )
            }
        ) {
            Icon(
                modifier = Modifier.size(CodeCustomTheme.Icon.s),
                painter = painterResource(if (uiState.isListening) R.drawable.ic_mic_on else R.drawable.ic_mic_off),
                contentDescription = null
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    AiConversationLayout(
        modifier = Modifier.fillMaxSize(),
        uiState = AiConversationUiState(),
        onEvent = { }
    )
}