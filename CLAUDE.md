# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

Kotlin Multiplatform (KMP) project targeting **Android**, **iOS**, and **Desktop (JVM)** using Compose Multiplatform for shared UI. Package namespace: `net.mercuryksm.rotalm`.

## Build Commands

```bash
# Desktop (JVM) - run
./gradlew :composeApp:run

# Android - build debug APK
./gradlew :composeApp:assembleDebug

# Run all common tests
./gradlew :composeApp:allTests

# Run desktop (JVM) tests only
./gradlew :composeApp:jvmTest

# iOS - open iosApp/ in Xcode and run from there
```

On Windows, use `./gradlew.bat` instead of `./gradlew`.

## Architecture

Single-module project (`composeApp`) with platform source sets:

- **`commonMain`** — Shared Kotlin/Compose UI and business logic (all platforms)
- **`androidMain`** — Android entry point (`MainActivity`) and platform implementations
- **`iosMain`** — iOS entry point (`MainViewController`) and platform implementations
- **`jvmMain`** — Desktop entry point (`main.kt`) and platform implementations
- **`commonTest`** — Shared tests using `kotlin.test`

The `expect`/`actual` pattern is used for platform-specific code (see `Platform.kt`).

Compose resources (drawables, etc.) go in `commonMain/composeResources/` and are accessed via the generated `Res` object.

## Key Configuration

- Kotlin 2.3.0, Compose Multiplatform 1.10.0
- Compose Hot Reload plugin enabled
- JVM target: 11 (Android and Desktop)
- Android minSdk 24, targetSdk/compileSdk 36
- Gradle configuration cache and build cache enabled
- Version catalog: `gradle/libs.versions.toml`
