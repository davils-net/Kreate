/*
 * All rights reserved
 * Copyright 2024 Davils
 *
 * Unauthorized copying, distribution, or modification of this work is strictly prohibited.
 */

package net.davils.kreate.feature.publish

import net.davils.kreate.KreateExtension
import net.davils.kreate.build.BuildConstants
import net.davils.kreate.feature.KreateFeature
import net.davils.kreate.feature.isFeatureEnabled
import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPom
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.publish.maven.plugins.MavenPublishPlugin
import org.gradle.kotlin.dsl.*

/**
 * Represents the publishing feature.
 *
 * @since 0.0.1
 * @author Nils Jäkel
 * */
public class Publish(override val project: Project, override val extension: KreateExtension) : KreateFeature {
    private val projectName = extension.core.name.get()
    private val projectDescription = extension.core.description.get()
    private val projectLicense = extension.core.license.get()
    private val projectInceptionYear = extension.publish.inceptionYear.get()

    override fun register(): Unit = project.afterEvaluate {
        if (!isFeatureEnabled(extension.publish)) return@afterEvaluate

        require(projectInceptionYear >= 2024) { "The inception year must be at least 2024" }
        plugins.apply(MavenPublishPlugin::class)

        extensions.configure(PublishingExtension::class) {
            publications.withType<MavenPublication> {
                pom {
                    pomConfiguration(this)
                }
            }
            publishRepository(this)
        }
    }

    /**
     * Applies the pom configuration.
     *
     * @param pom The maven pom to apply the configuration to.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
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
