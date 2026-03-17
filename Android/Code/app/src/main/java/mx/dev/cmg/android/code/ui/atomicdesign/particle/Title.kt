package mx.dev.cmg.android.code.ui.atomicdesign.particle

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mx.dev.cmg.android.code.R
import mx.dev.cmg.android.code.ui.atomicdesign.subatomic.CodeCustomTheme

@Composable
fun Title(
    title: String,
    @DrawableRes icon: Int,
    modifier: Modifier = Modifier,
    iconDescription: String? = null,
    onIconClick: (() -> Unit)? = null
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(CodeCustomTheme.Spacing.s),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            modifier = Modifier.size(CodeCustomTheme.Icon.l),
            onClick = { onIconClick?.invoke() },
            enabled = onIconClick != null
        ){
            Icon(
                modifier = Modifier.fillMaxSize(),
                tint = CodeCustomTheme.Color.onBackground,
                painter = painterResource(icon),
                contentDescription = iconDescription
            )
        }

        Text(
            style = CodeCustomTheme.Typography.header,
            color = CodeCustomTheme.Color.onBackground,
            text = title
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    Title(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        title = "Title",
        icon = R.drawable.ic_launcher_foreground,
        iconDescription = "Info Icon"
    )
}