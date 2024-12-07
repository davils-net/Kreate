/*
 * All rights reserved
 * Copyright 2024 Davils
 *
 * Unauthorized copying, distribution, or modification of this work is strictly prohibited.
 */

package net.davils.kreate.feature.buildconstants

import net.davils.kreate.KreateExtension
import net.davils.kreate.feature.isFeatureEnabled
import net.davils.kreate.feature.KreateFeature
import net.davils.kreate.feature.execTaskBeforeCompile
import net.davils.kreate.feature.registerTask
import org.gradle.api.Project

/**
 * Represents the build constants feature.
 *
 * @since 0.0.1
 * @author Nils Jäkel
 * */
public class BuildConstants(override val project: Project, override val extension: KreateExtension) : KreateFeature {
    override fun register(): Unit = project.afterEvaluate {
        if (!isFeatureEnabled(extension.buildConstants)) return@afterEvaluate

        val task = registerTask<GenerateBuildConstants>(
            name = "generateBuildConstants",
            description = "Generates the build constants for the current project."
        )
        execTaskBeforeCompile(task.get())

        val sourceSets = extension.buildConstants.sourceSets.orNull ?: throw IllegalArgumentException("sourceSets not configured or not found")
        val path = extension.buildConstants.path(this)
        sourceSets.kotlin.srcDir(path)
    }
}
