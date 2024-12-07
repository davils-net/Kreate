/*
 * All rights reserved
 * Copyright 2024 Davils
 *
 * Unauthorized copying, distribution, or modification of this work is strictly prohibited.
 */

package net.davils.kreate.feature.publish

import net.davils.kreate.feature.KreateFeatureConfiguration
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.Property
import javax.inject.Inject

/**
 * Represents the configuration for the publish feature.
 *
 * @since 0.0.1
 * @author Nils Jäkel
 * */
public abstract class PublishConfiguration @Inject constructor(objects: ObjectFactory)  : KreateFeatureConfiguration {
    override val enabled: Property<Boolean> = objects.property(Boolean::class.java).apply { set(false) }

    /**
     * The year the project was created.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    public val inceptionYear: Property<Int> = objects.property(Int::class.java).apply { set(2024) }
}
