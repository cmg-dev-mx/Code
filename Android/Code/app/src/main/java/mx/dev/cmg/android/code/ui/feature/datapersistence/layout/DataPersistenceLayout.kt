package mx.dev.cmg.android.code.ui.feature.datapersistence.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
    Column(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.background)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Title(
            modifier = Modifier.fillMaxWidth(),
            title = "Persistencia de Datos",
            icon = R.drawable.ic_arrow_back,
            onIconClick = { onEvent(DataPersistenceEvent.NavigateBack) }
        )

        Text(
            text = "Funcionalidad de persistencia de datos",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DataPersistenceLayoutPreview() {
    DataPersistenceLayout(
        uiState = DataPersistenceUiState(),
        onEvent = {}
    )
}
