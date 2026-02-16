---
name: setup-navigation3
description: Configurar Navigation3 en proyectos Android con Compose para implementar navegación declarativa basada en backstack, incluyendo plugin de serialización, dependencias, NavDisplay, y NavHost con decoradores para ViewModel y SavedState
---

# Setup Navigation3 en Android con Compose

## Contexto

Navigation3 es la biblioteca oficial de Android para gestionar navegación en aplicaciones Compose. Usa un enfoque declarativo basado en backstack, decoradores de entrada (para ViewModel y SavedState), y claves serializables para representar destinos.

**Casos de uso:**
- Apps con múltiples pantallas y navegación compleja
- Necesidad de compartir ViewModels entre destinos
- Persistencia de estado durante cambios de configuración
- Gestión explícita del back stack

## Pasos

### 1. Configurar versiones en gradle/libs.versions.toml

En `[versions]`:
```toml
nav3Core = "1.0.0"
nav3ViewModel = "2.10.0"
```

En `[libraries]`:
```toml
androidx-navigation3-runtime = { group = "androidx.navigation3", name = "navigation3-runtime", version.ref = "nav3Core" }
androidx-navigation3-ui = { group = "androidx.navigation3", name = "navigation3-ui", version.ref = "nav3Core" }
androidx-navigation3-viewmodel = { group = "androidx.lifecycle", name = "lifecycle-viewmodel-navigation3", version.ref = "nav3ViewModel" }
```

En `[plugins]`:
```toml
kotlin-serialization = { id = "org.jetbrains.kotlin.plugin.serialization", version.ref = "kotlin" }
```

### 2. Añadir plugins en build.gradle.kts (raíz)

```kotlin
plugins {
    // Otros plugins...
    alias(libs.plugins.kotlin.serialization) apply false
}
```

### 3. Añadir plugin en app/build.gradle.kts

```kotlin
plugins {
    // Otros plugins...
    alias(libs.plugins.kotlin.serialization)
}
```

### 4. Añadir dependencias en app/build.gradle.kts

```kotlin
dependencies {
    // Otras dependencias...
    implementation(libs.androidx.navigation3.runtime)
    implementation(libs.androidx.navigation3.ui)
    implementation(libs.androidx.navigation3.viewmodel)
}
```

### 5. Crear MainNavHost.kt

```kotlin
package mx.dev.cmg.android.code.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import kotlinx.serialization.Serializable

@Serializable data object Main: NavKey

@Composable
fun MainNavHost(modifier: Modifier = Modifier) {
    val backStack = rememberNavBackStack(Main)

    NavDisplay(
        modifier = modifier,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        backStack = backStack,
        entryProvider = entryProvider {
            entry<Main> {
                MainLayout(modifier = Modifier.fillMaxSize())
            }
        }
    )
}
```

### 6. Refactorizar MainActivity

Cambiar de usar el layout directamente a usar MainNavHost:

```kotlin
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
```

## Dependencias

- `androidx.navigation3:navigation3-runtime:1.0.0`
- `androidx.navigation3:navigation3-ui:1.0.0`
- `androidx.lifecycle:lifecycle-viewmodel-navigation3:2.10.0`
- Plugin: `org.jetbrains.kotlin.plugin.serialization`

## Notas

- **NavKey serializable:** Cada destino se representa con una clase/objeto serializable usando `@Serializable`
- **entryDecorators:** Agregan funcionalidad como persistencia de estado (`rememberSaveableStateHolderNavEntryDecorator`) o scope de ViewModel (`rememberViewModelStoreNavEntryDecorator`)
- **entryProvider:** Define qué UI renderizar para cada NavKey usando `entry<Type>`
- **rememberNavBackStack:** Inicializa el backstack con una ruta inicial
- Para agregar más pantallas, define nuevos NavKey y añade entradas en el entryProvider
- Para navegar: usa `backStack.push(NuevoNavKey)`, para volver: `backStack.pop()`
