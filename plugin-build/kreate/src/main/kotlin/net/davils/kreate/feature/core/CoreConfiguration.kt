package net.davils.kreate.feature.core

import net.davils.kreate.utils.KreateFeatureConfiguration
import org.gradle.api.provider.Property

public interface CoreConfiguration : KreateFeatureConfiguration {
    public val name: Property<String>
    public val description: Property<String>
    public val license: Property<License>
}
