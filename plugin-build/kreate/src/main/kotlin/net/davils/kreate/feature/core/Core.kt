package net.davils.kreate.feature.core

import net.davils.kreate.KreateExtension
import net.davils.kreate.build.BuildConstants
import net.davils.kreate.utils.KreateFeature
import net.davils.kreate.utils.isFeatureEnabled
import net.davils.kreate.utils.projectVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.register

public class Core(
    override val project: Project,
    override val extension: KreateExtension
) : KreateFeature {
    private val license = extension.core.license.get()

    override fun apply() {
        project.version = projectVersion

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
