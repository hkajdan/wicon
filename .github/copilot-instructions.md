# Wicon - AI Agent Instructions

## Project Overview
Wicon is an Android application built with **Kotlin** and **Jetpack Compose** that detects and displays gamepad/keyboard input. The app captures key events from input devices and renders them using modern Android UI patterns.

## Tech Stack
- **Language**: Kotlin 2.0.21
- **UI Framework**: Jetpack Compose with Material3
- **Build System**: Gradle with Kotlin DSL (`build.gradle.kts`)
- **Min SDK**: 28 | **Target SDK**: 36
- **Dependencies**: Defined in `gradle/libs.versions.toml` (version catalog pattern)

## Key Architecture Patterns

### Compose-First UI Architecture
- **Single Activity Pattern**: `MainActivity` extends `ComponentActivity` (Compose-native)
- **State Management**: Uses `mutableStateOf()` for reactive state (e.g., `pressedKey`)
- **Theme System**: `WiconTheme` wraps content with Material3 color schemes and dynamic theming (API 31+)
- **Scaffold-Based Layout**: Standard Material3 `Scaffold` provides system bars and padding

Example from `MainActivity.kt`:
```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()  // Use full screen with edge-to-edge UI
    setContent { WiconTheme { /* content */ } }
}
```

### Input Event Handling
- **Overrides `onKeyDown()`** to capture gamepad and keyboard events
- **Uses `InputDevice.SOURCE_GAMEPAD` and `SOURCE_KEYBOARD` flags** for source filtering
- **Logs via Android's `Log.d()`** for debugging (logcat output)
- **Updates Compose state** directly: `pressedKey.value = "..."`

## Critical Developer Workflows

### Build & Run
```bash
./gradlew build              # Full build
./gradlew assembleDebug      # Debug APK
./gradlew installDebug       # Deploy to connected device/emulator
./gradlew assemble           # All variants
```

### Testing
```bash
./gradlew test               # Unit tests (JUnit)
./gradlew connectedAndroidTest  # Instrumented tests
```

### Debugging
- **Android Logcat**: Filter by tag "TEST_MANETTE" to see gamepad input logs
- **Compose Preview**: Use `@Preview` composables for UI iteration (see `GreetingPreview`)

## Project-Specific Conventions

### Code Organization
- **Package Structure**: `com.hk.wicon` (base) → `ui.theme` (UI styling)
- **Activity**: Single `MainActivity` handles all input and UI
- **Theme Files**: Colors in XML (`colors.xml`), theme logic in Kotlin (`Theme.kt`)

### Compose Patterns Used
- **@Composable functions**: Stateless UI components (e.g., `Greeting()`)
- **@Preview**: Test UI in Android Studio without building
- **Modifier chain**: Functional UI composition (`Modifier.fillMaxSize().padding()`)

### Material3 Theming
- **Dynamic Colors**: Automatically adapts to system colors on Android 12+
- **Light/Dark Schemes**: `LightColorScheme` / `DarkColorScheme` with fallback colors
- **Typography**: Defined in `Type.kt` (not shown but part of theme system)

## Input Event Flow
```
User presses gamepad/keyboard button
→ onKeyDown() triggered
→ Event source verified (GAMEPAD or KEYBOARD)
→ Log.d("TEST_MANETTE", ...) writes to logcat
→ pressedKey.value updated (reactive state)
→ Greeting() recomposes with new text
```

## Important Notes for AI Agents
- **No Complex Services**: This is a single-activity app—avoid over-engineering components
- **State is Ephemeral**: Compose state (`mutableStateOf`) is not persisted; input updates are display-only
- **Resources in XML**: Strings (`strings.xml`), colors (`colors.xml`), themes (`themes.xml`)
- **Java 11 Compatibility**: Build uses `JavaVersion.VERSION_11`
- **Minify Disabled**: ProGuard is present but disabled in release builds (safe for refactoring)

## Common Development Tasks
- **Add New UI**: Create `@Composable` function in `MainActivity.kt` or new file in `ui/`
- **Add Input Types**: Extend `onKeyDown()` with additional `InputDevice.SOURCE_*` checks
- **Customize Theme**: Edit `Color.kt` definitions and `Theme.kt` color schemes
- **Debug Input**: Watch logcat tag "TEST_MANETTE" and cross-reference `KeyEvent` codes with Android docs
