/*
 * All rights reserved
 * Copyright 2024 Davils
 *
 * Unauthorized copying, distribution, or modification of this work is strictly prohibited.
 */

package net.davils.kreate.feature.cinterop

import net.davils.kreate.feature.KreateFeatureConfiguration
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.Property
import org.jetbrains.kotlin.konan.target.KonanTarget
import javax.inject.Inject

/**
 * Represents the configuration for the cinterop feature.
 *
 * @since 0.0.1
 * @author Nils Jäkel
 * */
public abstract class CInteropConfiguration @Inject constructor(objects: ObjectFactory) : KreateFeatureConfiguration {
    override val enabled: Property<Boolean> = objects.property(Boolean::class.java).apply { set(false) }
    /**
     * The rust edition.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    public val edition: Property<String> = objects.property(String::class.java).apply { set("2021") }

    /**
     * The initial cbindgen version.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    public val initialCBindVersion: Property<String> = objects.property(String::class.java).apply { set("0.27.0") }

    /**
     * The initial libc version.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    public val initialLibCVersion: Property<String> = objects.property(String::class.java).apply { set("0.2.167") }

    /**
     * The targets that should be applied for the cinterop.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    public val targets: ListProperty<Target> = objects.listProperty(Target::class.java).apply { set(listOf(Target.LINUX)) }

    /**
     * Sets the targets that should be applied for the cinterop.
     *
     * @param targets The targets that should be applied for the cinterop.
     * @param exclude The targets that should be excluded from the cinterop.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    public fun targets(targets: List<Target>, exclude: List<KonanTarget> = listOf()) {
        val changedTargets: MutableList<Target> = mutableListOf()

        targets.forEach { target ->
            val itemsToRemove = target.target.filter { t -> exclude.contains(t) }
            target.target.removeAll(itemsToRemove)
            changedTargets.add(target)
        }

        this.targets.set(changedTargets)
    }
}
