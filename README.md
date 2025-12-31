# Kids Notes App

A secure, biometric-gated note-taking application built with modern Android architecture components.

## Features

✅ **Biometric Authentication** - Secure login using fingerprint or face recognition  
✅ **Notes Management** - Create, read, update, and delete notes  
✅ **Room Database** - Persistent local storage with LiveData  
✅ **MVVM Architecture** - Clean separation of concerns with ViewModel pattern  
✅ **Material Design** - Beautiful, intuitive UI following Material Design guidelines

## Technical Stack

- **Language**: Kotlin
- **Architecture**: MVVM (Model-View-ViewModel)
- **Database**: Room Persistence Library
- **Authentication**: AndroidX Biometric API
- **UI Components**: Material Design Components
- **Async Operations**: Kotlin Coroutines + LiveData

## Project Structure

```
app/
├── src/main/
│   ├── java/com/kidsapp/notes/
│   │   ├── database/       # Room database entities, DAOs, and repository
│   │   ├── ui/             # Activities and RecyclerView adapters
│   │   ├── viewmodel/      # ViewModel classes
│   │   └── utils/          # Biometric authentication manager
│   ├── res/                # Layouts, strings, colors, themes
│   └── AndroidManifest.xml
```

## Building the App

1. Clone the repository:
   ```bash
   git clone https://github.com/sagarparwani32-sys/kids-app.git
   cd kids-app
   ```

2. Open in Android Studio or build from command line:
   ```bash
   ./gradlew build
   ```

3. Run on device/emulator:
   ```bash
   ./gradlew installDebug
   ```

## Requirements

- Android SDK 23 or higher
- Device with biometric authentication capability (fingerprint/face unlock)
- Android Studio Arctic Fox or newer (for development)

## License

This project is open-source and available under the [MIT License](LICENSE).