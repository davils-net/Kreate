package net.davils.kreate.feature.buildconstants

import net.davils.kreate.build.BuildConstants
import net.davils.kreate.feature.Task
import org.gradle.api.tasks.TaskAction

/**
 * Task to generate the build constants.
 *
 * @since 0.0.1
 * @author Nils Jäkel
 * */
public abstract class GenerateBuildConstants : Task() {
    /**
     * The project name from the build constants file.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    private val projectName = extension.core.name.get()

    /**
     * The group from the build constants file.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    private val group = BuildConstants.GROUP

    /**
     * Executes the task.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    @TaskAction
    override fun execute() {
        val properties = extension.buildConstants.properties.get()

        val content = properties.entries.joinToString("\n") { "const val ${it.key} = \"${it.value}\"" }
        val generatedDir = extension.buildConstants.path(project)
        generatedDir.mkdirs()

        val buildConstantsFile = generatedDir.resolve(relative = "BuildConstants.kt")
        if (!buildConstantsFile.exists()) {
            buildConstantsFile.createNewFile()
        }

        val isInternal = extension.buildConstants.onlyInternal.get()
        buildConstantsFile.writeText(
            """
             package $group.${projectName.lowercase()}.build
              
             ${if (isInternal) "internal" else "public"} object BuildConstants {
                $content
             }
            """.trimIndent()
        )
    }
}
