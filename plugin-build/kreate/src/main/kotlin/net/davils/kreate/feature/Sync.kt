/*
 * All rights reserved
 * Copyright 2024 Davils
 *
 * Unauthorized copying, distribution, or modification of this work is strictly prohibited.
 */

package net.davils.kreate.feature

import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.plugins.ide.idea.model.IdeaModel
import org.jetbrains.gradle.ext.IdeaExtPlugin
import org.jetbrains.gradle.ext.ProjectSettings
import org.jetbrains.gradle.ext.TaskTriggersConfig

/**
 * Executes a task on gradle sync.
 *
 * @param task The task, that should be executed on gradle sync.
 *
 * @since 0.0.2
 * @author Nils Jäkel
 * */
internal fun Project.execTaskOnSync(task: Task) {
    if (!project.rootProject.plugins.hasPlugin("org.jetbrains.gradle.plugin.idea-ext")) {
        project.rootProject.plugins.apply(IdeaExtPlugin::class)
    }

    if (!project.plugins.hasPlugin("org.jetbrains.gradle.plugin.idea-ext")) {
        project.plugins.apply(IdeaExtPlugin::class)
    }

    rootProject.extensions.configure<IdeaModel>("idea") {
        project {
            this as ExtensionAware
            configure<ProjectSettings> {
                this as ExtensionAware
                configure<TaskTriggersConfig> {
                    afterSync(task)
                }
            }
        }
    }
}

/**
 * Executes multiple tasks on gradle sync.
 *
 * @param syncTasks The tasks, that should be executed on gradle sync.
 *
 * @since 0.0.2
 * @author Nils Jäkel
 * */
internal fun Project.execTasksOnSync(vararg syncTasks: Task) {
    syncTasks.forEach { task ->
        execTaskOnSync(task)
    }
}