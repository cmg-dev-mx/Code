package mx.dev.cmg.android.code.ui.atomicdesign.subatomic

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

val smallShape = 4.dp
val mediumShape = 8.dp
val largeShape = 16.dp

class CodeShapes(
    val small: Dp,
    val medium: Dp,
    val large: Dp
) {
    companion object {
        val DefaultShapes = CodeShapes(
            small = smallShape,
            medium = mediumShape,
            large = largeShape
        )
    }
}