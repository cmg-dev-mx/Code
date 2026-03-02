---
name: update-gradle-versions
description: Actualizar versiones de dependencias en archivos libs.versions.toml de proyectos Android con Gradle, verificando compatibilidad entre librerías AndroidX, Kotlin y Compose BOM
---

# Actualizar Versiones de Gradle

Este skill ayuda a actualizar versiones de librerías en el archivo `libs.versions.toml` de proyectos Android.

## Contexto

Los proyectos Android modernos usan Gradle Version Catalogs (archivo `libs.versions.toml`) para centralizar las versiones de dependencias.

## Pasos

1. Localiza el archivo `gradle/libs.versions.toml` en el proyecto
2. Identifica la sección `[versions]`
3. Actualiza las versiones solicitadas manteniendo el formato:
   ```toml
   libraryName = "version"
   ```
4. Verifica que las versiones sean compatibles entre sí
5. Aplica los cambios

## Ejemplos de Actualizaciones Comunes

- **Kotlin**: Actualizar a versiones estables (ej: `2.0.21` → `2.3.0`)
- **Compose BOM**: Seguir el formato año.mes (ej: `2024.09.00` → `2026.01.00`)
- **AndroidX**: Verificar compatibilidad con la versión de Kotlin

## Notas

- Siempre usa versiones estables en producción
- Revisa las release notes de cada librería antes de actualizar
- Considera el impacto en otras dependencias del proyecto
