package net.davils.kreate.feature.docs

import net.davils.kreate.utils.KreateFeatureConfiguration
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.Property
import javax.inject.Inject

internal interface DocsConfiguration: KreateFeatureConfiguration

public abstract class DefaultDocsConfiguration @Inject constructor(objects: ObjectFactory): DocsConfiguration {
    override val enabled: Property<Boolean> = objects.property(Boolean::class.java).apply { set(false) }
}
