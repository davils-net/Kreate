/*
 * All rights reserved
 * Copyright 2024 Davils
 *
 * Unauthorized copying, distribution, or modification of this work is strictly prohibited.
 */

package net.davils.kreate.feature

import net.davils.kreate.KreateExtension
import org.gradle.api.Project
import org.gradle.api.provider.Property
import org.gradle.kotlin.dsl.getByType

/**
 * Applies a feature to the gradle project.
 *
 * @param configuration The configuration for the feature.
 * @param func The function to execute.
 *
 * @since 0.0.1
 * @author Nils Jäkel
 * */
internal fun <T : KreateFeatureConfiguration> Project.feature(configuration: T, func: Project.(ext: KreateExtension) -> Unit) {
    val extension = extensions.getByType<KreateExtension>()

    afterEvaluate {
        val isCoreEnabled: Boolean = extension.core.enabled.get()
        if (!isCoreEnabled) return@afterEvaluate

        if (!isFeatureEnabled(configuration)) return@afterEvaluate
        func(extension)
    }
}

/**
 * Central adapter for all feature configurations in kreate.
 *
 * @since 0.0.1
 * @author Nils Jäkel
 * */
internal interface KreateFeatureConfiguration {
    /**
     * If the feature is enabled.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    val enabled: Property<Boolean>
}

/**
 * Checks if a kreate feature is enabled.
 *
 * @param configuration The [KreateFeatureConfiguration].
 * @return A [Boolean] if the feature is enabled.
 *
 * @since 0.0.1
 * @author Nils Jäkel
 * */
private fun isFeatureEnabled(configuration: KreateFeatureConfiguration): Boolean = configuration.enabled.getOrElse(false)
