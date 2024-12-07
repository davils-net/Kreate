/*
 * All rights reserved
 * Copyright 2024 Davils
 *
 * Unauthorized copying, distribution, or modification of this work is strictly prohibited.
 */

package net.davils.kreate.feature

import net.davils.kreate.KreateExtension
import net.davils.kreate.build.BuildConstants
import net.davils.kreate.kreateExtension
import org.gradle.api.DefaultTask
import org.gradle.api.Project
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskProvider
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

/**
 * Central adapter for all tasks in kreate.
 *
 * @since 0.0.1
 * @author Nils Jäkel
 * */
public abstract class Task : DefaultTask() {
    /**
     * The kreate extension from the current gradle project.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    @get:Input
    internal val extension: KreateExtension = project.kreateExtension

    /**
     * The task, that should be executed if the task is triggered.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    public abstract fun execute()
}

/**
 * Executes a task before any compilation.
 *
 * @param task The task, that should be executed before compilation.
 *
 * @since 0.0.1
 * @author Nils Jäkel
 * */
internal fun Project.execTaskBeforeCompile(task: Task) {
    tasks.withType<KotlinCompilationTask<*>> {
        dependsOn(task)
    }

    tasks.withType<JavaCompile> {
        dependsOn(task)
    }
}

/**
 * Registers a task in the current gradle project under the group [BuildConstants.ORGANIZATION_NAME].
 *
 * @param name The name of the task.
 * @param description The description of the task.
 * @param inject Additional configuration for the task.
 * @param T The type of the task.
 *
 * @since 0.0.1
 * @author Nils Jäkel
 *
 * @see Task
 * */
internal inline fun <reified T : Task> Project.registerTask(
    name: String,
    description: String,
    crossinline inject: T.() -> Unit = {},
): TaskProvider<T?> {
    return tasks.register(name, T::class.java) {
        this.group = BuildConstants.ORGANIZATION_NAME.lowercase()
        this.description = description
        inject(this)
    }
}
