/*
 * All rights reserved
 * Copyright 2024 Davils
 *
 * Unauthorized copying, distribution, or modification of this work is strictly prohibited.
 */

package net.davils.kreate.feature.core

import net.davils.kreate.Paths
import net.davils.kreate.feature.Task
import org.gradle.api.tasks.TaskAction
import net.davils.kreate.isMultiplatform

public abstract class ConfigureGradleProperties : Task() {
    private val paths = Paths(project)

    @TaskAction
    override fun execute() {
        val isEnabled = extension.cinterop.enabled.get()
        val isMultiplatform = isMultiplatform(project)

        var content = paths.gradleProperties.readText()
        if (isEnabled && isMultiplatform) {
            if (!content.contains("kotlin.mpp.enableCInteropCommonization")) {
                paths.gradleProperties.appendText("\nkotlin.mpp.enableCInteropCommonization=true")
            }

            if (!content.contains("kotlin.native.ignoreDisabledTargets")) {
                paths.gradleProperties.appendText("\nkotlin.native.ignoreDisabledTargets=true")
            }
            return
        }

        if (content.contains("kotlin.mpp.enableCInteropCommonization")) {
            content = content.replace("kotlin.mpp.enableCInteropCommonization=true", "")
        }

        if (content.contains("kotlin.native.ignoreDisabledTargets")) {
            content = content.replace("kotlin.native.ignoreDisabledTargets=true", "")
        }
        paths.gradleProperties.writeText(content)
    }
}