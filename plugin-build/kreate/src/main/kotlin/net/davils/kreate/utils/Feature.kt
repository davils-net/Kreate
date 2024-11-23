package net.davils.kreate.utils

import net.davils.kreate.KreateExtension
import org.gradle.api.Project
import org.gradle.api.provider.Property

public interface KreateFeature {
    public val project: Project
    public val extension: KreateExtension
    public fun apply()
}

public interface KreateFeatureConfiguration {
    public val enabled: Property<Boolean>
}

/**
 * Checks if a kreate feature is enabled.
 *
 * @param configuration The feature configuration
 * @return A [Boolean] that indicates if the feature is enabled
 *
 * @since 0.0.1
 * @author Nils Jäkel
 * */
internal fun isFeatureEnabled(configuration: KreateFeatureConfiguration): Boolean = configuration.enabled.orElse(false).get()
