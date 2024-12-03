package net.davils.kreate.feature.cinterop

import net.davils.kreate.feature.Task
import org.gradle.api.tasks.TaskAction

/**
 * Task to compile the rust project.
 *
 * @since 0.0.1
 * @author Nils Jäkel
 * */
public abstract class CompileRust : Task() {
    /**
     * The rust project to compile.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    private val rustProject = rustProject(project, extension)

    @TaskAction
    override fun execute() {
        val builder = ProcessBuilder("cargo", "build", "--release")
        builder.directory(rustProject.file.resolve(rustProject.name))

        val process = builder.start()
        process.waitFor()
    }
}
