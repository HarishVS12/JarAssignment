# Jar Assignment App

This document provides instructions on how to run the Jar Assignment Android project and notes about its implementation.

## How to Run the Project

1.  **Prerequisites:** Android Studio (latest, e.g., Hedgehog), Android SDK, and an Emulator/Device.
2.  **Clone Repository:** Use `git clone https://github.com/HarishVS12/JarAssignment` and open the project in Android Studio.
3.  **Gradle Sync & Build:** Allow Android Studio to sync Gradle files and then build the project ("Build" > "Make Project").
4.  **Run:** Select your target device/emulator and click the "Run 'app'" button.

## Dependencies or Setup Required

All dependencies (Kotlin, Jetpack Compose, Navigation, Hilt, Lottie, Retrofit, Gson, etc.) are managed by Gradle and listed in `app/build.gradle.kts`. No manual setup is needed beyond an internet connection for the initial Gradle sync.

## Additional Notes About Implementation

*  All the UI colors and icons might differ from the original figma file shared as all the data has been captured from the given API. So there might be visual differences in the
   application from the design shared for the assignment. Thanks!

## Project Structure

app/
├── src/main/java/com/harish/jarassignment/
│   ├── core/         # Navigation, Utils
│   ├── data/         # Network, API DTOs, Mappers, Repository Interface.
│   ├── domain/       # Domain Models, Use Cases, Repository Impl.
│   ├── di/           # Hilt Modules
│   └── presentation/ # UI (Components, Screens, ViewModels, States)




