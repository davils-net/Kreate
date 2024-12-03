package net.davils.kreate.feature

import net.davils.kreate.KreateExtension
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.kotlin.dsl.getByType

public abstract class Task : DefaultTask() {
    @get:Input
    internal val extension: KreateExtension = project.extensions.getByType<KreateExtension>()

    public abstract fun execute()
}
