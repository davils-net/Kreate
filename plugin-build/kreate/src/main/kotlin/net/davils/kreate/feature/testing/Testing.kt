package net.davils.kreate.feature.testing

import io.kotest.framework.multiplatform.gradle.KotestMultiplatformCompilerGradlePlugin
import net.davils.kreate.KreateExtension
import net.davils.kreate.utils.KreateFeature
import org.gradle.api.Project
import net.davils.kreate.utils.isFeatureEnabled
import net.davils.kreate.utils.isMultiplatform
import org.gradle.api.tasks.testing.Test
import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.withType

public class Testing(override val project: Project, override val extension: KreateExtension) : KreateFeature {
    override fun apply(): Unit = project.afterEvaluate {
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
