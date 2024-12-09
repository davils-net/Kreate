/*
 * All rights reserved
 * Copyright 2024 Davils
 *
 * Unauthorized copying, distribution, or modification of this work is strictly prohibited.
 */

package net.davils.kreate

import net.davils.kreate.feature.buildconstants.BuildConstants
import net.davils.kreate.feature.core.Core
import net.davils.kreate.feature.cinterop.CInterop
import net.davils.kreate.feature.docs.Docs
import net.davils.kreate.feature.publish.Publish
import net.davils.kreate.feature.testing.Testing
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.create

/**
 * Entry point for the Kreate plugin.
 *
 * @since 0.0.1
 * @author Nils Jäkel
 * */
public class Kreate : Plugin<Project> {
    override fun apply(project: Project) {
        val kreateExtension = project.extensions.create("kreate", KreateExtension::class)

        Core(project, kreateExtension).register()
        Publish(project, kreateExtension).register()
        CInterop(project, kreateExtension).register()
        BuildConstants(project, kreateExtension).register()
        Testing(project, kreateExtension).register()
        Docs(project, kreateExtension).register()
    }
}
