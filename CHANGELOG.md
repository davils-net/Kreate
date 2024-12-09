# CHANGELOG
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
  - Adds publishing configuration for private GitLab instances.  
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
