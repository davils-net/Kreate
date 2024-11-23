package net.davils.kreate.buildconstants

import net.davils.kreate.KreateExtension
import net.davils.kreate.build.BuildConstants
import org.gradle.api.DefaultTask
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.provider.MapProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

public abstract class GenerateBuildConstants : DefaultTask() {
    @get:Input
    public abstract val onlyInternal: Property<Boolean>

    @get:Input
    public abstract val properties: MapProperty<String, String>

    @TaskAction
    internal fun generate() {
        val content = properties.get().entries.joinToString("\n") {
            "    const val ${it.key} = \"${it.value}\""
        }
        val extension = project.extensions.getByType<KreateExtension>()
        val generatedDir = extension.buildConstants.path(project)
        generatedDir.mkdirs()

        val buildConstantsFile = generatedDir.resolve(relative = "BuildConstants.kt")
        if (!buildConstantsFile.exists()) {
            buildConstantsFile.createNewFile()
        }

        val baseGroup = BuildConstants.GROUP
        val isInternal = onlyInternal.orElse(false).get()
        val projectName = extension.core.name.orElse(project.name).get()

        buildConstantsFile.writeText("""
             package $baseGroup.${projectName.lowercase()}.build
              
             ${if (isInternal) "internal" else "public"} object BuildConstants {
             $content
             }
        """.trimIndent())
    }
}

internal fun Project.generateBeforeCompile(task: Task) {
    tasks.withType<KotlinCompilationTask<*>> {
        dependsOn(task)
    }

    project.tasks.withType<JavaCompile> {
        dependsOn(task)
    }
}