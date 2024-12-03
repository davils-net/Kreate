/*
 * All rights reserved
 * Copyright 2024 Davils
 *
 * Unauthorized copying, distribution, or modification of this work is strictly prohibited.
 */

package net.davils.kreate.utils

/**
 * Represents the operating systems.
 *
 * @since 0.0.1
 * @author Nils Jäkel
 * */
internal enum class OsType(val value: String) {
    WINDOWS("windows"),
    LINUX("linux"),
    MACOS("macos"),
    UNKNOWN("unknown");
}

/**
 * Resolves the [OsType] as [Lazy] property.
 *
 * @since 0.0.1
 * @author Nils Jäkel
 *
 * @see OsType
 * @see Lazy
 * */
internal val os: OsType by lazy {
    val osName = System.getProperty("os.name").lowercase()
    when {
        osName.contains(other = "win") -> OsType.WINDOWS
        osName.contains(other = "nix") || osName.contains(other = "nux") || osName.contains(other = "aix") -> OsType.LINUX
        osName.contains(other = "mac") || osName.contains(other = "darwin") || osName.contains(other = "sunos") -> OsType.MACOS
        else -> { OsType.UNKNOWN }
    }
}

/**
 * Resolves the current project version as [Lazy] property.
 *
 * The default version is `0.0.0`, but it uses the `CI_COMMIT_SHORT_SHA` or `CI_COMMIT_TAG` environment variables
 * from the Gitlab CI pipeline to set the version in production.
 *
 * @since 0.0.1
 * @author Nils Jäkel
 *
 * @see Lazy
 * */
internal val projectVersion: String by lazy {
    System.getenv("CI_COMMIT_TAG") ?: System.getenv("CI_COMMIT_SHORT_SHA")?.let { "$it-dev" } ?: "0.0.0"
}
