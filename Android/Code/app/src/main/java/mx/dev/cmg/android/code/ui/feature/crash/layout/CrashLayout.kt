package mx.dev.cmg.android.code.ui.feature.crash.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mx.dev.cmg.android.code.R
import mx.dev.cmg.android.code.ui.atomicdesign.particle.Title

@Composable
fun CrashLayout(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Title(
            modifier = Modifier.fillMaxWidth(),
            title = stringResource(R.string.ejemplo_crash),
            icon = R.drawable.ic_arrow_back,
        )

        Button(
            onClick = { throw RuntimeException("¡Esto es un crash de ejemplo!") }
        ) {
            Text(text = stringResource(R.string.provocar_crash))
        }
    }
}

@Preview
@Composable
private fun Preview() {
    CrashLayout(modifier = Modifier.fillMaxSize())
}