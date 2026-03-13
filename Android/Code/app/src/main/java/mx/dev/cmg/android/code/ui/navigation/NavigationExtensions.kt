package mx.dev.cmg.android.code.ui.navigation

import android.content.Context
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.net.toUri
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey

fun NavBackStack<in NavKey>.navigateTo(destination: NavKey) {
    add(destination)
}

fun NavBackStack<out NavKey>.navigateBack() {
    removeLastOrNull()
}

fun launchCustomTab(context: Context, url: String) {
    val builder = CustomTabsIntent.Builder().apply {
        setShowTitle(true)
    }

    val customTabsIntent = builder.build()
    customTabsIntent.launchUrl(context, url.toUri())
}