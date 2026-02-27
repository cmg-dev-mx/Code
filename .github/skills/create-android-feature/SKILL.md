---
name: create-android-feature
description: Crear una nueva feature completa en proyecto Android Compose siguiendo arquitectura MVI con ViewModel, UiState, Event, SideEffect, layouts separados (Screen y Layout), integración con navegación Navigation3, y registro en Koin. Incluye estructura de directorios, composables preview-friendly, manejo de estados con StateFlow y Channels, y patrones de extensiones del proyecto
---

# Crear New Feature en Android con Compose y MVI

## Contexto

Este skill te ayuda a crear una nueva feature completa en un proyecto Android con Jetpack Compose siguiendo el patrón MVI (Model-View-Intent). El proyecto usa:

- **Arquitectura MVI**: Separación clara entre estado (UiState), eventos (Event), y efectos secundarios (SideEffect)
- **Jetpack Compose**: UI declarativa
- **Koin**: Inyección de dependencias
- **Navigation3**: Navegación declarativa basada en backstack
- **StateFlow y Channels**: Manejo reactivo de estado y efectos

La estructura de features sigue una organización modular donde cada feature tiene sus propios composables, ViewModels, y modelos de UI separados.

## Estructura de Directorios

Cada feature debe crearse bajo `ui/feature/` con la siguiente estructura:

```
ui/feature/
└── nombre-feature/
    ├── layout/
    │   ├── NombreFeatureScreen.kt      # Conecta ViewModel con Layout
    │   └── NombreFeatureLayout.kt      # UI composable sin estado
    ├── viewmodel/
    │   ├── NombreFeatureViewModel.kt   # Lógica MVI
    │   ├── NombreFeatureUiState.kt     # Estado de UI
    │   ├── NombreFeatureEvent.kt       # Eventos de usuario
    │   └── NombreFeatureSideEffect.kt  # Efectos secundarios
    └── model/ (opcional)
        └── ModelosUI.kt                 # Modelos específicos de UI
```

## Pasos

### 1. Crear Estructura de Directorios

Crear los directorios para la nueva feature (reemplaza `nombre-feature` con el nombre en minúsculas separado por guiones):

```bash
mkdir -p app/src/main/java/[paquete]/ui/feature/nombre-feature/layout
mkdir -p app/src/main/java/[paquete]/ui/feature/nombre-feature/viewmodel
mkdir -p app/src/main/java/[paquete]/ui/feature/nombre-feature/model  # Solo si es necesario
```

### 2. Crear UiState

Archivo: `viewmodel/NombreFeatureUiState.kt`

Define el estado de la UI como una data class inmutable:

```kotlin
package [paquete].ui.feature.nombrefeature.viewmodel

data class NombreFeatureUiState(
    val isLoading: Boolean = false
)
```

**Consideraciones:**
- Usa valores por defecto para el estado inicial
- Toda la información necesaria para renderizar la UI debe estar aquí
- Mantén la data class inmutable

### 3. Crear Event

Archivo: `viewmodel/NombreFeatureEvent.kt`

Define las acciones que el usuario puede realizar como una sealed interface:

```kotlin
package [paquete].ui.feature.nombrefeature.viewmodel

sealed interface NombreFeatureEvent {
    data object NavigateBack : NombreFeatureEvent
}
```

**Consideraciones:**
- Usa `data object` para eventos sin datos
- Usa `data class` para eventos que requieran parámetros
- Los nombres deben ser descriptivos de la acción del usuario

### 4. Crear SideEffect

Archivo: `viewmodel/NombreFeatureSideEffect.kt`

Define los efectos secundarios (navegación, toasts, etc.) como sealed interface:

```kotlin
package [paquete].ui.feature.nombrefeature.viewmodel

sealed interface NombreFeatureSideEffect {
    data object NavigateBack : NombreFeatureSideEffect
}
```

**Consideraciones:**
- Los SideEffects son acciones de "una sola vez"
- Típicamente se usan para navegación, mostrar toasts, snackbars, etc.
- No forman parte del estado persistente de la UI

### 5. Crear ViewModel

Archivo: `viewmodel/NombreFeatureViewModel.kt`

Implementa el ViewModel con el patrón MVI:

```kotlin
package [paquete].ui.feature.nombrefeature.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import [paquete].ui.base.viewmodel.launchEvent
import [paquete].ui.base.viewmodel.sendEffect
import [paquete].ui.base.viewmodel.update

class NombreFeatureViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(NombreFeatureUiState())
    val uiState: StateFlow<NombreFeatureUiState> = _uiState.asStateFlow()

    private val _sideEffect = Channel<NombreFeatureSideEffect>(Channel.BUFFERED)
    val sideEffect = _sideEffect.receiveAsFlow()

    fun onEvent(event: NombreFeatureEvent) = launchEvent {
        when (event) {
            is NombreFeatureEvent.NavigateBack -> _sideEffect.sendEffect(NombreFeatureSideEffect.NavigateBack)
        }
    }
}
```

**Consideraciones:**
- Usa `StateFlow` para el estado de UI
- Usa `Channel` para side effects (se consumen una sola vez)
- `launchEvent` ejecuta operaciones en viewModelScope
- `update` es una extensión para actualizar el estado de forma inmutable
- `sendEffect` envía efectos al Channel
- Toda la lógica de negocio debe ser privada

### 6. Crear Layout (UI Composable)

Archivo: `layout/NombreFeatureLayout.kt`

Crea el composable de UI puro (sin ViewModels):

```kotlin
package [paquete].ui.feature.nombrefeature.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import [paquete].R
import [paquete].ui.atomicdesign.particle.Title
import [paquete].ui.feature.nombrefeature.viewmodel.NombreFeatureEvent
import [paquete].ui.feature.nombrefeature.viewmodel.NombreFeatureUiState

@Composable
fun NombreFeatureLayout(
    uiState: NombreFeatureUiState,
    onEvent: (NombreFeatureEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.background)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Tu UI aquí",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun NombreFeatureLayoutPreview() {
    NombreFeatureLayout(
        uiState = NombreFeatureUiState(),
        onEvent = {}
    )
}
```

**Consideraciones:**
- Este composable NO debe tener dependencias de ViewModel
- Debe ser 100% preview-friendly
- Recibe `uiState` y emite eventos vía callback `onEvent`
- Usa Material3 components
- Incluye siempre un @Preview

### 7. Crear Screen (Conexión con ViewModel)

Archivo: `layout/NombreFeatureScreen.kt`

Conecta el Layout con el ViewModel:

```kotlin
package [paquete].ui.feature.nombrefeature.layout

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import [paquete].ui.feature.nombrefeature.viewmodel.NombreFeatureSideEffect
import [paquete].ui.feature.nombrefeature.viewmodel.NombreFeatureViewModel
import [paquete].ui.util.collectAsEffect
import org.koin.androidx.compose.koinViewModel

@Composable
fun NombreFeatureScreen(
    onNavigation: (NombreFeatureSideEffect) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: NombreFeatureViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    viewModel.sideEffect.collectAsEffect { sideEffect ->
        onNavigation(sideEffect)
    }

    NombreFeatureLayout(
        modifier = modifier,
        uiState = uiState,
        onEvent = viewModel::onEvent
    )
}
```

**Consideraciones:**
- Este es el único composable que conoce el ViewModel
- Usa `koinViewModel()` para obtener el ViewModel
- Usa `collectAsStateWithLifecycle()` para el estado
- Usa `collectAsEffect` para los side effects
- Delega el manejo de navegación al caller vía callback

### 8. Registrar ViewModel en Koin

Archivo: `di/AppModule.kt`

Agrega el ViewModel al módulo de Koin:

```kotlin
val appModule = module {
    // ... otros módulos ...

    // ViewModels
    viewModelOf(::NombreFeatureViewModel)
}
```

### 9. Crear NavKey para Navigation3

Archivo: `ui/navigation/MainNavHost.kt`

Agrega el NavKey serializable para la feature:

```kotlin
@Serializable data object NombreFeature : NavKey
```

**Consideraciones:**
- El nombre del NavKey debe ser en PascalCase
- Usa `data object` para navegación sin parámetros
- Usa `data class` si necesitas pasar parámetros:
  ```kotlin
  @Serializable data class DetailScreen(val id: String) : NavKey
  ```

### 10. Agregar Entry en NavHost

En el mismo archivo `MainNavHost.kt`, dentro de `entryProvider`, agrega:

```kotlin
entry<NombreFeature> {
    NombreFeatureScreen(
        modifier = Modifier.fillMaxSize(),
        onNavigation = { sideEffect ->
            when (sideEffect) {
                NombreFeatureSideEffect.NavigateBack ->
                    backStack.navigateBack()
                // Maneja otros side effects aquí
            }
        }
    )
}
```

**Consideraciones:**
- El tipo genérico `entry<T>` debe coincidir con el NavKey
- El manejo de navegación se hace en el callback `onNavigation`
- Usa `backStack.navigateTo(NavKey)` para navegar a otra pantalla
- Usa `backStack.navigateBack()` para regresar

### 11. Agregar Strings (Opcional)

Archivo: `res/values/strings.xml`

```xml
<string name="titulo_feature">Mi Nueva Feature</string>
```

## Ejemplo Completo

Para una feature llamada "Profile":

**Directorios:**
```
ui/feature/profile/
  ├── layout/
  │   ├── ProfileScreen.kt
  │   └── ProfileLayout.kt
  └── viewmodel/
      ├── ProfileViewModel.kt
      ├── ProfileUiState.kt
      ├── ProfileEvent.kt
      └── ProfileSideEffect.kt
```

**ProfileUiState.kt:**
```kotlin
data class ProfileUiState(
    val name: String = "",
    val email: String = "",
    val isLoading: Boolean = false
)
```

**ProfileEvent.kt:**
```kotlin
sealed interface ProfileEvent {
    data object NavigateBack : ProfileEvent
    data object SaveProfile : ProfileEvent
    data class UpdateName(val name: String) : ProfileEvent
}
```

**ProfileSideEffect.kt:**
```kotlin
sealed interface ProfileSideEffect {
    data object NavigateBack : ProfileSideEffect
    data class ShowToast(val message: String) : ProfileSideEffect
}
```

**Navigation (MainNavHost.kt):**
```kotlin
// NavKey
@Serializable data object Profile : NavKey

// Entry en entryProvider
entry<Profile> {
    ProfileScreen(
        modifier = Modifier.fillMaxSize(),
        onNavigation = { sideEffect ->
            when (sideEffect) {
                ProfileSideEffect.NavigateBack -> backStack.navigateBack()
                is ProfileSideEffect.ShowToast -> {
                    // Mostrar toast
                }
            }
        }
    )
}
```

## Notas Importantes

1. **Naming Conventions**:
   - Directorios: lowercase con guiones (ej: `user-profile`)
   - Clases: PascalCase con sufijo descriptivo (ej: `ProfileViewModel`, `ProfileUiState`)
   - NavKeys: PascalCase sin sufijos (ej: `Profile`, `UserDetail`)

2. **Separación de Responsabilidades**:
   - **Layout**: UI pura, sin lógica de negocio, preview-friendly
   - **Screen**: Conexión con ViewModel y manejo de side effects
   - **ViewModel**: Lógica de negocio y manejo de estado
   - **UiState**: Estado de la UI
   - **Event**: Acciones del usuario
   - **SideEffect**: Efectos secundarios de una sola vez

3. **Testing**:
   - Los Layouts son fáciles de previsualizar con @Preview
   - Los ViewModels se pueden testear sin dependencias de UI
   - El UiState es una data class testeable

4. **Performance**:
   - `collectAsStateWithLifecycle()` respeta el ciclo de vida
   - `collectAsEffect` garantiza que los side effects se consuman una sola vez
   - StateFlow solo emite cuando el valor cambia

5. **Navegación**:
   - Los side effects de navegación se manejan en el NavHost
   - Usa `navigateTo()` para ir a una nueva pantalla
   - Usa `navigateBack()` para regresar
   - Puedes pasar parámetros usando data class en NavKey

6. **Dependencias Necesarias**:
   - Koin para inyección de dependencias
   - Navigation3 para navegación
   - Compose Material3 para componentes de UI
   - lifecycle-compose para `collectAsStateWithLifecycle`

7. **Extensiones del Proyecto**:
   - `launchEvent`: Ejecuta código en viewModelScope
   - `update`: Actualiza StateFlow de forma inmutable
   - `sendEffect`: Envía efectos al Channel
   - `collectAsEffect`: Consume side effects de forma segura

8. **Package Naming**:
   - Reemplaza `[paquete]` con el package base del proyecto
   - Ejemplo: `mx.dev.cmg.android.code`
