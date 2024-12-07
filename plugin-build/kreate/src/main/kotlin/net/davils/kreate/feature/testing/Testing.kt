/*
 * All rights reserved
 * Copyright 2024 Davils
 *
 * Unauthorized copying, distribution, or modification of this work is strictly prohibited.
 */

package net.davils.kreate.feature.testing

import io.kotest.framework.multiplatform.gradle.KotestMultiplatformCompilerGradlePlugin
import net.davils.kreate.KreateExtension
import net.davils.kreate.feature.KreateFeature
import org.gradle.api.Project
import net.davils.kreate.feature.isFeatureEnabled
import net.davils.kreate.isMultiplatform
import org.gradle.api.tasks.testing.Test
import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.withType

/**
 * Represents the testing feature.
 *
 * @since 0.0.1
 * @author Nils Jäkel
 * */
public class Testing(override val project: Project, override val extension: KreateExtension) : KreateFeature {
    override fun register(): Unit = project.afterEvaluate {
        if (!isFeatureEnabled(extension.testing)) return@afterEvaluate

        val isMultiplatform = isMultiplatform(project)
        val isTestReport = extension.testing.createTestReport.get()

        if (isMultiplatform) {
            pluginManager.apply(KotestMultiplatformCompilerGradlePlugin::class)
        }

        tasks.withType<Test> {
            useJUnitPlatform()
            if (!isTestReport) {
                return@withType
            }

            reports.junitXml.required.set(true)
            systemProperty("gradle.build.dir", layout.buildDirectory.get().asFile.absolutePath)

            filter {
                isFailOnNoMatchingTests = false
            }

            testLogging {
                showExceptions = true
                showStandardStreams = true
                events = setOf(TestLogEvent.FAILED, TestLogEvent.PASSED, TestLogEvent.SKIPPED)
                exceptionFormat = TestExceptionFormat.FULL
            }
        }
    }
}
