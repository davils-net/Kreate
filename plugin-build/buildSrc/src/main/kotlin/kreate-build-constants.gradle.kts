import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    org.jetbrains.kotlin.jvm
}

val buildConstants = extensions.create("buildConstants", BuildConstants::class)

tasks {
    val generateBuildConstants = register("generateBuildConstants") {
        group = Project.NAME.lowercase()
        description = "Generates the build constants"
        doLast {
            generateBuildConstants(project, buildConstants)
        }
    }

    withType<JavaCompile> {
        options.encoding = "UTF-8"
        dependsOn(generateBuildConstants)
    }

    withType<KotlinCompile> {
        dependsOn(generateBuildConstants)
    }
}

kotlin {
    sourceSets {
        main {
            kotlin.srcDir(buildConstants.path(project))
        }
    }
}
