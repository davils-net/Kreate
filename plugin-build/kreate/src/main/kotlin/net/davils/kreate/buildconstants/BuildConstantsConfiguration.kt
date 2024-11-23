package net.davils.kreate.buildconstants

import org.gradle.api.Project
import org.gradle.api.provider.Property
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
import java.io.File

public interface BuildConstantsConfiguration {
    public val enabled: Property<Boolean>
    public val buildPath: Property<String>
    public val sourceSets: Property<KotlinSourceSet>
}

internal fun BuildConstantsConfiguration.path(project: Project): File {
    val path = buildPath.orElse("generated/templates").get()
    val buildDir = project.layout.buildDirectory.dir(path)
    return buildDir.get().asFile
}
