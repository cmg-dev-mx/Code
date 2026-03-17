package mx.dev.cmg.android.code.ui.feature.mvidemo.layout

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList
import mx.dev.cmg.android.code.R
import mx.dev.cmg.android.code.ui.atomicdesign.atom.CodeButton
import mx.dev.cmg.android.code.ui.atomicdesign.atom.CodeTextField
import mx.dev.cmg.android.code.ui.atomicdesign.particle.Title
import mx.dev.cmg.android.code.ui.atomicdesign.subatomic.CodeCustomTheme
import mx.dev.cmg.android.code.ui.feature.mvidemo.viewmodel.NameListEvent
import mx.dev.cmg.android.code.ui.feature.mvidemo.viewmodel.NameListUiState

@Composable
fun NameListLayout(
    uiState: NameListUiState,
    onEvent: (NameListEvent) -> Unit,
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
            title = stringResource(R.string.lista_nombres),
            icon = R.drawable.ic_arrow_back,
            onIconClick = { onEvent(NameListEvent.NavigateBack) }
        )

        AddName(
            modifier = Modifier.fillMaxWidth(),
            name = uiState.typedName,
            onNameChange = { onEvent(NameListEvent.TypedNameChange(it)) },
            onClickAdd = { onEvent(NameListEvent.AddName) },
            isLoading = uiState.isLoading
        )

        AnimatedVisibility(uiState.names.isEmpty()) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.no_hay_nombres),
                style = CodeCustomTheme.Typography.body,
                color = CodeCustomTheme.Color.onBackground.copy(alpha = 0.5f),
                textAlign = TextAlign.Center
            )
        }

        AnimatedVisibility(uiState.names.isNotEmpty()) {
            NameList(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                names = uiState.names
            )
        }
    }
}

@Composable
private fun NameList(
    names: PersistentList<String>,
    modifier: Modifier = Modifier
) {
    FlowRow(
        modifier = modifier.verticalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(CodeCustomTheme.Spacing.xs),
        verticalArrangement = Arrangement.spacedBy(CodeCustomTheme.Spacing.xs)
    ) {
        names.forEach { name ->
            Text(
                modifier = Modifier
                    .wrapContentWidth()
                    .background(
                        color = CodeCustomTheme.Color.secondary,
                        shape = RoundedCornerShape(CodeCustomTheme.Shape.large)
                    )
                    .padding(CodeCustomTheme.Spacing.xs),
                color = CodeCustomTheme.Color.onSecondary,
                style = CodeCustomTheme.Typography.body,
                text = name
            )
        }
    }
}

@Composable
private fun AddName(
    name: String,
    onNameChange: (String) -> Unit,
    onClickAdd: () -> Unit,
    modifier: Modifier = Modifier,
    isLoading: Boolean
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(CodeCustomTheme.Spacing.xs),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CodeTextField(
            modifier = Modifier.weight(1f),
            label = {
                Text(
                    style = CodeCustomTheme.Typography.body,
                    text = stringResource(R.string.escribe_nombre)
                )
            },
            value = name,
            onValueChange = { onNameChange(it) }
        )

        CodeButton(
            enabled = name.isNotEmpty(),
            onClick = { onClickAdd() }
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(CodeCustomTheme.Icon.s),
                    color = CodeCustomTheme.Color.onPrimary,
                    strokeWidth = 2.dp
                )
            } else {
                Icon(
                    modifier = Modifier.size(CodeCustomTheme.Icon.s),
                    painter = painterResource(R.drawable.ic_add),
                    contentDescription = null
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    NameListLayout(
        modifier = Modifier.fillMaxSize(),
        uiState = NameListUiState(
            names = listOf(
                "Carlos",
                "María",
                "José",
                "Ana",
                "Luis",
                "Sofía",
                "Miguel",
                "Lucía",
                "David",
                "Isabella",
                "Juan",
                ).toPersistentList(),
            typedName = "Jesús"
        ),
        onEvent = {}
    )
}