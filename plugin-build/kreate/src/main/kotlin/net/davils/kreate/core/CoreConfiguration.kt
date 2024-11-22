package net.davils.kreate.core

import org.gradle.api.provider.Property

public interface CoreConfiguration {
    public val name: Property<String>
    public val description: Property<String>
    public val license: Property<License>
}
