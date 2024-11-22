package net.davils.kreate

import net.davils.kreate.core.Core
import net.davils.kreate.publish.Publish
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.create

public class Kreate : Plugin<Project> {
    override fun apply(project: Project) {
        val extension = project.extensions.create("kreate", KreateExtension::class)

        Core.apply(project, extension)
        Publish.apply(project, extension)
    }
}