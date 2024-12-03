/*
 * All rights reserved
 * Copyright 2024 Davils
 *
 * Unauthorized copying, distribution, or modification of this work is strictly prohibited.
 */

package net.davils.kreate.feature.publish

import net.davils.kreate.utils.KreateFeatureConfiguration
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.Property
import javax.inject.Inject

/**
 * Represents the configuration for the publish feature.
 *
 * @since 0.0.1
 * @author Nils Jäkel
 * */
internal interface PublishConfiguration : KreateFeatureConfiguration {
    /**
     * The inception year of the project.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    val inceptionYear: Property<Int>
}

/**
 * Represents the default configuration for the publish feature.
 *
 * @since 0.0.1
 * @author Nils Jäkel
 * */
public abstract class DefaultPublishConfiguration @Inject constructor(objects: ObjectFactory) : PublishConfiguration {
    override val enabled: Property<Boolean> = objects.property(Boolean::class.java).apply { set(false) }
    override val inceptionYear: Property<Int> = objects.property(Int::class.java).apply { set(2024) }
}
