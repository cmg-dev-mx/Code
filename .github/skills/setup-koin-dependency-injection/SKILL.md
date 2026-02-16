---
name: setup-koin-dependency-injection
description: Configurar Koin 4.x como framework de inyección de dependencias en proyectos Android con Jetpack Compose, incluyendo Application class, módulos DI, y uso de koinViewModel() para ViewModels en composables
---

# Setup Koin Dependency Injection

## Contexto

Koin es un framework ligero de inyección de dependencias para Kotlin que utiliza DSL declarativo sin reflexión ni generación de código. Es ideal para proyectos Android modernos con Compose que necesitan gestionar dependencias de ViewModels, repositorios y otras clases sin la complejidad de Dagger/Hilt.

**Cuándo usar Koin:**
- Proyectos con Jetpack Compose y arquitectura MVVM
- Aplicaciones que requieren DI sin boilerplate excesivo
- Equipos que prefieren DSL de Kotlin sobre anotaciones

## Pasos

### 1. Añadir dependencias

En `gradle/libs.versions.toml`:
```toml
[versions]
koin = "4.1.1"

[libraries]
koin-android = { group = "io.insert-koin", name = "koin-android", version.ref = "koin" }
koin-compose = { group = "io.insert-koin", name = "koin-androidx-compose", version.ref = "koin" }
```

En `app/build.gradle.kts`:
```kotlin
dependencies {
    implementation(libs.koin.android)
    implementation(libs.koin.compose)
}
```

### 2. Crear Application class

Crear `CodeApplication.kt` (reemplaza "Code" con el nombre de tu app):
```kotlin
package mx.dev.cmg.android.code

import android.app.Application
import mx.dev.cmg.android.code.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class CodeApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CodeApplication)
            modules(appModule)
        }
    }
}
```

### 3. Crear módulo de dependencias

Crear `di/AppModule.kt`:
```kotlin
package mx.dev.cmg.android.code.di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::MainViewModel)
}
```

### 4. Registrar Application en Manifest

En `AndroidManifest.xml`:
```xml
<application
    android:name=".CodeApplication"
    ...>
```

### 5. Usar koinViewModel en Composables

En lugar de `viewModel()`, usa `koinViewModel()`:
```kotlin
import org.koin.androidx.compose.koinViewModel

@Composable
fun MyScreen() {
    val vm: MainViewModel = koinViewModel()
}
```

## Dependencias

```toml
koin = "4.1.1"
koin-android = "io.insert-koin:koin-android:4.1.1"
koin-compose = "io.insert-koin:koin-androidx-compose:4.1.1"
```

## Estructura

```
app/src/main/
├── AndroidManifest.xml (añadir android:name)
├── java/package/
│   ├── CodeApplication.kt (nueva)
│   └── di/
│       └── AppModule.kt (nueva)
gradle/
└── libs.versions.toml (actualizar)
```

## Notas

- **Módulos separados**: Para apps grandes, crea módulos por feature (`dataModule`, `domainModule`, `uiModule`)
- **Single vs Factory**: Usa `single { }` para singletons y `factory { }` para nuevas instancias
- **ViewModels**: `viewModelOf(::Class)` es la forma simplificada de declarar ViewModels
- **Testing**: Usa `koinApplication { modules(testModule) }` para tests con módulos mock
- **Debugging**: Activa logs con `.printLogger(Level.DEBUG)` en `startKoin`
