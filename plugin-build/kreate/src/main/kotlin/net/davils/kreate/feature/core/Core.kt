package net.davils.kreate.feature.core

import net.davils.kreate.KreateExtension
import net.davils.kreate.build.BuildConstants
import net.davils.kreate.utils.KreateFeature
import net.davils.kreate.utils.isFeatureEnabled
import org.gradle.api.Project
import org.gradle.kotlin.dsl.register

public class Core(
    override val project: Project,
    override val extension: KreateExtension
) : KreateFeature {
    private val license = extension.core.license.getOrElse(License.ALL_RIGHTS_RESERVED)

    override fun apply() {
        project.version = System.getenv("CI_COMMIT_TAG") ?: System.getenv("CI_COMMIT_SHORT_SHA")?.let { "$it-dev" } ?: "0.0.0"

        project.afterEvaluate {
            if (!isFeatureEnabled(extension.core)) return@afterEvaluate
            generateLicense(project, license)

            project.tasks.register<PatchVersions>("patchVersions") {
                group = BuildConstants.ORGANIZATION_NAME.lowercase()
                description = "Patches all given files with the version of this the project"
            }
        }
    }
}
