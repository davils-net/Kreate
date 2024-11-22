package net.davils.kreate.core

import net.davils.kreate.KreateExtension
import net.davils.kreate.KreateFeature
import net.davils.kreate.build.BuildConstants
import org.gradle.api.Project
import org.gradle.kotlin.dsl.register

public object Core : KreateFeature {
    override fun apply(project: Project, extension: KreateExtension) {
        project.version = System.getenv("CI_COMMIT_TAG") ?: System.getenv("CI_COMMIT_SHORT_SHA")?.let { "$it-dev" } ?: "0.0.0"

        val license = extension.core.license.orElse(License.ALL_RIGHTS_RESERVED)
        generateLicense(project, license.get())

        project.tasks.register<PatchVersions>("patchVersions") {
            group = BuildConstants.ORGANIZATION_NAME.lowercase()
            description = "Patches all given files with the version of this the project"
        }
    }
}