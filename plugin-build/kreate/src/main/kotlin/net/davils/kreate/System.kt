/*
 * All rights reserved
 * Copyright 2024 Davils
 *
 * Unauthorized copying, distribution, or modification of this work is strictly prohibited.
 */

package net.davils.kreate

/**
 * Contains all available operating system types.
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
 * Gets the [OsType] of the current operating system as [Lazy] property.
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
