package mx.dev.cmg.android.code.ui.feature.aiconversation.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import mx.dev.cmg.android.code.R
import mx.dev.cmg.android.code.ui.atomicdesign.particle.Title
import mx.dev.cmg.android.code.ui.atomicdesign.subatomic.CodeCustomTheme
import mx.dev.cmg.android.code.ui.feature.aiconversation.viewmodel.AiConversationEvent

@Composable
fun AiConversationLayout(
    onEvent: (AiConversationEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(color = CodeCustomTheme.Color.background)
            .padding(CodeCustomTheme.Spacing.s)
    ) {
        Title(
            modifier = Modifier.fillMaxWidth(),
            title = stringResource(R.string.ai_conversation),
            icon = R.drawable.ic_arrow_back,
            onIconClick = { onEvent(AiConversationEvent.NavigateBack) }
        )
    }
}