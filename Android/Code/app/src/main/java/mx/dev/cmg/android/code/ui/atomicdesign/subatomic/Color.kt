package mx.dev.cmg.android.code.ui.atomicdesign.subatomic

import androidx.compose.ui.graphics.Color
import mx.dev.cmg.android.code.domain.RemoteColors

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

/**
 * Maps [RemoteColors] (from Firebase Remote Config) to [CodeColor].
 *
 * Remote config key mapping:
 * - primary   → onBackground, surface
 * - secondary → background, onSurface, onPrimary
 * - tertiary  → secondary, onSecondary (as color)
 * - quaternary→ primary, onSecondary
 */
fun RemoteColors.toCodeColor(): CodeColor {
    val primaryColor = Color(primary)
    val secondaryColor = Color(secondary)
    val tertiaryColor = Color(tertiary)
    val quaternaryColor = Color(quaternary)

    return CodeColor(
        background = secondaryColor,
        onBackground = primaryColor,
        surface = primaryColor,
        onSurface = secondaryColor,
        primary = quaternaryColor,
        onPrimary = secondaryColor,
        secondary = tertiaryColor,
        onSecondary = quaternaryColor
    )
}
