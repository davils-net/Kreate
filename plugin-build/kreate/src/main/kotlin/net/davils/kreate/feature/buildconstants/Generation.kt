/*
 * All rights reserved
 * Copyright 2024 Davils
 *
 * Unauthorized copying, distribution, or modification of this work is strictly prohibited.
 */

package net.davils.kreate.feature.buildconstants

import net.davils.kreate.build.BuildConstants
import net.davils.kreate.feature.Task
import net.davils.kreate.feature.isFeatureEnabled
import org.gradle.api.tasks.TaskAction

/**
 * Task to generate the build constants.
 *
 * @since 0.0.1
 * @author Nils Jäkel
 * */
public abstract class GenerateBuildConstants : Task() {
    /**
     * The name of the current gradle project.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    private val projectName = extension.core.name.get()

    /**
     * The task action.
     * It generates the build constants and writes them to a file
     * to get access to their values at runtime.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    @TaskAction
    override fun execute() {
        val properties = extension.buildConstants.properties.get()

        val content = properties.entries.joinToString("\n") { "const val ${it.key} = \"${it.value}\"" }
        val generatedDir = extension.buildConstants.path(project)
        generatedDir.mkdirs()

        val buildConstantsFile = generatedDir.resolve(relative = "BuildConstants.kt")
        if (!buildConstantsFile.exists()) {
            buildConstantsFile.createNewFile()
        }

        val isInternal = extension.buildConstants.onlyInternal.get()
        buildConstantsFile.writeText(
            """
             package ${BuildConstants.GROUP}.${projectName.lowercase()}.build
              
             ${if (isInternal) "internal" else "public"} object BuildConstants {
                $content
             }
            """.trimIndent()
        )
    }
}
