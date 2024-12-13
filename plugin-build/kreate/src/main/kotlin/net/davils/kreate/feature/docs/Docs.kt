/*
 * All rights reserved
 * Copyright 2024 Davils
 *
 * Unauthorized copying, distribution, or modification of this work is strictly prohibited.
 */

package net.davils.kreate.feature.docs

import net.davils.kreate.feature.feature
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.getByName
import org.jetbrains.dokka.gradle.DokkaPlugin
import org.jetbrains.dokka.gradle.DokkaTask

/**
 * Applies the docs feature to the gradle project.
 *
 * @param project The current gradle project.
 * @param config The docs' configuration.
 *
 * @since 0.0.1
 * @author Nils Jäkel
 * */
internal fun docs(project: Project, config: DocsConfiguration) = project.feature(config) { ext ->
    val isMultiModuleMode = config.isMultiModuleMode.get()
    val name = ext.core.name.get()
    val description = ext.core.description.get()

    pluginManager.apply(DokkaPlugin::class)
    if (parent != null && isMultiModuleMode && !project.rootProject.plugins.hasPlugin("org.jetbrains.dokka")) {
        rootProject.pluginManager.apply(DokkaPlugin::class)
    }

    tasks.getByName("dokkaHtml", DokkaTask::class) {
        this.moduleName.set(name)
        this.description = description
    }
}
