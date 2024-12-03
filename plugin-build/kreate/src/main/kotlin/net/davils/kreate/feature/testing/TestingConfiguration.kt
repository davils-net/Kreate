/*
 * All rights reserved
 * Copyright 2024 Davils
 *
 * Unauthorized copying, distribution, or modification of this work is strictly prohibited.
 */

package net.davils.kreate.feature.testing

import net.davils.kreate.utils.KreateFeatureConfiguration
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.Property
import javax.inject.Inject

/**
 * Creates the configuration for the testing feature.
 *
 * @since 0.0.1
 * @author Nils Jäkel
 * */
internal interface TestingConfiguration : KreateFeatureConfiguration {
    /**
     * Indicates if a test report should be created.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    val createTestReport: Property<Boolean>
}

/**
 * Default configuration for the testing feature.
 *
 * @since 0.0.1
 * @author Nils Jäkel
 * */
public abstract class DefaultTestingConfiguration @Inject constructor(objects: ObjectFactory) : TestingConfiguration {
    override val enabled: Property<Boolean> = objects.property(Boolean::class.java).apply { set(true) }
    override val createTestReport: Property<Boolean> = objects.property(Boolean::class.java).apply { set(true) }
}
