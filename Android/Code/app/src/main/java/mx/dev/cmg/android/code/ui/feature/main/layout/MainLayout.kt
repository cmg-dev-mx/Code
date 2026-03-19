package mx.dev.cmg.android.code.ui.feature.main.layout

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.collections.immutable.toPersistentList
import mx.dev.cmg.android.code.R
import mx.dev.cmg.android.code.ui.atomicdesign.particle.Title
import mx.dev.cmg.android.code.ui.atomicdesign.subatomic.CodeCustomTheme
import mx.dev.cmg.android.code.ui.feature.main.model.FeatureUI
import mx.dev.cmg.android.code.ui.feature.main.viewmodel.MainEvent
import mx.dev.cmg.android.code.ui.feature.main.viewmodel.MainUiState

@Composable
fun MainLayout(
    uiState: MainUiState,
    onEvent: (MainEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(color = CodeCustomTheme.Color.background)
            .padding(CodeCustomTheme.Spacing.s),
        verticalArrangement = Arrangement.spacedBy(CodeCustomTheme.Spacing.s)
    ) {
        Title(
            modifier = Modifier.fillMaxWidth(),
            title = stringResource(R.string.app_name),
            icon = R.drawable.ic_launcher_foreground
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(CodeCustomTheme.Spacing.xs)
        ) {
            items(uiState.availableFeatures) { feature ->
                MainItem(
                    modifier = Modifier.fillMaxWidth(),
                    title = stringResource(feature.displayName),
                    icon = feature.icon,
                    onClick = { onEvent(feature.onClickEvent) }
                )
            }
        }
    }
}

@Composable
private fun MainItem(
    title: String,
    @DrawableRes icon: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .clip(shape = RoundedCornerShape(CodeCustomTheme.Spacing.s))
            .background(color = CodeCustomTheme.Color.surface)
            .clickable { onClick() }
            .padding(CodeCustomTheme.Spacing.s),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            style = CodeCustomTheme.Typography.title,
            color = CodeCustomTheme.Color.onSurface,
            text = title,
        )

        Icon(
            modifier = Modifier.size(CodeCustomTheme.Icon.s),
            tint = CodeCustomTheme.Color.onSurface,
            painter = painterResource(icon),
            contentDescription = null
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    MainLayout(
        uiState = MainUiState(
            isLoading = false,
            availableFeatures = listOf(
                FeatureUI.MVI
            ).toPersistentList()
        ),
        onEvent = {},
        modifier = Modifier.fillMaxSize()
    )
}