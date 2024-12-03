package net.davils.kreate.feature.core

import net.davils.kreate.utils.KreateFeatureConfiguration
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.Property
import javax.inject.Inject

/**
 * Represents the configuration for the core feature.
 *
 * @since 0.0.1
 * @author Nils Jäkel
 * */
internal interface CoreConfiguration : KreateFeatureConfiguration {
    /**
     * The project name.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    val name: Property<String>

    /**
     * The project description.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    val description: Property<String>

    /**
     * The [License] for the project.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    val license: Property<License>
}

/**
 * The default configuration for the core feature.
 *
 * @since 0.0.1
 * @author Nils Jäkel
 * */
public abstract class DefaultCoreConfiguration @Inject constructor(objects: ObjectFactory) : CoreConfiguration {
    override val enabled: Property<Boolean> = objects.property(Boolean::class.java).apply { set(true) }
    override val name: Property<String> = objects.property(String::class.java).apply { set("Project") }
    override val description: Property<String> = objects.property(String::class.java).apply { set("A Davils project.") }
    override val license: Property<License> = objects.property(License::class.java).apply { set(License.ALL_RIGHTS_RESERVED) }
}
