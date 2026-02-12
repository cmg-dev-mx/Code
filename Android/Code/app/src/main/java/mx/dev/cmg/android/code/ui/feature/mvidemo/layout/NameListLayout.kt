package mx.dev.cmg.android.code.ui.feature.mvidemo.layout

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
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
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mx.dev.cmg.android.code.R
import mx.dev.cmg.android.code.ui.atomicdesign.particle.Title
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
            .background(color = MaterialTheme.colorScheme.background)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
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
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
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
    names: List<String>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(names) { name ->
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(8.dp),
                text = name,
                style = MaterialTheme.typography.bodyLarge
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
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            modifier = Modifier.weight(1f),
            label = {
                Text(
                    text = stringResource(R.string.escribe_nombre),
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            value = name,
            onValueChange = { onNameChange(it) }
        )

        Button(
            enabled = name.isNotEmpty(),
            onClick = { onClickAdd() }
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp),
                    color = MaterialTheme.colorScheme.onPrimary,
                    strokeWidth = 2.dp
                )
            } else {
                Icon(
                    modifier = Modifier.size(20.dp),
                    tint = MaterialTheme.colorScheme.onPrimary,
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
            names = listOf("Carlos", "María", "José"),
            typedName = "Jesús"
        ),
        onEvent = {}
    )
}