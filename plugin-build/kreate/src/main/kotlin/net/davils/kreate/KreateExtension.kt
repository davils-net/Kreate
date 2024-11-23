package net.davils.kreate

import net.davils.kreate.buildconstants.BuildConstantsConfiguration
import net.davils.kreate.core.CoreConfiguration
import net.davils.kreate.publish.PublishConfiguration
import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.api.tasks.Nested

public abstract class KreateExtension {
    @get:Nested
    public abstract val core: CoreConfiguration
    @get:Nested
    public abstract val publishing: PublishConfiguration
    @get:Nested
    public abstract val buildConstants: BuildConstantsConfiguration

    public fun core(action: Action<CoreConfiguration>) {
        action.execute(core)
    }

    public fun publishing(action: Action<PublishConfiguration>) {
        action.execute(publishing)
    }

    public fun buildConstants(action: Action<BuildConstantsConfiguration>) {
        action.execute(buildConstants)
    }
}

public interface KreateFeature {
    public fun apply(project: Project, extension: KreateExtension)
}