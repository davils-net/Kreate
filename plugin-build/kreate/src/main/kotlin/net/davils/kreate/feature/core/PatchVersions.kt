package net.davils.kreate.feature.core

import org.gradle.api.DefaultTask
import org.gradle.api.provider.ListProperty
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import java.io.File

public abstract class PatchVersions : DefaultTask() {
    private val version = System.getenv("CI_COMMIT_TAG") ?: System.getenv("CI_COMMIT_SHORT_SHA")?.let { "$it-dev" } ?: "0.0.0"

    @get:Input
    public abstract val files: ListProperty<Entry>

    @TaskAction
    internal fun patch() {
        val files = files.get()
        files.forEach { entry ->
            val file = entry.file
            if (!file.exists()) throw IllegalStateException("File $file does not exist")

            val content = file.readText()
            file.writeText(content.replace(entry.regex, version))
        }
    }
}

public data class Entry(public val file: File, public val regex: Regex)
