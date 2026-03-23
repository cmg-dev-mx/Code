package mx.dev.cmg.android.code.ui.atomicdesign.subatomic

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf

val LocalCodeColor = compositionLocalOf { CodeColor.DefaultColorScheme }

class CodeCustomTheme {
    companion object {
        val Color: CodeColor
            @Composable
            get() = LocalCodeColor.current
        val Typography: CodeTypography = CodeTypography.DefaultTypography
        val Shape: CodeShapes = CodeShapes.DefaultShapes
        val Spacing: CodeSpacing = CodeSpacing.DefaultSpacing
        val Icon: CodeIcon = CodeIcon.DefaultIcon
    }
}

@Composable
fun CodeTheme(
    colors: CodeColor = CodeColor.DefaultColorScheme,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalCodeColor provides colors,
        content = content
    )
}
