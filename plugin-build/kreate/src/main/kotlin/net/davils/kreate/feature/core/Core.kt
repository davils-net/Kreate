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
        val gradleProperties = registerTask<ConfigureGradleProperties>(
            "configureGradleProperties",
            "Configures the gradle properties."
        )
        execTasksBeforeCompile(license.get(), filePatch.get(), gradleProperties.get())

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
