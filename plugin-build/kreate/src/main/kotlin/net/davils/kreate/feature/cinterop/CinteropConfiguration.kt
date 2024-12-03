package net.davils.kreate.feature.cinterop

import net.davils.kreate.utils.KreateFeatureConfiguration
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.Property
import javax.inject.Inject

/**
 * Represents the configuration for the cinterop feature.
 *
 * @since 0.0.1
 * @author Nils Jäkel
 * */
internal interface CinteropConfiguration : KreateFeatureConfiguration {
    /**
     * The rust edition.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    val edition: Property<String>
    val initialCBindVersion: Property<String>
    val initialLibCVersion: Property<String>
}

/**
 * Creates the default configuration for the cinterop feature.
 *
 * @since 0.0.1
 * @author Nils Jäkel
 * */
public abstract class DefaultCinteropConfiguration @Inject constructor(objects: ObjectFactory) : CinteropConfiguration {
    override val enabled: Property<Boolean> = objects.property(Boolean::class.java).apply { set(false) }
    override val edition: Property<String> = objects.property(String::class.java).apply { set("2021") }
    override val initialCBindVersion: Property<String> = objects.property(String::class.java).apply { set("0.27.0") }
    override val initialLibCVersion: Property<String> = objects.property(String::class.java).apply { set("0.2.164") }
}
