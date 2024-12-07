/*
 * All rights reserved
 * Copyright 2024 Davils
 *
 * Unauthorized copying, distribution, or modification of this work is strictly prohibited.
 */

package net.davils.kreate.feature.testing

import net.davils.kreate.feature.KreateFeatureConfiguration
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.Property
import javax.inject.Inject

/**
 * Represents the configuration for the testing feature.
 *
 * @since 0.0.1
 * @author Nils Jäkel
 * */
public abstract class TestingConfiguration @Inject constructor(objects: ObjectFactory) : KreateFeatureConfiguration {
    override val enabled: Property<Boolean> = objects.property(Boolean::class.java).apply { set(true) }

    /**
     * If a test report should be created.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    public val createTestReport: Property<Boolean> = objects.property(Boolean::class.java).apply { set(true) }
}
