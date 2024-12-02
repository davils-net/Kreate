package net.davils.kreate.feature.core

import net.davils.kreate.feature.Task
import net.davils.kreate.utils.projectVersion
import org.gradle.api.provider.ListProperty
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import java.io.File

/**
 * Task to patch versions in files, that are not controlled by gradle.
 *
 * @since 0.0.1
 * @author Nils Jäkel
 * */
public abstract class VersionPatch : Task() {
    /**
     * A list of [Entry] to patch.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    @get:Input
    public abstract val files: ListProperty<Entry>

    /**
     * Executes the task.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    @TaskAction
    override fun execute() {
        val files = files.get()
        files.forEach { entry ->
            val file = entry.file
            if (!file.exists()) throw IllegalStateException("File $file does not exist")

            val content = file.readText()
            file.writeText(content.replace(entry.regex, projectVersion))
        }
    }
}

/**
 * Represents an entry in the [VersionPatch] task.
 *
 * @since 0.0.1
 * @author Nils Jäkel
 * */
public data class Entry(
    /**
     * The file, that should be patched.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    public val file: File,
    /**
     * The regex, that should be replaced.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    public val regex: String,
)
