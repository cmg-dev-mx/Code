package mx.dev.cmg.android.code.ui.atomicdesign.atom

import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import mx.dev.cmg.android.code.ui.atomicdesign.subatomic.CodeCustomTheme

@Composable
fun CodeSwitch(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    modifier: Modifier = Modifier,
    thumbContent: (@Composable () -> Unit)? = null,
    enabled: Boolean = true,
    colors: SwitchColors = codeSwitchDefaults(),
) {
    Switch(
        modifier = modifier,
        checked = checked,
        onCheckedChange = onCheckedChange,
        thumbContent = thumbContent,
        enabled = enabled,
        colors = colors
    )
}

@Composable
fun codeSwitchDefaults(): SwitchColors =
    SwitchDefaults.colors(
        checkedThumbColor = CodeCustomTheme.Color.primary,
        checkedTrackColor = CodeCustomTheme.Color.primary.copy(alpha = 0.5f),
        uncheckedThumbColor = CodeCustomTheme.Color.secondary,
        uncheckedTrackColor = CodeCustomTheme.Color.secondary.copy(alpha = 0.5f),
        disabledCheckedThumbColor = CodeCustomTheme.Color.primary.copy(alpha = 0.5f),
        disabledCheckedTrackColor = CodeCustomTheme.Color.primary.copy(alpha = 0.25f),
        disabledUncheckedThumbColor = CodeCustomTheme.Color.onSecondary.copy(alpha = 0.5f),
        disabledUncheckedTrackColor = CodeCustomTheme.Color.onSecondary.copy(alpha = 0.25f),
        uncheckedBorderColor = CodeCustomTheme.Color.secondary,
        checkedBorderColor = CodeCustomTheme.Color.primary,
        disabledCheckedBorderColor = CodeCustomTheme.Color.primary.copy(alpha = 0.5f),
        disabledUncheckedBorderColor = CodeCustomTheme.Color.onSecondary.copy(alpha = 0.5f)
    )

@Preview
@Composable
private fun Preview() {

    var checked by remember { mutableStateOf(false) }

    CodeSwitch(
        checked = checked,
        onCheckedChange = { checked = it }
    )
}