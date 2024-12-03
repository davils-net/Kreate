package net.davils.kreate.utils

import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

/**
 * Generates the build constants before the compilation.
 *
 * @since 0.0.1
 * @author Nils Jäkel
 * */
internal fun Project.generateBeforeCompile(task: Task) {
    tasks.withType<KotlinCompilationTask<*>> {
        dependsOn(task)
    }

    tasks.withType<JavaCompile> {
        dependsOn(task)
    }
}