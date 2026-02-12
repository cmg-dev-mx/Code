package mx.dev.cmg.android.code

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.ui.Modifier
import mx.dev.cmg.android.code.ui.navigation.MainNavHost
import mx.dev.cmg.android.code.ui.theme.CodeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CodeTheme {
                MainNavHost(
                    modifier = Modifier
                        .systemBarsPadding()
                        .fillMaxSize()
                )
            }
        }
    }
}