package net.davils.kreate.feature.cinterop

import net.davils.kreate.KreateExtension
import org.gradle.api.Project
import net.davils.kreate.utils.isFeatureEnabled
import net.davils.kreate.utils.KreateFeature
import org.gradle.kotlin.dsl.register
import net.davils.kreate.build.BuildConstants
import org.gradle.kotlin.dsl.getByType
import net.davils.kreate.utils.isMultiplatform
import java.io.File

public class Cinterop(override val project: Project, override val extension: KreateExtension) : KreateFeature {
    override fun apply(): Unit = project.afterEvaluate {
        if (!isFeatureEnabled(extension.cinterop) || !isMultiplatform(project)) return@afterEvaluate

        val generationTask = project.tasks.register<SetupRustProject>("setupRustProject") {
            group = BuildConstants.ORGANIZATION_NAME.lowercase()
            description = "Generates the Cinterop project for the current project."
        }

        val compileTask = project.tasks.register<CompileRust>("compileRust") {
            group = BuildConstants.ORGANIZATION_NAME.lowercase()
            description = "Compiles the Rust project"
            dependsOn(generationTask)
        }

        project.tasks.register<GenerateDefFiles>("generateDefFiles") {
            group = BuildConstants.ORGANIZATION_NAME.lowercase()
            description = "Generates the def files."
            dependsOn(compileTask)
        }
    }
}

internal fun rustProject(project: Project): Pair<String, File> {
    val extension = project.extensions.getByType<KreateExtension>()
    val name = extension.core.name.getOrElse(project.rootProject.name)
    val file = project.rootProject.projectDir.absoluteFile

    return Pair("$name-rust".lowercase(), file)
}
