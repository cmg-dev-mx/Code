package mx.dev.cmg.android.code.ui.feature.web.layout

import android.annotation.SuppressLint
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import mx.dev.cmg.android.code.R
import mx.dev.cmg.android.code.ui.atomicdesign.particle.Title
import mx.dev.cmg.android.code.ui.atomicdesign.subatomic.CodeCustomTheme
import mx.dev.cmg.android.code.ui.feature.web.viewmodel.WebEvent
import mx.dev.cmg.android.code.ui.feature.web.viewmodel.WebUiState

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebLayout(
    uiState: WebUiState,
    onEvent: (WebEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    val isPreview = LocalInspectionMode.current

    Column(
        modifier = modifier
            .background(CodeCustomTheme.Color.background)
            .padding(CodeCustomTheme.Spacing.s),
        verticalArrangement = Arrangement.spacedBy(CodeCustomTheme.Spacing.xs)
    ) {
        Title(
            modifier = Modifier.fillMaxWidth(),
            title = stringResource(id = R.string.web_view),
            icon = R.drawable.ic_arrow_back,
            onIconClick = { onEvent(WebEvent.NavigateBack) }
        )

        if (isPreview) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .border(
                        width = 1.dp,
                        color = CodeCustomTheme.Color.secondary.copy(alpha = 0.5f),
                        shape = RoundedCornerShape(CodeCustomTheme.Shape.medium)
                    )
                    .background(
                        color = CodeCustomTheme.Color.secondary.copy(alpha = 0.1f),
                        shape = RoundedCornerShape(CodeCustomTheme.Shape.medium)
                    )
            )
        } else {
            AndroidView(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .border(
                        width = 1.dp,
                        color = CodeCustomTheme.Color.secondary.copy(alpha = 0.5f),
                        shape = RoundedCornerShape(CodeCustomTheme.Shape.medium)
                    ),
                factory = {
                    WebView(it).apply {
                        webViewClient = WebViewClient()

                    }
                },
                update = {
                    it.settings.javaScriptEnabled = true
                    it.loadUrl(uiState.url)
                }
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    WebLayout(
        modifier = Modifier.fillMaxSize(),
        uiState = WebUiState(),
        onEvent = {}
    )
}