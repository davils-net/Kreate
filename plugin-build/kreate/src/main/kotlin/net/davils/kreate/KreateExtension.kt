package net.davils.kreate

import net.davils.kreate.feature.buildconstants.DefaultBuildConstantsConfiguration
import net.davils.kreate.feature.cinterop.DefaultCinteropConfiguration
import net.davils.kreate.feature.core.DefaultCoreConfiguration
import net.davils.kreate.feature.publish.DefaultPublishConfiguration
import org.gradle.api.Action
import org.gradle.api.tasks.Nested

public abstract class KreateExtension {
    @get:Nested
    public abstract val core: DefaultCoreConfiguration
    @get:Nested
    public abstract val publish: DefaultPublishConfiguration
    @get:Nested
    public abstract val buildConstants: DefaultBuildConstantsConfiguration
    @get:Nested
    public abstract val cinterop: DefaultCinteropConfiguration

    public fun core(action: Action<DefaultCoreConfiguration>) {
        action.execute(core)
    }

    public fun publish(action: Action<DefaultPublishConfiguration>) {
        action.execute(publish)
    }

    public fun buildConstants(action: Action<DefaultBuildConstantsConfiguration>) {
        action.execute(buildConstants)
    }

    public fun cinterop(action: Action<DefaultCinteropConfiguration>) {
        action.execute(cinterop)
    }
}
