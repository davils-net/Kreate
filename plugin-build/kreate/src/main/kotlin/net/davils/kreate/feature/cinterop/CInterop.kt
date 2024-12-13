/*
 * All rights reserved
 * Copyright 2024 Davils
 *
 * Unauthorized copying, distribution, or modification of this work is strictly prohibited.
 */

package net.davils.kreate.feature.cinterop

import net.davils.kreate.feature.*
import net.davils.kreate.feature.execTaskBeforeCompile
import net.davils.kreate.feature.registerTask
import org.gradle.api.Project
import net.davils.kreate.isMultiplatform

/**
 * Applies the C Interop feature to the gradle project.
 *
 * @param project The current gradle project.
 * @param config The C Interop configuration.
 *
 * @since 0.0.2
 * @author Nils Jäkel
 * */
internal fun cinterop(project: Project, config: CInteropConfiguration) = project.feature(config) { _ ->
    if (!isMultiplatform(this)) return@feature

    val setupRust = registerTask<SetupRustProject>(
        "setupRustProject",
        "Generates the Rust project for the native implementation."
    )

    val gitExclude = registerTask<ExcludeSourcesInGit>(
        "excludeSourcesInGit",
        "Excludes the sources in the gitignore file."
    ) { dependsOn(setupRust) }

    val enableCInterop = registerTask<EnableCInteropSupport>(
        "enableCInteropSupport",
        "Enables the C Interop support in the gradle.properties."
    ) { dependsOn(gitExclude) }

    val compileRust = registerTask<CompileRust>(
        "compileRust",
        "Compiles the Rust project."
    ) { dependsOn(enableCInterop) }

    val generateDefinitionFiles = registerTask<GenerateDefinitionFile>(
        "generateDefinitionFiles",
        "Generates the definition file as bridge for the kotlin compiler."
    ) {
        dependsOn(compileRust)
    }
    execTaskBeforeCompile(generateDefinitionFiles.get())
    applyNativeTargets(project, config)
}
