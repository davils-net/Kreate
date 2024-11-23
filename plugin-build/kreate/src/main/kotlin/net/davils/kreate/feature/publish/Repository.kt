package net.davils.kreate.feature.publish

import org.gradle.api.credentials.HttpHeaderCredentials
import org.gradle.api.publish.PublishingExtension
import org.gradle.authentication.http.HttpHeaderAuthentication
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.credentials
import java.net.URI

internal fun publishRepository(publishExtension: PublishingExtension) {
    val jobToken = System.getenv("CI_JOB_TOKEN") ?: return
    val projectId = System.getenv(/* name = */ "CI_PROJECT_ID")
    val apiV4 = System.getenv(/* name = */ "CI_API_V4_URL")

    publishExtension.repositories {
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
