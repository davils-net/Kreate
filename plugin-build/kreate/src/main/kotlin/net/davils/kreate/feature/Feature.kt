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

/**
 * Central adapter for all features in kreate.
 *
 * @since 0.0.1
 * @author Nils Jäkel
 * */
internal interface KreateFeature {
    /**
     * The current gradle project.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    val project: Project

    /**
     * The kreate extension from the current gradle project.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    val extension: KreateExtension

    /**
     * Registers a feature to the plugin.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    fun register()
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
internal fun isFeatureEnabled(configuration: KreateFeatureConfiguration): Boolean = configuration.enabled.getOrElse(false)
