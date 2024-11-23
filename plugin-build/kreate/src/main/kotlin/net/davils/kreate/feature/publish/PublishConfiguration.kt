package net.davils.kreate.feature.publish

import net.davils.kreate.utils.KreateFeatureConfiguration
import org.gradle.api.provider.Property

public interface PublishConfiguration : KreateFeatureConfiguration {
    public val inceptionYear: Property<Int>
}
