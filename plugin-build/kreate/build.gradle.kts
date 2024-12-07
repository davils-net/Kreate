/*
 * All rights reserved
 * Copyright 2024 Davils
 *
 * Unauthorized copying, distribution, or modification of this work is strictly prohibited.
 */

plugins {
    `kotlin-dsl`
    `kreate-publishing`
    `kreate-build-constants`
}

repositories {
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation(gradleApi())
    implementation(libs.bundles.kreate.dependencies)
}

gradlePlugin {
    website = Project.ORGANISATION_URL
    plugins {
        register(Project.NAME.lowercase()) {
            id = "${Project.GROUP}.kreate"
            displayName = Project.NAME
            description = Project.DESCRIPTION
            implementationClass = "${Project.GROUP}.kreate.${Project.NAME}"
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
        "ORGANISATION_URL" to Project.ORGANISATION_URL,
        "ORGANISATION_EMAIL" to Project.ORGANIZATION_EMAIL,
        "GROUP" to Project.GROUP
    )
}
