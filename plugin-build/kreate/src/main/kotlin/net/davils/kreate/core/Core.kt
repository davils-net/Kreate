package net.davils.kreate.core

import net.davils.kreate.KreateExtension
import net.davils.kreate.KreateFeature
import org.gradle.api.Project

public object Core : KreateFeature {
    override fun apply(project: Project, extension: KreateExtension) {
        project.version = System.getenv("CI_COMMIT_TAG") ?: System.getenv("CI_COMMIT_SHORT_SHA")?.let { "$it-dev" } ?: "0.0.0"

        val license = extension.core.license.orElse(License.ALL_RIGHTS_RESERVED)
        generateLicense(project, license.get())
    }
}