/*
 * All rights reserved
 * Copyright 2024 Davils
 *
 * Unauthorized copying, distribution, or modification of this work is strictly prohibited.
 */

package net.davils.kreate.feature.cinterop

import net.davils.kreate.feature.Task
import net.davils.kreate.os
import net.davils.kreate.OsType
import net.davils.kreate.Paths
import org.gradle.api.tasks.TaskAction

/**
 * Represents the task to generate the cinterop definition file.
 *
 * @since 0.0.1
 * @author Nils Jäkel
 * */
public abstract class GenerateDefinitionFile : Task() {
    /**
     * The path handler.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    private val paths = Paths(project)

    /**
     * The task action.
     * It generates the cinterop definition file.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    @TaskAction
    override fun execute() {
        val hFile = paths.includeDir.list()?.joinToString(" ") ?: throw IllegalStateException("No header file found in ${paths.includeDir}")
        paths.cinteropDir.mkdirs()

        if (!paths.cinteropFile.exists()) {
            paths.cinteropFile.createNewFile()
        }

        when (os) {
            OsType.WINDOWS -> {
                paths.cinteropFile.writeText(
                    """
                    headers = $hFile
                    staticLibraries = ${paths.rustDir.name.removeSuffix("-rust")}.lib
                    compilerOpts = -I${paths.includeDir.absolutePath.replace("\\", "/")}
                    libraryPaths = ${paths.rustRelease.absolutePath.replace("\\", "/")}
                    """.trimIndent()
                )
            }

            else -> {
                paths.cinteropFile.writeText(
                    """
                    headers = $hFile
                    staticLibraries = lib${paths.rustDir.name.removeSuffix("-rust")}.a
                    compilerOpts = -I${paths.includeDir}
                    libraryPaths = ${paths.rustRelease.absolutePath}
                """.trimIndent()
                )
            }
        }
    }
}
