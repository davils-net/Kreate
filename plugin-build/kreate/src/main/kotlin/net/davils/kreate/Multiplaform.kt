package net.davils.kreate

import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.plugin.KotlinMultiplatformPluginWrapper

internal fun isMultiplatform(project: Project): Boolean = project.plugins.any { it is KotlinMultiplatformPluginWrapper }
