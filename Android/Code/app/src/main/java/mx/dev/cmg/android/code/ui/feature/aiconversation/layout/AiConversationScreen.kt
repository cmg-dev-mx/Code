package mx.dev.cmg.android.code.ui.feature.aiconversation.layout

import android.Manifest
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import mx.dev.cmg.android.code.ui.feature.aiconversation.viewmodel.AiConversationEvent
import mx.dev.cmg.android.code.ui.feature.aiconversation.viewmodel.AiConversationSideEffect
import mx.dev.cmg.android.code.ui.feature.aiconversation.viewmodel.AiConversationViewModel
import mx.dev.cmg.android.code.ui.util.collectAsEffect
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun AiConversationScreen(
    onNavigation: (AiConversationSideEffect) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AiConversationViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    viewModel.sideEffect.collectAsEffect { sideEffect ->
        onNavigation(sideEffect)
    }

    val audioPermissionState = rememberPermissionState(Manifest.permission.RECORD_AUDIO)
    var pendingStartListening by remember { mutableStateOf(false) }

    LaunchedEffect(audioPermissionState.status) {
        if (audioPermissionState.status.isGranted && pendingStartListening) {
            pendingStartListening = false
            viewModel.onEvent(AiConversationEvent.StartListening)
        }
    }

    AiConversationLayout(
        modifier = modifier,
        uiState = uiState,
        onEvent = { event ->
            when (event) {
                is AiConversationEvent.StartListening -> {
                    if (audioPermissionState.status.isGranted) {
                        viewModel.onEvent(event)
                    } else {
                        pendingStartListening = true
                        audioPermissionState.launchPermissionRequest()
                    }
                }
                else -> viewModel.onEvent(event)
            }
        }
    )
}

