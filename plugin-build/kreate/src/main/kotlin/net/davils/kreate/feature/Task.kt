/*
 * All rights reserved
 * Copyright 2024 Davils
 *
 * Unauthorized copying, distribution, or modification of this work is strictly prohibited.
 */

package net.davils.kreate.feature

import net.davils.kreate.KreateExtension
import org.gradle.api.DefaultTask
import org.gradle.api.Project
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

/**
 * Represents a kreate task.
 *
 * @since 0.0.1
 * @author Nils Jäkel
 * */
public abstract class Task : DefaultTask() {
    /**
     * The kreate extension.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    @get:Input
    internal val extension: KreateExtension = project.extensions.getByType<KreateExtension>()

    /**
     * The task to execute.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    public abstract fun execute()
}

internal fun Project.execTaskBeforeCompile(task: org.gradle.api.Task) {
    tasks.withType<KotlinCompilationTask<*>> {
        dependsOn(task)
    }

    tasks.withType<JavaCompile> {
        dependsOn(task)
    }
}

internal fun registerTask(task: Task, name: String, description: String) {

}