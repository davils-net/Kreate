package net.davils.kreate.buildconstants

import net.davils.kreate.KreateExtension
import net.davils.kreate.KreateFeature
import net.davils.kreate.build.BuildConstants
import org.gradle.api.Project
import org.gradle.kotlin.dsl.register

public object BuildConstants : KreateFeature {
    override fun apply(project: Project, extension: KreateExtension): Unit = project.afterEvaluate {
        val isBuildConstantsEnabled = extension.buildConstants.enabled.orElse(false).get()
        if (!isBuildConstantsEnabled) {
            return@afterEvaluate
        }

        val task = project.tasks.register<GenerateBuildConstants>("generateBuildConstants") {
            group = BuildConstants.ORGANIZATION_NAME.lowercase()
            description = "Generates the build constants for the current project."
        }
        project.generateBeforeCompile(task.get())

        val sourceSets = extension.buildConstants.sourceSets.orNull ?: throw IllegalArgumentException("sourceSets not configured or not found")
        val path = extension.buildConstants.path(project)
        sourceSets.kotlin.srcDir(path)
    }
}
