/*
 * All rights reserved
 * Copyright 2024 Davils
 *
 * Unauthorized copying, distribution, or modification of this work is strictly prohibited.
 */

package net.davils.kreate.feature.cinterop

import net.davils.kreate.KreateExtension
import net.davils.kreate.feature.execTaskBeforeCompile
import org.gradle.api.Project
import net.davils.kreate.feature.isFeatureEnabled
import net.davils.kreate.feature.KreateFeature
import net.davils.kreate.feature.registerTask
import net.davils.kreate.isMultiplatform

/**
 * Represents the cinterop feature.
 *
 * @since 0.0.1
 * @author Nils Jäkel
 * */
public class CInterop(override val project: Project, override val extension: KreateExtension) : KreateFeature {
    override fun register(): Unit = project.afterEvaluate {
        if (!isFeatureEnabled(extension.cinterop) || !isMultiplatform(project)) return@afterEvaluate

        val generationTask = registerTask<SetupRustProject>(
            "setupRustProject",
            "Generates the Rust project for the native implementation."
        )

        val configureCargo = registerTask<ConfigureCargo>(
            "configureCargo",
            "Configures the Cargo for the rust project."
        ) { dependsOn(generationTask) }

        val configureBuildScript = registerTask<ConfigureBuildScript>(
            "configureBuildScript",
            "Configures the build script for the rust project."
        ) { dependsOn(configureCargo) }

        val gitExclude = registerTask<ExcludeSourcesInGit>(
            "excludeSourcesInGit",
            "Excludes the sources in the gitignore file."
        ) { dependsOn(configureBuildScript) }

        val compileRust = registerTask<CompileRust>(
            "compileRust",
            "Compiles the Rust project."
        ) { dependsOn(gitExclude) }

        val generateDefinitionFiles = registerTask<GenerateDefinitionFile>(
            "generateDefinitionFiles",
            "Generates the definition file as bridge for the kotlin compiler."
        ) { dependsOn(compileRust) }

        execTaskBeforeCompile(generateDefinitionFiles.get())
        applyNativeTargets(project, extension)
    }
}
