package net.davils.kreate.utils

public enum class OsType(public val value: String) {
    WINDOWS("windows"),
    LINUX("linux"),
    MACOS("macos"),
    UNKNOWN("unknown");
}

public val os: OsType by lazy {
    val osName = System.getProperty("os.name").lowercase()
    when {
        osName.contains(other = "win") -> OsType.WINDOWS
        osName.contains(other = "nix") || osName.contains(other = "nux") || osName.contains(other = "aix") -> OsType.LINUX
        osName.contains(other = "mac") || osName.contains(other = "darwin") || osName.contains(other = "sunos") -> OsType.MACOS
        else -> { OsType.UNKNOWN }
    }
}

internal val projectVersion: String by lazy {
    System.getenv("CI_COMMIT_TAG") ?: System.getenv("CI_COMMIT_SHORT_SHA")?.let { "$it-dev" } ?: "0.0.0"
}
