package net.davils.kreate

import net.davils.kreate.core.CoreConfiguration
import net.davils.kreate.publishing.PublishingConfiguration
import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.api.tasks.Nested

public abstract class KreateExtension {
    @get:Nested
    public abstract val core: CoreConfiguration
    @get:Nested
    public abstract val publishing: PublishingConfiguration

    public fun core(action: Action<CoreConfiguration>) {
        action.execute(core)
    }

    public fun publishing(action: Action<PublishingConfiguration>) {
        action.execute(publishing)
    }
}

public interface KreateFeature {
    public fun apply(project: Project, extension: KreateExtension)
}