/*
 * All rights reserved
 * Copyright 2024 Davils
 *
 * Unauthorized copying, distribution, or modification of this work is strictly prohibited.
 */

package net.davils.kreate.feature.cinterop

import net.davils.kreate.Paths
import net.davils.kreate.feature.Task
import org.gradle.api.tasks.TaskAction
import net.davils.kreate.isMultiplatform

/**
 * Task to enable the C interop support.
 *
 * @since 0.0.2
 * @author Nils Jäkel
 * */
public abstract class EnableCInteropSupport : Task() {
    /**
     * The path handler.
     *
     * @since 0.0.2
     * @author Nils Jäkel
     * */
    private val paths = Paths(project)

    /**
     * The task action.
     * It configures the gradle properties.
     *
     * @since 0.0.2
     * @author Nils Jäkel
     * */
    @TaskAction
    override fun execute() {
        val isMultiplatform = isMultiplatform(project)
        val content = paths.gradleProperties.readText()

        if (isEnabled && isMultiplatform) {
            if (!content.contains("kotlin.mpp.enableCInteropCommonization")) {
                paths.gradleProperties.appendText("\nkotlin.mpp.enableCInteropCommonization=true")
            }

            if (!content.contains("kotlin.native.ignoreDisabledTargets")) {
                paths.gradleProperties.appendText("\nkotlin.native.ignoreDisabledTargets=true")
            }
            return
        }
        paths.gradleProperties.writeText(content)
    }
}
