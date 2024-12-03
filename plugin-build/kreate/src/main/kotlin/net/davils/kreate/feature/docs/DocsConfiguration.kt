/*
 * All rights reserved
 * Copyright 2024 Davils
 *
 * Unauthorized copying, distribution, or modification of this work is strictly prohibited.
 */

package net.davils.kreate.feature.docs

import net.davils.kreate.utils.KreateFeatureConfiguration
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.Property
import javax.inject.Inject

/**
 * Creates the configuration for the docs feature.
 *
 * @since 0.0.1
 * @author Nils Jäkel
 * */
internal interface DocsConfiguration: KreateFeatureConfiguration {
    /**
     * Applies the dokka plugin to the root project to enable the dokka multi-module support.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    val isMultiModuleMode: Property<Boolean>
}

/**
 * Default configuration for the docs feature.
 *
 * @since 0.0.1
 * @author Nils Jäkel
 * */
public abstract class DefaultDocsConfiguration @Inject constructor(objects: ObjectFactory): DocsConfiguration {
    override val enabled: Property<Boolean> = objects.property(Boolean::class.java).apply { set(false) }
    override val isMultiModuleMode: Property<Boolean> = objects.property(Boolean::class.java).apply { set(true) }
}
