package mx.dev.cmg.android.code.ui.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

/**
 * Consume eventos de side effects de forma segura, garantizando que:
 * - Los eventos se consumen una sola vez
 * - Se respeta el ciclo de vida del Composable
 * - No se pierden eventos durante recomposiciones
 */
@Composable
fun <T> Flow<T>.collectAsEffect(
    lifecycleState: Lifecycle.State = Lifecycle.State.STARTED,
    onEvent: suspend (T) -> Unit
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    
    LaunchedEffect(this, lifecycleOwner) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(lifecycleState) {
            this@collectAsEffect.collect { event ->
                withContext(Dispatchers.Main.immediate) {
                    onEvent(event)
                }
            }
        }
    }
}
