/*
 * All rights reserved
 * Copyright 2024 Davils
 *
 * Unauthorized copying, distribution, or modification of this work is strictly prohibited.
 */

package net.davils.kreate.feature.cinterop

import net.davils.kreate.utils.KreateFeatureConfiguration
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
internal interface CInteropConfiguration : KreateFeatureConfiguration {
    /**
     * The rust edition.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    val edition: Property<String>
    val initialCBindVersion: Property<String>
    val initialLibCVersion: Property<String>
    val targets: ListProperty<Target>

    fun targets(targets: List<Target>, exclude: List<KonanTarget> = listOf())
}

/**
 * Creates the default configuration for the cinterop feature.
 *
 * @since 0.0.1
 * @author Nils Jäkel
 * */
public abstract class DefaultCInteropConfiguration @Inject constructor(objects: ObjectFactory) : CInteropConfiguration {
    override val enabled: Property<Boolean> = objects.property(Boolean::class.java).apply { set(false) }
    override val edition: Property<String> = objects.property(String::class.java).apply { set("2021") }
    override val initialCBindVersion: Property<String> = objects.property(String::class.java).apply { set("0.27.0") }
    override val initialLibCVersion: Property<String> = objects.property(String::class.java).apply { set("0.2.164") }
    override val targets: ListProperty<Target> = objects.listProperty(Target::class.java).apply { set(listOf(Target.LINUX)) }

    override fun targets(targets: List<Target>, exclude: List<KonanTarget>) {
        val changedTargets: MutableList<Target> = mutableListOf()

        targets.forEach { target ->
            val itemsToRemove = target.target.filter { t -> exclude.contains(t) }
            target.target.removeAll(itemsToRemove)
            changedTargets.add(target)
        }

        this.targets.set(changedTargets)
    }
}