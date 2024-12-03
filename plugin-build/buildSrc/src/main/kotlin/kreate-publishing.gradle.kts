/*
 * All rights reserved
 * Copyright 2024 Davils
 *
 * Unauthorized copying, distribution, or modification of this work is strictly prohibited.
 */

plugins {
    `maven-publish`
}

publishing {
    publications {
        withType<MavenPublication> {
            pom {
                name = Project.NAME
                description = Project.DESCRIPTION

                developers {
                    developer {
                        id = Project.ORGANIZATION_NAME.lowercase()
                        name = Project.ORGANIZATION_NAME
                        email = "contact@davils.net"
                        organization = Project.ORGANIZATION_NAME
                        organizationUrl = Project.ORGANISATION_URL
                        url = Project.ORGANISATION_URL
                        timezone = "Europe/Berlin"
                    }
                }

                licenses {
                    license {
                        name = "All rights reserved"
                    }
                }

                issueManagement {
                    system = "YouTrack"
                }
            }

            repositories {
                if (System.getenv(/* name = */ "CI_JOB_TOKEN") != null) {
                    maven {
                        name = "GitLab"

                        val projectId = System.getenv(/* name = */ "CI_PROJECT_ID")
                        val apiV4 = System.getenv(/* name = */ "CI_API_V4_URL")
                        url = uri("$apiV4/projects/$projectId/packages/maven")

                        authentication {
                            create(/* name = */ "token", /* type = */ HttpHeaderAuthentication::class.java) {
                                credentials(HttpHeaderCredentials::class.java) {
                                    name = "Job-Token"
                                    value = System.getenv(/* name = */ "CI_JOB_TOKEN")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}