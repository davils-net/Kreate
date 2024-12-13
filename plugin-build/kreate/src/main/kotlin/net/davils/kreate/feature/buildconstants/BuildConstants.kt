/*
 * All rights reserved
 * Copyright 2024 Davils
 *
 * Unauthorized copying, distribution, or modification of this work is strictly prohibited.
 */

package net.davils.kreate.feature.buildconstants

import net.davils.kreate.feature.*
import net.davils.kreate.feature.execTaskBeforeCompile
import net.davils.kreate.feature.registerTask
import org.gradle.api.Project

/**
 * Applies the build constants feature to the gradle project.
 *
 * @param project The current gradle project.
 * @param config The build constants configuration.
 *
 * @since 0.0.2
 * @author Nils Jäkel
 * */
internal fun buildConstants(project: Project, config: BuildConstantsConfiguration) = project.feature(config) { _ ->
    val task = registerTask<GenerateBuildConstants>(
        name = "generateBuildConstants",
        description = "Generates the build constants for the current project."
    )
    execTaskBeforeCompile(task.get())

    val sourceSets = config.sourceSets.orNull ?: throw IllegalArgumentException("sourceSets not configured or not found")
    val path = config.path(this)
    sourceSets.kotlin.srcDir(path)
}
