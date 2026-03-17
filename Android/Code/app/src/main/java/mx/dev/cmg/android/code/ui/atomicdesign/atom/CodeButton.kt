package mx.dev.cmg.android.code.ui.atomicdesign.atom

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mx.dev.cmg.android.code.ui.atomicdesign.subatomic.CodeCustomTheme

@Composable
fun CodeButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors : ButtonColors = codeButtonDefaults(),
    content: @Composable RowScope.() -> Unit
) {

    Button(
        modifier = modifier,
        enabled = enabled,
        onClick = onClick,
        content = content,
        shape = RoundedCornerShape(CodeCustomTheme.Shape.large),
        colors = colors
    )
}

@Composable
fun codeButtonDefaults() : ButtonColors =
    ButtonDefaults.buttonColors(
        containerColor = CodeCustomTheme.Color.primary,
        contentColor = CodeCustomTheme.Color.onPrimary,
        disabledContainerColor = CodeCustomTheme.Color.primary.copy(alpha = 0.5f),
        disabledContentColor = CodeCustomTheme.Color.onPrimary.copy(alpha = 0.5f)
    )

@Preview(showBackground = true)
@Composable
private fun Preview() {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        CodeButton(
            onClick = {},
            content = { Text(text = "Botón de ejemplo") }
        )

        CodeButton(
            enabled = false,
            onClick = {},
            content = { Text(text = "Botón de ejemplo") }
        )
    }
}