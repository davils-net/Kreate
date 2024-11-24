package net.davils.kreate

import net.davils.kreate.feature.buildconstants.BuildConstants
import net.davils.kreate.feature.core.Core
import net.davils.kreate.feature.cinterop.Cinterop
import net.davils.kreate.feature.docs.Docs
import net.davils.kreate.feature.publish.Publish
import net.davils.kreate.feature.testing.Testing
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.create

public class Kreate : Plugin<Project> {
    override fun apply(project: Project) {
        val extension = project.extensions.create("kreate", KreateExtension::class)

        Core(project, extension).apply()
        Cinterop(project, extension).apply()
        BuildConstants(project, extension).apply()
        Testing(project, extension).apply()
        Docs(project, extension).apply()
        Publish(project, extension).apply()
    }
}
