package net.davils.kreate

import net.davils.kreate.feature.buildconstants.BuildConstantsConfiguration
import net.davils.kreate.feature.core.CoreConfiguration
import net.davils.kreate.feature.cinterop.CinteropConfiguration
import net.davils.kreate.feature.publish.PublishConfiguration
import org.gradle.api.Action
import org.gradle.api.tasks.Nested

public abstract class KreateExtension {
    @get:Nested
    public abstract val core: CoreConfiguration
    @get:Nested
    public abstract val publishing: PublishConfiguration
    @get:Nested
    public abstract val buildConstants: BuildConstantsConfiguration
    @get:Nested
    public abstract val cinterop: CinteropConfiguration

    public fun core(action: Action<CoreConfiguration>) {
        action.execute(core)
    }

    public fun publishing(action: Action<PublishConfiguration>) {
        action.execute(publishing)
    }

    public fun buildConstants(action: Action<BuildConstantsConfiguration>) {
        action.execute(buildConstants)
    }

    public fun cinterop(action: Action<CinteropConfiguration>) {
        action.execute(cinterop)
    }
}
