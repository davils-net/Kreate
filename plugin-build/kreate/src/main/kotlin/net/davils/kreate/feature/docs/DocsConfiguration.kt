/*
 * All rights reserved
 * Copyright 2024 Davils
 *
 * Unauthorized copying, distribution, or modification of this work is strictly prohibited.
 */

package net.davils.kreate.feature.docs

import net.davils.kreate.feature.KreateFeatureConfiguration
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.Property
import javax.inject.Inject

/**
 * Represents the configuration for the docs feature.
 *
 * @since 0.0.1
 * @author Nils Jäkel
 * */
public abstract class DocsConfiguration @Inject constructor(objects: ObjectFactory): KreateFeatureConfiguration {
    override val enabled: Property<Boolean> = objects.property(Boolean::class.java).apply { set(false) }

    /**
     * Applies the dokka plugin to the root project.
     * It enables the dokka multi-module support.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    public val isMultiModuleMode: Property<Boolean> = objects.property(Boolean::class.java).apply { set(true) }
}
