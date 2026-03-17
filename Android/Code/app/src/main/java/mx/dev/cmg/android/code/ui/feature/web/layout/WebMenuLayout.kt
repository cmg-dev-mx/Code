package mx.dev.cmg.android.code.ui.feature.web.layout

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
import mx.dev.cmg.android.code.ui.atomicdesign.particle.Title
import mx.dev.cmg.android.code.ui.atomicdesign.subatomic.CodeCustomTheme
import mx.dev.cmg.android.code.ui.feature.web.viewmodel.WebMenuEvent

@Composable
fun WebMenuLayout(
    onEvent: (WebMenuEvent) -> Unit,
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
            title = stringResource(R.string.web_menu),
            icon = R.drawable.ic_arrow_back,
            onIconClick = { onEvent(WebMenuEvent.NavigateBack) }
        )

        CodeButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = { onEvent(WebMenuEvent.OpenWebInLayout) }
        ) {
            Text(
                style = CodeCustomTheme.Typography.body,
                text = stringResource(R.string.open_web_in_layout)
            )
        }

        CodeButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = { onEvent(WebMenuEvent.OpenCustomTab) }
        ) {
            Text(
                style = CodeCustomTheme.Typography.body,
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