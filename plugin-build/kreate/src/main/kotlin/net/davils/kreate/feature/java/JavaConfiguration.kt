/*
 * All rights reserved
 * Copyright 2024 Davils
 *
 * Unauthorized copying, distribution, or modification of this work is strictly prohibited.
 */

package net.davils.kreate.feature.java

import net.davils.kreate.feature.KreateFeatureConfiguration
import org.gradle.api.JavaVersion
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.Property
import javax.inject.Inject

/**
 * Represents the configuration for the java feature.
 *
 * @since 0.0.3
 * @author Nils Jäkel
 * */
public abstract class JavaConfiguration @Inject constructor(objects: ObjectFactory) : KreateFeatureConfiguration {
    override val enabled: Property<Boolean> = objects.property(Boolean::class.javaObjectType).apply { set(true) }

    /**
     * The java version to use.
     *
     * @since 0.0.3
     * @author Nils Jäkel
     * */
    public val javaVersion: Property<JavaVersion> = objects.property(JavaVersion::class.java).apply { set(JavaVersion.VERSION_21) }

    /**
     * If the sources jar should be created.
     *
     * @since 0.0.3
     * @author Nils Jäkel
     * */
    public val withSourcesJar: Property<Boolean> = objects.property(Boolean::class.javaObjectType).apply { set(true) }

    /**
     * If the javadoc jar should be created.
     *
     * @since 0.0.3
     * @author Nils Jäkel
     * */
    public val withJavadocJar: Property<Boolean> = objects.property(Boolean::class.javaObjectType).apply { set(true) }
}