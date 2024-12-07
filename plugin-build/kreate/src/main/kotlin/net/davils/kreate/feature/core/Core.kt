/*
 * All rights reserved
 * Copyright 2024 Davils
 *
 * Unauthorized copying, distribution, or modification of this work is strictly prohibited.
 */

package net.davils.kreate.feature.core

import net.davils.kreate.KreateExtension
import net.davils.kreate.feature.registerTask
import net.davils.kreate.feature.KreateFeature
import net.davils.kreate.feature.isFeatureEnabled
import net.davils.kreate.isMultiplatform
import net.davils.kreate.projectVersion
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

/**
 * Represents the core feature.
 *
 * @since 0.0.1
 * @author Nils Jäkel
 * */
public class Core(override val project: Project, override val extension: KreateExtension) : KreateFeature {
    private val isExplicitApiMode = extension.core.isExplicitApiMode.get()

    override fun register() {
        project.version = projectVersion

        project.afterEvaluate {
            if (!isFeatureEnabled(extension.core)) return@afterEvaluate

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

            registerTask<GenerateLicense>("generateLicense", "Generates the license for the current project.")
            registerTask<VersionPatch>("patchVersions", "Patches all given files with the project version.")
        }
    }
}
