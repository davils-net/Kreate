package net.davils.kreate.feature.buildconstants

import net.davils.kreate.KreateExtension
import net.davils.kreate.utils.isFeatureEnabled
import net.davils.kreate.utils.KreateFeature
import net.davils.kreate.utils.generateBeforeCompile
import net.davils.kreate.build.BuildConstants
import org.gradle.api.Project
import org.gradle.kotlin.dsl.register

/**
 * Represents the build constants feature.
 *
 * @since 0.0.1
 * @author Nils Jäkel
 * */
public class BuildConstants(override val project: Project, override val extension: KreateExtension) : KreateFeature {
    override fun apply(): Unit = project.afterEvaluate {
        if (!isFeatureEnabled(extension.buildConstants)) return@afterEvaluate

        val task = tasks.register<GenerateBuildConstants>("generateBuildConstants") {
            group = BuildConstants.ORGANIZATION_NAME.lowercase()
            description = "Generates the build constants for the current project."
        }
        generateBeforeCompile(task.get())

        val sourceSets = extension.buildConstants.sourceSets.orNull ?: throw IllegalArgumentException("sourceSets not configured or not found")
        val path = extension.buildConstants.path(this)
        sourceSets.kotlin.srcDir(path)
    }
}
