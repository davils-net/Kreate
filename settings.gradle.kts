plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "Kreate"

include("example")
includeBuild("plugin-build")
