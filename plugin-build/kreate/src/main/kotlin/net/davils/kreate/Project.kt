/*
 * All rights reserved
 * Copyright 2024 Davils
 *
 * Unauthorized copying, distribution, or modification of this work is strictly prohibited.
 */

package net.davils.kreate

import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.plugin.KotlinMultiplatformPluginWrapper
import org.gradle.kotlin.dsl.getByType

/**
 * Gets the current project version as [Lazy] property.
 *
 * The default version is `0.0.0`, but uses the `CI_COMMIT_SHORT_SHA` or `CI_COMMIT_TAG` environment variables
 * from the Gitlab CI pipeline to set the version in production.
 *
 * @since 0.0.1
 * @author Nils Jäkel
 *
 * @see Lazy
 * */
public val projectVersion: String by lazy {
    System.getenv("CI_COMMIT_TAG") ?: System.getenv("CI_COMMIT_SHORT_SHA")?.let { "$it-dev" } ?: "0.0.0"
}

/**
 * Detects if the current project is configured as a multiplatform project.
 *
 * @param project The current gradle project.
 * @return A [Boolean] that indicates if the project is a multiplatform project.
 *
 * @since 0.0.1
 * @author Nils Jäkel
 * */
internal fun isMultiplatform(project: Project): Boolean = project.plugins.any { it is KotlinMultiplatformPluginWrapper }

/**
 * Gets the kreate extension from the current gradle project.
 *
 * @since 0.0.1
 * @author Nils Jäkel
 * */
internal val Project.kreateExtension: KreateExtension
    get() = extensions.getByType<KreateExtension>()

