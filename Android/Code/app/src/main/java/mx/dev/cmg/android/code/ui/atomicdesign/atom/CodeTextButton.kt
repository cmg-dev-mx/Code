package mx.dev.cmg.android.code.ui.atomicdesign.atom

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mx.dev.cmg.android.code.ui.atomicdesign.subatomic.CodeCustomTheme

@Composable
fun CodeTextButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors : ButtonColors = codeTextButtonDefaults(),
    content: @Composable RowScope.() -> Unit,
) {
    TextButton(
        modifier = modifier,
        enabled = enabled,
        onClick = onClick,
        content = content,
        colors = colors
    )
}

@Composable
fun codeTextButtonDefaults(): ButtonColors =
    ButtonDefaults.textButtonColors().copy(
        contentColor = CodeCustomTheme.Color.primary,
        disabledContentColor = CodeCustomTheme.Color.primary.copy(alpha = 0.5f)
    )

@Preview(showBackground = true)
@Composable
private fun Preview() {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

    CodeTextButton(
            onClick = {},
            content = { Text(text = "Botón de ejemplo") }
        )

        CodeTextButton(
            enabled = false,
            onClick = {},
            content = { Text(text = "Botón de ejemplo") }
        )
    }

}