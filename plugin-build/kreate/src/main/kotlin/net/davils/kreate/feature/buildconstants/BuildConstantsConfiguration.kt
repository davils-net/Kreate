package net.davils.kreate.feature.buildconstants

import net.davils.kreate.utils.KreateFeatureConfiguration
import org.gradle.api.Project
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.Property
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
import java.io.File
import javax.inject.Inject

internal interface BuildConstantsConfiguration : KreateFeatureConfiguration {
    val buildPath: Property<String>
    val sourceSets: Property<KotlinSourceSet>
}

public abstract class DefaultBuildConstantsConfiguration @Inject constructor(objects: ObjectFactory): BuildConstantsConfiguration {
    override val enabled: Property<Boolean> = objects.property(Boolean::class.java).apply { set(false) }
    override val buildPath: Property<String> = objects.property(String::class.java).apply { set("generated/templates") }
}

internal fun BuildConstantsConfiguration.path(project: Project): File {
    val path = buildPath.get()
    val buildDir = project.layout.buildDirectory.dir(path)
    return buildDir.get().asFile
}
