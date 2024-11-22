package net.davils.kreate.publish

import org.gradle.api.provider.Property

public interface PublishConfiguration {
    public val enabled: Property<Boolean>
    public val inceptionYear: Property<Int>
}
