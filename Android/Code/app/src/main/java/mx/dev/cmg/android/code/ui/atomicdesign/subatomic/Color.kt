package mx.dev.cmg.android.code.ui.atomicdesign.subatomic

import androidx.compose.ui.graphics.Color

// Colors
val MoltenBark = Color(0xFF502D24)
val DuneMist = Color(0xFFC8AC98)
val OchreBlaze = Color(0xFFD45D26)
val VelvetRouge = Color(0xFF6E0E0D)

class CodeColor(
    val background: Color,
    val onBackground: Color,
    val surface: Color,
    val onSurface: Color,
    val primary: Color,
    val onPrimary: Color,
    val secondary: Color,
    val onSecondary: Color
) {
    companion object {
        val DefaultColorScheme = CodeColor(
            background = DuneMist,
            onBackground = MoltenBark,
            surface = MoltenBark,
            onSurface = DuneMist,
            primary = VelvetRouge,
            onPrimary = DuneMist,
            secondary = OchreBlaze,
            onSecondary = VelvetRouge
        )
    }
}