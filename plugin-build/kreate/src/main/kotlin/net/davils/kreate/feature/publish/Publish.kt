package net.davils.kreate.feature.publish

import net.davils.kreate.KreateExtension
import net.davils.kreate.build.BuildConstants
import net.davils.kreate.feature.core.License
import net.davils.kreate.utils.KreateFeature
import net.davils.kreate.utils.isFeatureEnabled
import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPom
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.publish.maven.plugins.MavenPublishPlugin
import org.gradle.kotlin.dsl.*

public class Publish(
    override val project: Project,
    override val extension: KreateExtension,
) : KreateFeature {
    private val projectName = extension.core.name.getOrElse(project.name)
    private val projectDescription = extension.core.description.getOrElse("A Davils project")
    private val projectLicense = extension.core.license.getOrElse(License.ALL_RIGHTS_RESERVED)
    private val projectInceptionYear = extension.publishing.inceptionYear.getOrElse(2024)

    override fun apply(): Unit = project.afterEvaluate {
        if (!isFeatureEnabled(extension.publishing)) return@afterEvaluate

        require(projectInceptionYear >= 2024) { "The inception year must be at least 2024" }
        project.pluginManager.apply(MavenPublishPlugin::class)

        project.extensions.configure(PublishingExtension::class) {
            publications.withType<MavenPublication> {
                pom {
                    pomConfiguration(this)
                }
            }
            publishRepository(this)
        }
    }

    private fun pomConfiguration(pom: MavenPom) {
        pom.name.set(projectName)
        pom.description.set(projectDescription)
        pom.url.set(BuildConstants.ORGANISATION_URL)

        pom.issueManagement {
            system.set("YouTrack")
        }

        pom.ciManagement {
            system.set("Gitlab")
        }

        pom.licenses {
            license {
                name.set(projectLicense.value)
            }
        }

        pom.developers {
            developer {
                id.set(BuildConstants.ORGANIZATION_NAME.lowercase())
                name.set(BuildConstants.ORGANIZATION_NAME)
                email.set(BuildConstants.ORGANISATION_EMAIL)
                url.set(BuildConstants.ORGANISATION_URL)
                organization.set(BuildConstants.ORGANIZATION_NAME)
                organizationUrl.set(BuildConstants.ORGANISATION_URL)
            }
        }
    }
}
