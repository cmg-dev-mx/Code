package mx.dev.cmg.android.code.ui.navigation

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey

fun NavBackStack<in NavKey>.navigateTo(destination: NavKey) {
    add(destination)
}

fun NavBackStack<out NavKey>.navigateBack() {
    removeLastOrNull()
}