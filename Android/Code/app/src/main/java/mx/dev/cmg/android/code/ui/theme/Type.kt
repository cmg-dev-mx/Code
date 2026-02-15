package mx.dev.cmg.android.code.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import mx.dev.cmg.android.code.R

val customFontFamily  = FontFamily(
    fonts = listOf(Font(resId = R.font.josefin_sans))
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = Typography().bodyLarge.copy(fontFamily = customFontFamily),
    bodyMedium = Typography().bodyMedium.copy(fontFamily = customFontFamily),
    bodySmall = Typography().bodySmall.copy(fontFamily = customFontFamily),
    labelLarge = Typography().labelLarge.copy(fontFamily = customFontFamily),
    labelMedium = Typography().labelMedium.copy(fontFamily = customFontFamily),
    labelSmall = Typography().labelSmall.copy(fontFamily = customFontFamily),
    displayLarge = Typography().displayLarge.copy(fontFamily = customFontFamily),
    displayMedium = Typography().displayMedium.copy(fontFamily = customFontFamily),
    displaySmall = Typography().displaySmall.copy(fontFamily = customFontFamily),
    headlineLarge = Typography().headlineLarge.copy(fontFamily = customFontFamily),
    headlineMedium = Typography().headlineMedium.copy(fontFamily = customFontFamily),
    headlineSmall = Typography().headlineSmall.copy(fontFamily = customFontFamily),
    titleLarge = Typography().titleLarge.copy(fontFamily = customFontFamily),
    titleMedium = Typography().titleMedium.copy(fontFamily = customFontFamily),
    titleSmall = Typography().titleSmall.copy(fontFamily = customFontFamily)
)