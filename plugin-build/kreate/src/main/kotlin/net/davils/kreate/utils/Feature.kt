/*
 * All rights reserved
 * Copyright 2024 Davils
 *
 * Unauthorized copying, distribution, or modification of this work is strictly prohibited.
 */

package net.davils.kreate.utils

import net.davils.kreate.KreateExtension
import org.gradle.api.Project
import org.gradle.api.provider.Property

/**
 * Represents a kreate feature.
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
     * The created kreate extension.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    val extension: KreateExtension

    /**
     * Applies the feature to the plugin.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    fun apply()
}

/**
 * Represents a kreate feature configuration.
 *
 * @since 0.0.1
 * @author Nils Jäkel
 * */
internal interface KreateFeatureConfiguration {
    /**
     * Indicates if the feature is enabled.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    val enabled: Property<Boolean>
}

/**
 * Checks if a kreate feature is enabled.
 *
 * @param configuration The feature configuration
 * @return A [Boolean] that indicates if the feature is enabled
 *
 * @since 0.0.1
 * @author Nils Jäkel
 * */
internal fun isFeatureEnabled(configuration: KreateFeatureConfiguration): Boolean = configuration.enabled.getOrElse(false)