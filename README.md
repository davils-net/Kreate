# Kreate
A Gradle plugin to simplify the creation of Davils projects.

![Kotlin Badge](https://img.shields.io/badge/kotlin-davils?style=flat&logo=kotlin&labelColor=white&color=purple&link=https%3A%2F%2Fkotlinlang.org%2F)  
![License Badge](https://img.shields.io/badge/ARR-davils?style=flat&logoColor=black&label=license&labelColor=white&color=purple&link=https%3A%2F%2Fkotlinlang.org%2F)  
![Pipeline Status](https://gitlab.davils.net/davils/tools/kreate/badges/main/pipeline.svg)

## Getting Started
To use Kreate, add the following repository to your `build.gradle.kts` file:

```kotlin
repositories {
    maven {
        name = "Gitlab"
        url = uri("https://gitlab.davils.net/api/v4/projects/1/packages/maven")
    }
}
```

Then, apply the Kreate plugin by including the following in your `build.gradle.kts` file:

```kotlin
plugins {
    id("net.davils.kreate") version "<VERSION>"
}
```

Replace `<VERSION>` with the desired plugin version.

## Changelog
For updates, new features, and bug fixes, please refer to the [CHANGELOG](CHANGELOG.md).

## License
Kreate is licensed under the All Rights Reserved License. For more details, see the [LICENSE](LICENSE).
