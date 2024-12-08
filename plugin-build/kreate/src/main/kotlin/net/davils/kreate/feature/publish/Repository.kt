/*
 * All rights reserved
 * Copyright 2024 Davils
 *
 * Unauthorized copying, distribution, or modification of this work is strictly prohibited.
 */

package net.davils.kreate.feature.publish

import org.gradle.api.credentials.HttpHeaderCredentials
import org.gradle.api.publish.PublishingExtension
import org.gradle.authentication.http.HttpHeaderAuthentication
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.credentials
import java.net.URI

/**
 * Adds the gitlab maven repository to the [PublishingExtension].
 *
 * @param publishExtension The current gradle project publishing extension.
 *
 * @since 0.0.1
 * @author Nils Jäkel
 * */
internal fun publishRepository(publishExtension: PublishingExtension) {
    val jobToken = System.getenv("CI_JOB_TOKEN") ?: return
    val projectId = System.getenv("CI_PROJECT_ID")
    val apiV4 = System.getenv("CI_API_V4_URL")

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
