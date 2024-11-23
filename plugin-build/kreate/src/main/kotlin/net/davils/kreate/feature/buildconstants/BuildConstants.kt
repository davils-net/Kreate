package net.davils.kreate.feature.buildconstants

import net.davils.kreate.KreateExtension
import net.davils.kreate.utils.isFeatureEnabled
import net.davils.kreate.build.BuildConstants
import net.davils.kreate.utils.KreateFeature
import org.gradle.api.Project
import org.gradle.kotlin.dsl.register

public class BuildConstants(override val project: Project, override val extension: KreateExtension) : KreateFeature {
    override fun apply(): Unit = project.afterEvaluate {
        if (!isFeatureEnabled(extension.buildConstants)) return@afterEvaluate

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
