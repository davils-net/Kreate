/*
 * All rights reserved
 * Copyright 2024 Davils
 *
 * Unauthorized copying, distribution, or modification of this work is strictly prohibited.
 */

package net.davils.kreate

import net.davils.kreate.feature.buildconstants.DefaultBuildConstantsConfiguration
import net.davils.kreate.feature.cinterop.DefaultCInteropConfiguration
import net.davils.kreate.feature.core.DefaultCoreConfiguration
import net.davils.kreate.feature.docs.DefaultDocsConfiguration
import net.davils.kreate.feature.publish.DefaultPublishConfiguration
import net.davils.kreate.feature.testing.DefaultTestingConfiguration
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
     * The core configuration of the plugin.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    @get:Nested
    public abstract val core: DefaultCoreConfiguration

    /**
     * The publishing configuration of the plugin.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    @get:Nested
    public abstract val publish: DefaultPublishConfiguration

    /**
     * The build constants configuration of the plugin.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    @get:Nested
    public abstract val buildConstants: DefaultBuildConstantsConfiguration

    /**
     * The cinterop configuration of the plugin.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    @get:Nested
    public abstract val cinterop: DefaultCInteropConfiguration

    /**
     * The testing configuration of the plugin.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    @get:Nested
    public abstract val testing: DefaultTestingConfiguration

    /**
     * The docs configuration of the plugin.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    @get:Nested
    public abstract val docs: DefaultDocsConfiguration

    /**
     * The core configuration of the plugin.
     *
     * @param action The changes, that would be applied.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    public fun core(action: Action<DefaultCoreConfiguration>) {
        action.execute(core)
    }

    /**
     * The publishing configuration of the plugin.
     *
     * @param action The changes, that would be applied.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    public fun publish(action: Action<DefaultPublishConfiguration>) {
        action.execute(publish)
    }

    /**
     * The build constants configuration of the plugin.
     *
     * @param action The changes, that would be applied.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    public fun buildConstants(action: Action<DefaultBuildConstantsConfiguration>) {
        action.execute(buildConstants)
    }

    /**
     * The cinterop configuration of the plugin.
     *
     * @param action The changes, that would be applied.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    public fun cinterop(action: Action<DefaultCInteropConfiguration>) {
        action.execute(cinterop)
    }

    /**
     * The testing configuration of the plugin.
     *
     * @param action The changes, that would be applied.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    public fun testing(action: Action<DefaultTestingConfiguration>) {
        action.execute(testing)
    }

    /**
     * The docs configuration of the plugin.
     *
     * @param action The changes, that would be applied.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    public fun docs(action: Action<DefaultDocsConfiguration>) {
        action.execute(docs)
    }
}
