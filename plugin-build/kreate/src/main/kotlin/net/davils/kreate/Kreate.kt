/*
 * All rights reserved
 * Copyright 2024 Davils
 *
 * Unauthorized copying, distribution, or modification of this work is strictly prohibited.
 */

package net.davils.kreate

import net.davils.kreate.feature.buildconstants.buildConstants
import net.davils.kreate.feature.cinterop.cinterop
import net.davils.kreate.feature.core.core
import net.davils.kreate.feature.docs.docs
import net.davils.kreate.feature.publish.publish
import net.davils.kreate.feature.testing.testing
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

        publish(project, kreateExtension.publish)
        core(project, kreateExtension.core)
        cinterop(project, kreateExtension.cinterop)
        buildConstants(project, kreateExtension.buildConstants)
        testing(project, kreateExtension.testing)
        docs(project, kreateExtension.docs)
    }
}
