package mx.dev.cmg.android.code.ui.atomicdesign.subatomic

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

val xxxsSpacing = 4.dp
val xxsSpacing = 8.dp
val xsSpacing = 12.dp
val sSpacing = 16.dp
val mSpacing = 24.dp
val lSpacing = 32.dp
val xlSpacing = 40.dp
val xxlSpacing = 48.dp
val xxxlSpacing = 64.dp

class CodeSpacing(
    val xxxs: Dp,
    val xxs: Dp,
    val xs: Dp,
    val s: Dp,
    val m: Dp,
    val l: Dp,
    val xl: Dp,
    val xxl: Dp,
    val xxxl: Dp
) {
    companion object {
        val DefaultSpacing = CodeSpacing(
            xxxs = xxxsSpacing,
            xxs = xxsSpacing,
            xs = xsSpacing,
            s = sSpacing,
            m = mSpacing,
            l = lSpacing,
            xl = xlSpacing,
            xxl = xxlSpacing,
            xxxl = xxxlSpacing
        )
    }
}