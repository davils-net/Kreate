package net.davils.kreate.feature.testing

import net.davils.kreate.utils.KreateFeatureConfiguration
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.Property
import javax.inject.Inject

internal interface TestingConfiguration : KreateFeatureConfiguration {
    val createTestReport: Property<Boolean>
}

public abstract class DefaultTestingConfiguration @Inject constructor(objects: ObjectFactory) : TestingConfiguration {
    override val enabled: Property<Boolean> = objects.property(Boolean::class.java).apply { set(true) }
    override val createTestReport: Property<Boolean> = objects.property(Boolean::class.java).apply { set(true) }
}
