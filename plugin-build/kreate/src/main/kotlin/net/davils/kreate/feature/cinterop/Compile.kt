/*
 * All rights reserved
 * Copyright 2024 Davils
 *
 * Unauthorized copying, distribution, or modification of this work is strictly prohibited.
 */

package net.davils.kreate.feature.cinterop

import net.davils.kreate.feature.Task
import net.davils.kreate.Paths
import net.davils.kreate.feature.isFeatureEnabled
import net.davils.kreate.isMultiplatform
import org.gradle.api.tasks.TaskAction

/**
 * Represents the task to compile the rust project.
 *
 * @since 0.0.1
 * @author Nils Jäkel
 * */
public abstract class CompileRust : Task() {
    /**
     * The path handler.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    private val paths = Paths(project)

    /**
     * The task action.
     * It compiles the rust project.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    @TaskAction
    override fun execute() {
        if (!isFeatureEnabled(extension.cinterop) || !isMultiplatform(project)) return

        val builder = ProcessBuilder("cargo", "build", "--release")
        builder.directory(paths.rustDir)

        val process = builder.start()
        process.waitFor()
    }
}
