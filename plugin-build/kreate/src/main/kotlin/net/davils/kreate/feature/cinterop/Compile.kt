package net.davils.kreate.feature.cinterop

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

public abstract class CompileRust : DefaultTask() {
    private val rustConf = rustProject(project)

    @TaskAction
    public fun compile() {
        val builder = ProcessBuilder("cargo", "build", "--release")
        builder.directory(rustConf.second.resolve(rustConf.first))

        val process = builder.start()
        process.waitFor()
    }
}
