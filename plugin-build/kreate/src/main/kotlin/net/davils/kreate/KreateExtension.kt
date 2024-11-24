package net.davils.kreate

import net.davils.kreate.feature.buildconstants.DefaultBuildConstantsConfiguration
import net.davils.kreate.feature.cinterop.DefaultCinteropConfiguration
import net.davils.kreate.feature.core.DefaultCoreConfiguration
import net.davils.kreate.feature.docs.DefaultDocsConfiguration
import net.davils.kreate.feature.publish.DefaultPublishConfiguration
import net.davils.kreate.feature.testing.DefaultTestingConfiguration
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
    @get:Nested
    public abstract val testing: DefaultTestingConfiguration
    @get:Nested
    public abstract val docs: DefaultDocsConfiguration

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

    public fun testing(action: Action<DefaultTestingConfiguration>) {
        action.execute(testing)
    }

    public fun docs(action: Action<DefaultDocsConfiguration>) {
        action.execute(docs)
    }
}
