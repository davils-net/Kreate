/*
 * All rights reserved
 * Copyright 2024 Davils
 *
 * Unauthorized copying, distribution, or modification of this work is strictly prohibited.
 */

package net.davils.kreate

import net.davils.kreate.feature.buildconstants.BuildConstantsConfiguration
import net.davils.kreate.feature.cinterop.CInteropConfiguration
import net.davils.kreate.feature.core.CoreConfiguration
import net.davils.kreate.feature.docs.DocsConfiguration
import net.davils.kreate.feature.java.JavaConfiguration
import net.davils.kreate.feature.publish.PublishConfiguration
import net.davils.kreate.feature.testing.TestingConfiguration
import org.gradle.api.Action
import org.gradle.api.tasks.Nested

/**
 * Kreate extension to configure the entire plugin.
 *
 * @since 0.0.1
 * @author Nils Jäkel
 * */
public abstract class KreateExtension {
    /**
     * The default core configuration of the plugin.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    @get:Nested
    public abstract val core: CoreConfiguration

    /**
     * The default publishing configuration of the plugin.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    @get:Nested
    public abstract val publish: PublishConfiguration

    /**
     * The default build constants configuration of the plugin.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    @get:Nested
    public abstract val buildConstants: BuildConstantsConfiguration

    /**
     * The default cinterop configuration of the plugin.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    @get:Nested
    public abstract val cinterop: CInteropConfiguration

    /**
     * The default testing configuration of the plugin.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    @get:Nested
    public abstract val testing: TestingConfiguration

    /**
     * The default docs configuration of the plugin.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    @get:Nested
    public abstract val docs: DocsConfiguration

    /**
     * The default java configuration of the plugin.
     *
     * @since 0.0.3
     * @author Nils Jäkel
     * */
    @get:Nested
    public abstract val jv: JavaConfiguration

    /**
     * The default core configuration of the plugin.
     *
     * @param action The changes, that should be applied.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    public fun core(action: Action<CoreConfiguration>) {
        action.execute(core)
    }

    /**
     * The default publishing configuration of the plugin.
     *
     * @param action The changes, that should be applied.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    public fun publish(action: Action<PublishConfiguration>) {
        action.execute(publish)
    }

    /**
     * The default build constants configuration of the plugin.
     *
     * @param action The changes, that should be applied.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    public fun buildConstants(action: Action<BuildConstantsConfiguration>) {
        action.execute(buildConstants)
    }

    /**
     * The default cinterop configuration of the plugin.
     *
     * @param action The changes, that should be applied.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    public fun cinterop(action: Action<CInteropConfiguration>) {
        action.execute(cinterop)
    }

    /**
     * The default testing configuration of the plugin.
     *
     * @param action The changes, that should be applied.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    public fun testing(action: Action<TestingConfiguration>) {
        action.execute(testing)
    }

    /**
     * The default docs configuration of the plugin.
     *
     * @param action The changes, that should be applied.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    public fun docs(action: Action<DocsConfiguration>) {
        action.execute(docs)
    }

    /**
     * The default java configuration of the plugin.
     *
     * @param action The changes, that should be applied.
     *
     * @since 0.0.3
     * @author Nils Jäkel
     * */
    public fun jv(action: Action<JavaConfiguration>) {
        action.execute(jv)
    }
}
