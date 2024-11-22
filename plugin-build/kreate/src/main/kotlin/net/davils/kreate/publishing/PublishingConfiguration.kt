package net.davils.kreate.publishing

import org.gradle.api.provider.Property

public interface PublishingConfiguration {
    public val enabled: Property<Boolean>
    public val inceptionYear: Property<Int>
}