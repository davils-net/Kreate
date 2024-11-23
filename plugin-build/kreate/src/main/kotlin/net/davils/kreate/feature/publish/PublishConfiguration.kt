package net.davils.kreate.feature.publish

import net.davils.kreate.utils.KreateFeatureConfiguration
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.Property
import javax.inject.Inject

internal interface PublishConfiguration : KreateFeatureConfiguration {
    val inceptionYear: Property<Int>
}

public abstract class DefaultPublishConfiguration @Inject constructor(objects: ObjectFactory) : PublishConfiguration {
    override val enabled: Property<Boolean> = objects.property(Boolean::class.java).apply { set(false) }
    override val inceptionYear: Property<Int> = objects.property(Int::class.java).apply { set(2024) }
}
