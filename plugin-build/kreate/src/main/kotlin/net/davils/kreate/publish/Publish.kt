package net.davils.kreate.publish

import net.davils.kreate.KreateExtension
import net.davils.kreate.KreateFeature
import net.davils.kreate.build.BuildConstants
import net.davils.kreate.core.License
import org.gradle.api.Project
import org.gradle.api.credentials.HttpHeaderCredentials
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.publish.maven.plugins.MavenPublishPlugin
import org.gradle.authentication.http.HttpHeaderAuthentication
import org.gradle.kotlin.dsl.*
import java.net.URI

public object Publish : KreateFeature {
    override fun apply(project: Project, extension: KreateExtension): Unit = project.afterEvaluate {
        val isPublishingEnabled = extension.publishing.enabled.orElse(false).get()
        if (!isPublishingEnabled) {
            return@afterEvaluate
        }
        project.pluginManager.apply(MavenPublishPlugin::class)

        val inceptionYear = extension.publishing.inceptionYear.orElse(2024).get()
        if (inceptionYear <= 0) throw IllegalStateException("inceptionYear must be greater than 0")

        val projectName = extension.core.name.orElse(project.name).get()
        val projectDescription = extension.core.description.orElse("A Davils project").get()
        val license = extension.core.license.orElse(License.ALL_RIGHTS_RESERVED).get()

        project.extensions.configure(PublishingExtension::class) {
            publications.withType<MavenPublication> {
                pom {
                    name.set(projectName)
                    description.set(projectDescription)
                    url.set(BuildConstants.ORGANISATION_URL)

                    issueManagement {
                        system.set("YouTrack")
                    }

                    ciManagement {
                        system.set("Gitlab")
                    }

                    licenses {
                        license {
                            name.set(license.value)
                        }
                    }

                    developers {
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

            repositories {
                val jobToken = System.getenv("CI_JOB_TOKEN") ?: return@repositories
                val projectId = System.getenv(/* name = */ "CI_PROJECT_ID")
                val apiV4 = System.getenv(/* name = */ "CI_API_V4_URL")

                maven {
                    name = "GitLab"
                    url = URI("$apiV4/projects/$projectId/packages/maven")
                    authentication {
                        create<HttpHeaderAuthentication>("token") {
                            credentials(HttpHeaderCredentials::class) {
                                name = "Job-Token"
                                value = jobToken
                            }
                        }
                    }
                }
            }
        }
    }
}
