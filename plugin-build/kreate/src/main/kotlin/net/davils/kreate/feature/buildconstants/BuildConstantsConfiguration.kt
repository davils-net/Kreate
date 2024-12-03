package net.davils.kreate.feature.buildconstants

import net.davils.kreate.utils.KreateFeatureConfiguration
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
internal interface BuildConstantsConfiguration : KreateFeatureConfiguration {
    /**
     * The path in the build directory.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    val buildPath: Property<String>

    /**
     * The source sets to apply the build constants to.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    val sourceSets: Property<KotlinSourceSet>

    /**
     * Indicates whether to only apply the build constants to internal source sets.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    val onlyInternal: Property<Boolean>

    /**
     * The properties to add to the build constants.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    val properties: MapProperty<String, String>
}

/**
 * Represents the default configuration for the build constants feature.
 *
 * @since 0.0.1
 * @author Nils Jäkel
 * */
public abstract class DefaultBuildConstantsConfiguration @Inject constructor(objects: ObjectFactory): BuildConstantsConfiguration {
    override val enabled: Property<Boolean> = objects.property(Boolean::class.java).apply { set(false) }
    override val buildPath: Property<String> = objects.property(String::class.java).apply { set("generated/templates") }
    override val onlyInternal: Property<Boolean> = objects.property(Boolean::class.java).apply { set(true) }
    override val properties: MapProperty<String, String> = objects.mapProperty(String::class.java, String::class.java).apply { set(emptyMap()) }
}

/**
 * Gets the path to the build constants.
 *
 * @since 0.0.1
 * @author Nils Jäkel
 * */
internal fun BuildConstantsConfiguration.path(project: Project): File {
    val path = buildPath.get()
    val buildDir = project.layout.buildDirectory.dir(path)
    return buildDir.get().asFile
}
