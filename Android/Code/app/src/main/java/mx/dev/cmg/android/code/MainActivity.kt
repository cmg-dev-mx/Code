package mx.dev.cmg.android.code

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import mx.dev.cmg.android.code.ui.atomicdesign.subatomic.CodeTheme
import mx.dev.cmg.android.code.ui.navigation.MainNavHost
import mx.dev.cmg.android.code.ui.theme.ThemeViewModel
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val themeViewModel: ThemeViewModel = koinViewModel()
            val colors by themeViewModel.colors.collectAsStateWithLifecycle()

            CodeTheme(colors = colors) {
                MainNavHost(
                    modifier = Modifier
                        .systemBarsPadding()
                        .fillMaxSize()
                )
            }
        }
    }
}