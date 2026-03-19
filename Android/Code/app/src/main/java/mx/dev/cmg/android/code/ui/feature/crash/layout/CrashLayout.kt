package mx.dev.cmg.android.code.ui.feature.crash.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mx.dev.cmg.android.code.R
import mx.dev.cmg.android.code.ui.atomicdesign.atom.CodeButton
import mx.dev.cmg.android.code.ui.atomicdesign.particle.Title
import mx.dev.cmg.android.code.ui.atomicdesign.subatomic.CodeCustomTheme
import mx.dev.cmg.android.code.ui.feature.crash.viewmodel.CrashEvent

@Composable
fun CrashLayout(
    onEvent: (CrashEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(CodeCustomTheme.Color.background)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(CodeCustomTheme.Spacing.s),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Title(
            modifier = Modifier.fillMaxWidth(),
            title = stringResource(R.string.ejemplo_crash),
            icon = R.drawable.ic_arrow_back,
            onIconClick = { onEvent(CrashEvent.NavigateBack) }
        )

        CodeButton(
            onClick = { onEvent(CrashEvent.OnCrashButtonClicked) }
        ) {
            Text(
                style = CodeCustomTheme.Typography.body,
                text = stringResource(R.string.provocar_crash)
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    CrashLayout(
        modifier = Modifier.fillMaxSize(),
        onEvent = {}
    )
}