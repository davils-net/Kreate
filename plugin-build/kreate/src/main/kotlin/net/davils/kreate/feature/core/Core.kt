/*
 * All rights reserved
 * Copyright 2024 Davils
 *
 * Unauthorized copying, distribution, or modification of this work is strictly prohibited.
 */

package net.davils.kreate.feature.core

import net.davils.kreate.feature.*
import net.davils.kreate.feature.execTasksBeforeCompile
import net.davils.kreate.feature.registerTask
import net.davils.kreate.isMultiplatform
import net.davils.kreate.projectVersion
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

/**
 * Applies the core feature to the gradle project.
 *
 * @param project The current gradle project.
 * @param config The core configuration.
 *
 * @since 0.0.1
 * @author Nils Jäkel
 * */
internal fun core(project: Project, config: CoreConfiguration) {
    project.version = projectVersion

    project.feature(config) { _ ->
        val isExplicitApiMode = config.isExplicitApiMode.get()
        val license = registerTask<GenerateLicense>(
            "generateLicense",
            "Generates the license for the current project."
        )
        val filePatch = registerTask<FilePatch>(
            "filePatch",
            "Patches all given files with the project version."
        )
        execTasksBeforeCompile(license.get(), filePatch.get())

        if (!isMultiplatform(project)) {
            project.extensions.configure<KotlinJvmProjectExtension>("kotlin") {
                if (isExplicitApiMode) {
                    explicitApi()
                }
            }
        } else {
            project.extensions.configure<KotlinMultiplatformExtension>("kotlin") {
                if (isExplicitApiMode) {
                    explicitApi()
                }
                jvm()
            }
        }
    }
}
