/*
 * All rights reserved
 * Copyright 2024 Davils
 *
 * Unauthorized copying, distribution, or modification of this work is strictly prohibited.
 */

package net.davils.kreate.feature.buildconstants

import net.davils.kreate.feature.KreateFeatureConfiguration
import org.gradle.api.Project
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.MapProperty
import org.gradle.api.provider.Property
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
import java.io.File
import javax.inject.Inject

/**
 * Represents the configuration for the build constants feature.
 *
 * @since 0.0.1
 * @author Nils Jäkel
 * */
public abstract class BuildConstantsConfiguration @Inject constructor(objects: ObjectFactory) : KreateFeatureConfiguration {
    /**
     * The path in the build directory.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    public val buildPath: Property<String> = objects.property(String::class.java).apply { set("generated/templates") }

    /**
     * The source sets to apply the build constants to.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    public abstract val sourceSets: Property<KotlinSourceSet>

    /**
     * If the build constants should be internal.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    public val onlyInternal: Property<Boolean> = objects.property(Boolean::class.java).apply { set(true) }

    /**
     * The properties to add to the build constants.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    public val properties: MapProperty<String, String> = objects.mapProperty(String::class.java, String::class.java).apply { set(emptyMap()) }
}

/**
 * Gets the path to the build constants directory.
 *
 * @param project The gradle project in which the build constants should be generated.
 *
 * @since 0.0.1
 * @author Nils Jäkel
 * */
internal fun BuildConstantsConfiguration.path(project: Project): File {
    val path = buildPath.get()
    val buildDir = project.layout.buildDirectory.dir(path)
    return buildDir.get().asFile
}
