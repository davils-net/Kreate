package net.davils.kreate.feature.cinterop

import net.davils.kreate.utils.os
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import net.davils.kreate.utils.OsType

public abstract class GenerateDefFiles : DefaultTask() {
    private val rustConf = rustProject(project)
    private val includeDir = rustConf.second.resolve(rustConf.first).resolve("include")
    private val nativeCInteropDir = project.rootProject.file("cinterop")

    @TaskAction
    public fun generate() {
        val hFile = includeDir.list()?.joinToString(" ") ?: throw IllegalStateException("No header file found in $includeDir")

        nativeCInteropDir.mkdirs()
        val defFile = nativeCInteropDir.resolve("cinterop.def")
        if (!defFile.exists()) {
            defFile.createNewFile()
        }

        when (os) {
            OsType.WINDOWS -> {
                defFile.writeText(
                    """
                    headers = $hFile
                    staticLibraries = ${rustConf.first.removeSuffix("-rust")}.lib
                    compilerOpts = -I${includeDir.absolutePath.replace("\\", "/")}
                    libraryPaths = ${rustConf.second.resolve("target/release").absolutePath.replace("\\", "/")}
                """.trimIndent()
                )
            }

            else -> {
                defFile.writeText(
                    """
                    headers = $hFile
                    staticLibraries = lib${rustConf.first.removeSuffix("-rust")}.a
                    compilerOpts = -I$includeDir
                    libraryPaths = ${rustConf.second.resolve("target/release")}
                """.trimIndent()
                )
            }
        }
    }
}
