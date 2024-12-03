/*
 * All rights reserved
 * Copyright 2024 Davils
 *
 * Unauthorized copying, distribution, or modification of this work is strictly prohibited.
 */

package net.davils.kreate.feature.core

import net.davils.kreate.KreateExtension
import net.davils.kreate.build.BuildConstants
import net.davils.kreate.utils.KreateFeature
import net.davils.kreate.utils.isFeatureEnabled
import net.davils.kreate.utils.isMultiplatform
import net.davils.kreate.utils.projectVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.register
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

/**
 * Represents the core feature.
 *
 * @since 0.0.1
 * @author Nils Jäkel
 * */
public class Core(override val project: Project, override val extension: KreateExtension) : KreateFeature {
    override fun apply() {
        project.version = projectVersion

        project.afterEvaluate {
            if (!isFeatureEnabled(extension.core)) return@afterEvaluate

            if (!isMultiplatform(project)) {
                project.extensions.configure<KotlinJvmProjectExtension>("kotlin") {
                    explicitApi()
                }
            } else {
                project.extensions.configure<KotlinMultiplatformExtension>("kotlin") {
                    explicitApi()
                }
            }

            tasks.register<GenerateLicense>("generateLicense") {
                group = BuildConstants.ORGANIZATION_NAME.lowercase()
                description = "Generates the license for the current project."
            }

            tasks.register<VersionPatch>("patchVersion") {
                group = BuildConstants.ORGANIZATION_NAME.lowercase()
                description = "Patches all given files with the version of this the project"
            }
        }
    }
}
