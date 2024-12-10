/*
 * All rights reserved
 * Copyright 2024 Davils
 *
 * Unauthorized copying, distribution, or modification of this work is strictly prohibited.
 */

package net.davils.kreate.feature.core

import net.davils.kreate.feature.KreateFeatureConfiguration
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.Property
import javax.inject.Inject

/**
 * Represents the configuration for the core feature.
 *
 * @since 0.0.1
 * @author Nils Jäkel
 * */
public abstract class CoreConfiguration @Inject constructor(objects: ObjectFactory) : KreateFeatureConfiguration {
    override val enabled: Property<Boolean> = objects.property(Boolean::class.java).apply { set(true) }
    /**
     * The name for the current gradle project.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    public val name: Property<String> = objects.property(String::class.java).apply { set("Project") }

    /**
     * The description for the current gradle project.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    public val description: Property<String> = objects.property(String::class.java).apply { set("A Davils project.") }

    /**
     * The [License] for the current gradle project.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     *
     * @see License
     * */
    public val license: Property<License> = objects.property(License::class.java).apply { set(License.ALL_RIGHTS_RESERVED) }

    /**
     * If the current gradle project should have explicit api mode enabled.
     * It enforces the declaration of optional visibility modifiers.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    public val isExplicitApiMode: Property<Boolean> = objects.property(Boolean::class.java).apply { set(true) }

    /**
     * The entries that should be patched with the project version.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    public val patchEntries: ListProperty<Entry> = objects.listProperty(Entry::class.java).apply { set(listOf()) }
}
