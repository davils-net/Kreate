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
 * Represents the Task to exclude some compiler artifacts from git.
 *
 * @since 0.0.1
 * @author Nils Jäkel
 * */
public abstract class ExcludeSourcesInGit : Task() {
    /**
     * The path handler.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    private val paths = Paths(project)

    /**
     * The task action. It excludes some compiler artifacts from git.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    @TaskAction
    override fun execute() {
        if (!paths.gitignore.exists()) {
            paths.gitignore.createNewFile()
        }

        val gitignore = paths.gitignore.readText()
        if (!gitignore.contains("### Rust ###")) {
            paths.gitignore.appendText("\n\n### Rust ###")
        }
        if (!gitignore.contains("target")) {
            paths.gitignore.appendText("\ntarget")
        }
        if (!gitignore.contains("cinterop")) {
            paths.gitignore.appendText("\ncinterop")
        }
    }
}