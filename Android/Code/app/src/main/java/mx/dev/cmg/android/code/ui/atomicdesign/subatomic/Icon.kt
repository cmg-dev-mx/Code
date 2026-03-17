package mx.dev.cmg.android.code.ui.atomicdesign.subatomic

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

// Size
val iconSizeS = 24.dp
val iconSizeMedium = 32.dp
val iconSizeLarge = 48.dp

class CodeIcon(
    val s: Dp,
    val m: Dp,
    val l: Dp
) {
    companion object {
        val DefaultIcon = CodeIcon(
            s = iconSizeS,
            m = iconSizeMedium,
            l = iconSizeLarge
        )
    }
}