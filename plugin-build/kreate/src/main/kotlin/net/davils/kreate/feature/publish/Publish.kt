/*
 * All rights reserved
 * Copyright 2024 Davils
 *
 * Unauthorized copying, distribution, or modification of this work is strictly prohibited.
 */

package net.davils.kreate.feature.publish

import net.davils.kreate.build.BuildConstants
import net.davils.kreate.feature.core.License
import net.davils.kreate.feature.feature
import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPom
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.kotlin.dsl.*

/**
 * Applies the publish feature to the gradle project.
 *
 * @param project The current gradle project.
 * @param config The publishing configuration.
 *
 * @since 0.0.2
 * @author Nils Jäkel
 * */
internal fun publish(project: Project, config: PublishConfiguration) = project.feature(config) { ext ->
    val name = ext.core.name.get()
    val description = ext.core.description.get()
    val license = ext.core.license.get()
    val inceptionYear = config.inceptionYear.get()

    require(inceptionYear >= 2024) { "The inception year must be at least 2024" }
    pluginManager.apply("maven-publish")

    extensions.configure(PublishingExtension::class) {
        publications.withType<MavenPublication> {
            pom {
                pomConfiguration(this, name, description, license)
            }
        }
        publishRepository(this)
    }
}

/**
 * Applies the pom configuration.
 *
 * @param pom The maven pom to apply the configuration to.
 * @param name The name of the project.
 * @param description The description of the project.
 * @param license The license of the project.
 *
 * @since 0.0.1
 * @author Nils Jäkel
 * */
private fun pomConfiguration(pom: MavenPom, name: String, description: String, license: License) {
    pom.name.set(name)
    pom.description.set(description)
    pom.url.set(BuildConstants.ORGANISATION_URL)

    pom.issueManagement {
        this.system.set("YouTrack")
    }

    pom.ciManagement {
        this.system.set("Gitlab")
    }

    pom.licenses {
        license {
            this.name.set(license.value)
        }
    }

    pom.developers {
        developer {
            this.id.set(BuildConstants.ORGANIZATION_NAME.lowercase())
            this.name.set(BuildConstants.ORGANIZATION_NAME)
            this.email.set(BuildConstants.ORGANISATION_EMAIL)
            this.url.set(BuildConstants.ORGANISATION_URL)
            this.organization.set(BuildConstants.ORGANIZATION_NAME)
            this.organizationUrl.set(BuildConstants.ORGANISATION_URL)
        }
    }
}
