/*
 * All rights reserved
 * Copyright 2024 Davils
 *
 * Unauthorized copying, distribution, or modification of this work is strictly prohibited.
 */

package net.davils.kreate.feature.cinterop

import net.davils.kreate.feature.Task
import net.davils.kreate.utils.os
import net.davils.kreate.utils.OsType
import org.gradle.api.tasks.TaskAction

/**
 * Task to generate the def file.
 *
 * @since 0.0.1
 * @author Nils Jäkel
 * */
public abstract class GenerateDefinitionFiles : Task() {
    /**
     * The rust project.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    private val rustProject = rustProject(project, extension)

    /**
     * The include directory for the h-file.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    private val includeDir = rustProject.file.resolve(rustProject.name).resolve("include")

    /**
     * The native cinterop directory for the def file.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    private val nativeCInteropDir = project.rootProject.file("cinterop")

    @TaskAction
    override fun execute() {
        val hFile = includeDir.list()?.joinToString(" ") ?: throw IllegalStateException("No header file found in $includeDir")

        nativeCInteropDir.mkdirs()
        val defFile = nativeCInteropDir.resolve("cinterop.def")
        if (!defFile.exists()) {
            defFile.createNewFile()
        }

        when (os) {
            OsType.WINDOWS -> {
                defFile.writeText(
                    """
                    headers = $hFile
                    staticLibraries = ${rustProject.name.removeSuffix("-rust")}.lib
                    compilerOpts = -I${includeDir.absolutePath.replace("\\", "/")}
                    libraryPaths = ${rustProject.file.resolve("target/release").absolutePath.replace("\\", "/")}
                """.trimIndent()
                )
            }

            else -> {
                defFile.writeText(
                    """
                    headers = $hFile
                    staticLibraries = lib${rustProject.name.removeSuffix("-rust")}.a
                    compilerOpts = -I$includeDir
                    libraryPaths = ${rustProject.file.resolve("target/release")}
                """.trimIndent()
                )
            }
        }
    }
}
