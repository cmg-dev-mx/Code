package mx.dev.cmg.android.code.ui.feature.datapersistence.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mx.dev.cmg.android.code.R
import mx.dev.cmg.android.code.ui.atomicdesign.particle.Title
import mx.dev.cmg.android.code.ui.feature.datapersistence.viewmodel.DataPersistenceEvent
import mx.dev.cmg.android.code.ui.feature.datapersistence.viewmodel.DataPersistenceUiState

@Composable
fun DataPersistenceLayout(
    uiState: DataPersistenceUiState,
    onEvent: (DataPersistenceEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.background),
        topBar = {
            Title(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                title = stringResource(R.string.notas),
                icon = R.drawable.ic_arrow_back,
                onIconClick = { onEvent(DataPersistenceEvent.NavigateBack) }
            )
        },
        floatingActionButton = {
            IconButton(
                onClick = { onEvent(DataPersistenceEvent.CreateNote) },
                modifier = Modifier.background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = MaterialTheme.shapes.small
                )
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_add),
                    contentDescription = stringResource(R.string.agregar_nota),
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DataPersistenceLayoutPreview() {
    DataPersistenceLayout(
        modifier = Modifier.fillMaxSize(),
        uiState = DataPersistenceUiState(),
        onEvent = {}
    )
}
