package mx.dev.cmg.android.code.ui.atomicdesign.subatomic

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import mx.dev.cmg.android.code.R

// Size
val xxxs = 10.sp
val xxs = 12.sp
val xs = 14.sp
val s = 16.sp
val m = 20.sp
val l = 24.sp
val xl = 32.sp
val xxl = 40.sp
val xxxl = 48.sp

// Weight
val light = FontWeight(300)
val regular = FontWeight(400)
val medium = FontWeight(500)
val semiBold = FontWeight(600)
val bold = FontWeight(700)

// Face
val JosefinSans = Font(resId = R.font.josefin_sans)

class CodeTypography(
    val header: TextStyle,
    val title: TextStyle,
    val body: TextStyle,
    val small: TextStyle
) {
    companion object {
        val DefaultTypography = CodeTypography(
            header = TextStyle(
                fontSize = xl,
                fontWeight = bold,
                fontFamily = FontFamily(JosefinSans)
            ),
            title = TextStyle(
                fontSize = m,
                fontWeight = semiBold,
                fontFamily = FontFamily(JosefinSans)
            ),
            body = TextStyle(
                fontSize = s,
                fontWeight = regular,
                fontFamily = FontFamily(JosefinSans)
            ),
            small = TextStyle(
                fontSize = xs,
                fontWeight = light,
                fontFamily = FontFamily(JosefinSans)
            )
        )
    }
}