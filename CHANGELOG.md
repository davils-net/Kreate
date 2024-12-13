# CHANGELOG
## Version 0.0.2
### Features
#### Core
- **Gradle Sync**
  - Introduced a function to execute tasks during Gradle Sync, streamlining project configurations.

#### CInterop
- **Rust-to-Kotlin Interoperability**
  - Enabled applying native targets without requiring native bindings, enhancing flexibility in cross-language development.

### Fixes
#### Documentation
- **Dokka Integration**
  - Resolved an issue where the Dokka plugin was being redundantly applied to the root project. It now only applies when not already present.

#### CInterop
- **Rust-to-Kotlin Interoperability**
  - Fixed an issue where Cargo and build script overrides occurred on every build/compile.

#### Publishing
- **Maven Publish Plugin**
  - Corrected the timing issue where the Maven Publish plugin was applied too late during Gradle evaluation, ensuring proper functionality.

### Dependencies
- **Kotlin:** 2.1.0
- **Kotest:** 6.0.0.M1
- **Dokka:** 1.9.20
- **Idea Ext:** 1.1.9

## Version 0.0.1
### Features
#### Core
- **License Generator**  
  - Automatically generates and writes a configured license if none exists.
- **File Patcher**  
  - Updates specified files and patterns with new content based on provided regex.
- **Explicit API Mode Toggle**  
  - Enables or disables explicit API mode for Kotlin projects.

#### Publishing
- **GitLab Integration**  
  - Adds publishing configuration for the private GitLab instance of Davils.  
  - Includes configuration for project credits.

#### Build Constants
- **Gradle Properties Integration**  
  - Merges defined properties into build constants.  
  - Supports custom output paths within the build directory.

#### CInterop
- **Rust-to-Kotlin Interoperability**  
  - Automates generation of Rust projects compatible with Kotlin.  
  - Configures `cargo` and build scripts automatically.  
  - Compiles Rust code and generates Kotlin cinterop definition files.  
- **Git Exclusions**  
  - Automatically excludes unnecessary resources from version control.

#### Testing
- **Test Reports**  
  - Generates detailed test reports for Gradle projects when enabled.  
- **Kotest Multiplatform Support**  
  - Integrates Kotest for Kotlin Multiplatform projects.

#### Documentation
- **Dokka Integration**  
  - Applies the Dokka plugin for project documentation.  
- **Multi-Module Support**  
  - Provides enhanced support for multi-module projects.

### Dependencies
- **Kotlin**: 2.1.0  
- **Kotest**: 6.0.0.M1  
- **Dokka**: 1.9.20
