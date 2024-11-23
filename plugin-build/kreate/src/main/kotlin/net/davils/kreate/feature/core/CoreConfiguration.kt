package net.davils.kreate.feature.core

import net.davils.kreate.utils.KreateFeatureConfiguration
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.Property
import javax.inject.Inject

internal interface CoreConfiguration : KreateFeatureConfiguration {
    val name: Property<String>
    val description: Property<String>
    val license: Property<License>
}

public abstract class DefaultCoreConfiguration @Inject constructor(objects: ObjectFactory) : CoreConfiguration {
    override val enabled: Property<Boolean> = objects.property(Boolean::class.java).apply { set(true) }
    override val name: Property<String> = objects.property(String::class.java).apply { set("Project") }
    override val description: Property<String> = objects.property(String::class.java).apply { set("A Davils project.") }
    override val license: Property<License> = objects.property(License::class.java).apply { set(License.ALL_RIGHTS_RESERVED) }
}
