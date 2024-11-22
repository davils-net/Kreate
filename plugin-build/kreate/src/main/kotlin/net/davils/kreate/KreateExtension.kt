package net.davils.kreate

import net.davils.kreate.core.CoreConfiguration
import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.api.tasks.Nested

public interface KreateExtension {
    @get:Nested
    public val core: CoreConfiguration
}

public fun KreateExtension.core(action: Action<CoreConfiguration>) {
    action.execute(core)
}

public interface KreateFeature {
    public fun apply(project: Project, extension: KreateExtension)
}