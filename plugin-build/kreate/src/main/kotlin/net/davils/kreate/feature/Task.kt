package net.davils.kreate.feature

import net.davils.kreate.KreateExtension
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.kotlin.dsl.getByType

/**
 * Represents a kreate task.
 *
 * @since 0.0.1
 * @author Nils Jäkel
 * */
public abstract class Task : DefaultTask() {
    /**
     * The kreate extension.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    @get:Input
    internal val extension: KreateExtension = extensions.getByType<KreateExtension>()

    /**
     * The task to execute.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    public abstract fun execute()
}
