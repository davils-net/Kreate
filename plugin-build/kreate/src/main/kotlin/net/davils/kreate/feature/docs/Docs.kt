package net.davils.kreate.feature.docs

import net.davils.kreate.KreateExtension
import net.davils.kreate.utils.KreateFeature
import net.davils.kreate.utils.isFeatureEnabled
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.getByName
import org.jetbrains.dokka.gradle.DokkaPlugin
import org.jetbrains.dokka.gradle.DokkaTask

/**
 * Represents the docs feature.
 *
 * @since 0.0.1
 * @author Nils Jäkel
 * */
public class Docs(override val project: Project, override val extension: KreateExtension) : KreateFeature {
    private val isMultiModuleMode = extension.docs.isMultiModuleMode.get()

    override fun apply(): Unit = project.afterEvaluate {
        if (!isFeatureEnabled(extension.docs)) return@afterEvaluate

        pluginManager.apply(DokkaPlugin::class)
        if (parent != null && isMultiModuleMode) {
            rootProject.plugins.apply(DokkaPlugin::class)
        }

        val name = extension.core.name.get()
        val projectDescription = extension.core.description.get()
        tasks.getByName("dokkaHtml", DokkaTask::class) {
            moduleName.set(name)
            description = projectDescription
        }
    }
}
