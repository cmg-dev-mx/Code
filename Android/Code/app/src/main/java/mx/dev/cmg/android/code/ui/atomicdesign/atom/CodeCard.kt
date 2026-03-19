package mx.dev.cmg.android.code.ui.atomicdesign.atom

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mx.dev.cmg.android.code.ui.atomicdesign.subatomic.CodeCustomTheme

@Composable
fun CodeCard(
    modifier: Modifier = Modifier,
    colors: CardColors = codeCardDefaults(),
    content: @Composable ColumnScope.() -> Unit,
) {
    Card(
        modifier = modifier,
        colors = colors,
        content = content
    )
}

@Composable
fun codeCardDefaults(): CardColors =
    CardDefaults.cardColors(
        containerColor = CodeCustomTheme.Color.surface,
        contentColor = CodeCustomTheme.Color.onSurface
    )

@Preview
@Composable
private fun Preview() {
    CodeCard(
        content = {
            Text(
                modifier = Modifier.padding(16.dp),
                text = "Contenido de la tarjeta"
            )
        }
    )
}