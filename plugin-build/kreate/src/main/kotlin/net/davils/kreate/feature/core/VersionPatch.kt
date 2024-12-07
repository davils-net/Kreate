/*
 * All rights reserved
 * Copyright 2024 Davils
 *
 * Unauthorized copying, distribution, or modification of this work is strictly prohibited.
 */

package net.davils.kreate.feature.core

import net.davils.kreate.feature.Task
import net.davils.kreate.projectVersion
import org.gradle.api.tasks.TaskAction
import java.io.File

/**
 * Task to patch versions in files, that are not controlled by gradle.
 *
 * @since 0.0.1
 * @author Nils Jäkel
 * */
public abstract class VersionPatch : Task() {
    /**
     * The files, that should be patched.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    private val files = extension.core.versionPatchFiles.get()

    /**
     * The task action.
     * It replaces the version in the given files with the configured [Regex].
     *
     * @since 0.0.1
     * @author Nils Jäkel
     *
     * @see Regex
     * */
    @TaskAction
    override fun execute() {
        files.forEach { entry ->
            val file = entry.file
            if (!file.exists()) throw IllegalStateException("File $file does not exist")

            val content = file.readText()
            file.writeText(content.replace(entry.regex, projectVersion))
        }
    }
}

/**
 * Represents an entry in the [VersionPatch] task.
 * It contains information about the file, that should be patched and the regex,
 * that should be replaced with the project version.
 *
 * @since 0.0.1
 * @author Nils Jäkel
 * */
public data class Entry(
    /**
     * The file, that should be patched.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    public val file: File,
    /**
     * The regex, that should be replaced.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    public val regex: Regex,
)
