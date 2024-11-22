package net.davils.kreate.core

import org.gradle.api.provider.Property
import java.io.File

public interface CoreConfiguration {
    public val name: Property<String>
    public val description: Property<String>
    public val license: Property<License>

    public fun patchVersionsInFiles(vararg files: File) {
        files.forEach { file ->
            patchVersionsInFile(file)
        }
    }

    public fun patchVersionsInFiles(files: List<File>) {
        patchVersionsInFiles(*files.toTypedArray())
    }

    public fun patchVersionsInFile(file: File) {
        val version = System.getenv("CI_COMMIT_TAG") ?: System.getenv("CI_COMMIT_SHORT_SHA")?.let { "$it-dev" } ?: "0.0.0"
        val content = file.readText()
        file.writeText(content.replace(Regex("\\d+\\.\\d+\\.\\d+"), version))
    }
}
