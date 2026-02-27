package mx.dev.cmg.android.code.ui.feature.datapersistence.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mx.dev.cmg.android.code.ui.feature.datapersistence.viewmodel.NoteDetailEvent

@Composable
fun NoteDetailLayout(
    onEvent: (NoteDetailEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        Text("Detalle de nota")
    }
}

@Preview
@Composable
private fun Preview() {
    NoteDetailLayout(
        modifier = Modifier.fillMaxSize(),
        onEvent = {}
    )
}