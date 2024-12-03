package net.davils.kreate.feature.cinterop

import net.davils.kreate.KreateExtension
import org.gradle.api.Project
import net.davils.kreate.utils.isFeatureEnabled
import net.davils.kreate.utils.KreateFeature
import org.gradle.kotlin.dsl.register
import net.davils.kreate.build.BuildConstants
import net.davils.kreate.utils.isMultiplatform

/**
 * Represents the cinterop feature.
 *
 * @since 0.0.1
 * @author Nils Jäkel
 * */
public class CInterop(override val project: Project, override val extension: KreateExtension) : KreateFeature {
    override fun apply(): Unit = project.afterEvaluate {
        if (!isFeatureEnabled(extension.cinterop) || !isMultiplatform(project)) return@afterEvaluate

        val generationTask = tasks.register<SetupRustProject>("setupRustProject") {
            group = BuildConstants.ORGANIZATION_NAME.lowercase()
            description = "Generates the Cinterop project for the current project."
        }

        val configureCargo = tasks.register<ConfigureCargo>("configureCargo") {
            group = BuildConstants.ORGANIZATION_NAME.lowercase()
            description = "Configures Cargo for the current project."
            dependsOn(generationTask)
        }

        val configureBuildScript = tasks.register<ConfigureBuildScript>("configureBuildScript") {
            group = BuildConstants.ORGANIZATION_NAME.lowercase()
            description = "Configures the build script for the current project."
            dependsOn(configureCargo)
        }

        val compileTask = tasks.register<CompileRust>("compileRust") {
            group = BuildConstants.ORGANIZATION_NAME.lowercase()
            description = "Compiles the Rust project"
            dependsOn(configureBuildScript)
        }

        tasks.register<GenerateDefFiles>("generateDefFiles") {
            group = BuildConstants.ORGANIZATION_NAME.lowercase()
            description = "Generates the def files."
            dependsOn(compileTask)
        }
    }
}
