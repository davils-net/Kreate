plugins {
    `kotlin-dsl`
    `kreate-publishing`
    `kreate-build-constants`
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(gradleApi())
    implementation(libs.kotlin.gradle.api)
}

gradlePlugin {
    website = Project.ORGANISATION_URL
    plugins {
        register(Project.NAME.lowercase()) {
            id = Project.GROUP
            displayName = Project.NAME
            description = Project.DESCRIPTION
            implementationClass = "${Project.GROUP}.${Project.NAME}"
        }
    }
}

val targetJavaVersion = JavaVersion.VERSION_1_8
java {
    sourceCompatibility = targetJavaVersion
    targetCompatibility = targetJavaVersion
    withJavadocJar()
    withSourcesJar()
}

kotlin {
    explicitApi()
    jvmToolchain(targetJavaVersion.majorVersion.toInt())
}

buildConstants {
    properties = mapOf(
        "ORGANIZATION_NAME" to Project.ORGANIZATION_NAME,
        "ORGANISATION_URL" to Project.ORGANISATION_URL
    )
}
