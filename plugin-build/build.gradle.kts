plugins {
    `kreate-publishing`
}

allprojects {
    version = System.getenv("CI_COMMIT_TAG") ?: System.getenv("CI_COMMIT_SHORT_SHA")?.let { "$it-dev" } ?: "0.0.0"
    group = Project.GROUP
}
