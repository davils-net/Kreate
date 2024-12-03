/*
 * All rights reserved
 * Copyright 2024 Davils
 *
 * Unauthorized copying, distribution, or modification of this work is strictly prohibited.
 */

import org.gradle.api.provider.MapProperty
import org.gradle.api.Project as GradleProject

/**
 * The [BuildConstants] interface defines the constants that are used to generate the [BuildConstants] file.
 *
 * @property properties The properties that are used to generate the [BuildConstants] file.
 *
 * @see [generateBuildConstants]
 * @since 0.0.1
 */
interface BuildConstants {
    val properties: MapProperty<String, String>
}

/**
 * Generates the build constants based on the properties provided in the [BuildConstants].
 *
 * The generated constants are written to a file named `BuildConstants.kt` in the directory provided by the
 * [BuildConstants.path] property.
 *
 * @param project The project in which the build constants should be generated.
 * @param buildConstants The build constants configuration that provides the properties to generate the build
 * constants.
 *
 * @see BuildConstants
 * @since 0.0.1
 */
internal fun generateBuildConstants(project: GradleProject, buildConstants: BuildConstants) {
    val content = buildConstants.properties.get().entries.joinToString("\n") {
        "    const val ${it.key} = \"${it.value}\""
    }
    val generatedDir = buildConstants.path(project)
    generatedDir.mkdirs()

    val buildConstantsFile = generatedDir.resolve(relative = "BuildConstants.kt")

    if (!buildConstantsFile.exists()) {
        buildConstantsFile.createNewFile()
    }

    buildConstantsFile.outputStream().use { outputStream ->
        outputStream.write(
            """
// This file is generated automatically. Do not edit or modify!
package ${Project.GROUP}.build

internal object BuildConstants {
$content
}
            """.trimIndent().toByteArray()
        )
    }
}

/**
 * Sets the path to the generated build constants file.
 * @param project The project in which the build constants should be generated.
 *
 * @see BuildConstants
 * @since 0.0.1
 */
internal fun BuildConstants.path(project: GradleProject) = project.layout.buildDirectory
    .dir("generated/templates")
    .get()
    .asFile