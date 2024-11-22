package net.davils.kreate.core

import org.gradle.api.DefaultTask
import org.gradle.api.provider.ListProperty
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import java.io.File

public abstract class PatchVersions : DefaultTask() {
    private val version = System.getenv("CI_COMMIT_TAG") ?: System.getenv("CI_COMMIT_SHORT_SHA")?.let { "$it-dev" } ?: "0.0.0"

    @get:Input
    public abstract val files: ListProperty<File>

    @TaskAction
    internal fun patch() {
        val files = files.get()
        files.forEach { file ->
            if (!file.exists()) throw IllegalStateException("File $file does not exist")

            val content = file.readText()
            val regex = Regex("\\d+\\.\\d+\\.\\d+")
            file.writeText(content.replace(regex, version))
        }
    }
}