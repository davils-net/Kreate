package net.davils.kreate.feature.docs

import net.davils.kreate.KreateExtension
import net.davils.kreate.utils.KreateFeature
import net.davils.kreate.utils.isFeatureEnabled
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.getByName
import org.jetbrains.dokka.gradle.DokkaPlugin
import org.jetbrains.dokka.gradle.DokkaTask

public class Docs(override val project: Project, override val extension: KreateExtension) : KreateFeature {
    override fun apply(): Unit = project.afterEvaluate {
        if (!isFeatureEnabled(extension.docs)) return@afterEvaluate

        pluginManager.apply(DokkaPlugin::class)
        if (project.parent != null) {
            project.rootProject.plugins.apply(DokkaPlugin::class)
        }

        val name = extension.core.name.get().lowercase()
        val projectDescription = extension.core.description.get()
        tasks.getByName("dokkaHtml", DokkaTask::class) {
            moduleName.set(name)
            description = projectDescription
        }
    }
}
