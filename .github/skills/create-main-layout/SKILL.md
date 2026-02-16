---
name: create-main-layout
description: Refactorizar MainActivity de un proyecto Android Compose para usar un composable Layout separado, eliminar código de ejemplo del template, e implementar edge-to-edge con system bars padding
---

# Crear MainLayout y refactorizar MainActivity

## Contexto

Aplicar este patrón cuando se inicia un proyecto Android con Jetpack Compose y se necesita:
- Separar la lógica de UI de MainActivity
- Eliminar el código de ejemplo generado por el template
- Establecer una estructura limpia para el feature principal
- Implementar edge-to-edge con system bars padding

## Pasos

1. **Crear el archivo MainLayout.kt** en `ui/feature/main/layout/`
   - Definir un composable `MainLayout` que reciba un `Modifier`
   - Usar `Column` como contenedor base con padding de 16.dp
   - Agregar preview para desarrollo

2. **Refactorizar MainActivity.kt**
   - Eliminar el composable `Greeting` y su preview
   - Remover imports innecesarios (Scaffold, Text, padding, preview)
   - Importar `systemBarsPadding`, `MainLayout` y `fillMaxSize`
   - Reemplazar el contenido de `setContent` con `MainLayout`
   - Aplicar modifiers: `systemBarsPadding()` y `fillMaxSize()`

## Estructura

```
app/src/main/java/mx/dev/cmg/android/code/
├── MainActivity.kt                           # Refactorizado
└── ui/
    └── feature/
        └── main/
            └── layout/
                └── MainLayout.kt             # Nuevo archivo
```

## Notas

- `systemBarsPadding()` respeta las barras del sistema en modo edge-to-edge
- El padding interno (16.dp) se maneja en MainLayout, no en MainActivity
- El preview de MainLayout usa `fillMaxSize()` para simular el tamaño real
- Mantener MainActivity limpia: solo configuración de tema y layout principal
- Este patrón facilita testing y reusabilidad del layout
