plugins {
    `kotlin-dsl`
    `kreate-publishing`
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(gradleApi())
}

gradlePlugin {
    website = Project.ORGANISATION_URL
    plugins {
        register(Project.NAME.lowercase()) {
            id = "${Project.GROUP}.${Project.NAME.lowercase()}"
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
    jvmToolchain(targetJavaVersion.majorVersion.toInt())
}
