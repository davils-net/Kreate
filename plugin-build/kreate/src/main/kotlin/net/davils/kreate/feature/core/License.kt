/*
 * All rights reserved
 * Copyright 2024 Davils
 *
 * Unauthorized copying, distribution, or modification of this work is strictly prohibited.
 */

package net.davils.kreate.feature.core

import net.davils.kreate.Paths
import net.davils.kreate.build.BuildConstants
import net.davils.kreate.feature.Task
import net.davils.kreate.feature.isFeatureEnabled
import org.gradle.api.tasks.TaskAction

/**
 * Contains all available licenses for davils projects.
 *
 * @since 0.0.1
 * @author Nils Jäkel
 * */
public enum class License(
    /**
     * The name of the license.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    public val value: String,

    /**
     * The copyright text of the license.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    public val text: String
) {
    MIT("MIT", mit),
    ALL_RIGHTS_RESERVED("All rights reserved", allRightsReserved);

    public companion object {
        /**
         * Gets a license by its name.
         *
         * @param name The name of the license.
         * @return If a [License] is found, it will be returned, otherwise `null`.
         *
         * @since 0.0.1
         * @author Nils Jäkel
         * */
        public fun byName(name: String): License? = values().firstOrNull { it.value == name }
    }
}

/**
 * Represents the task to generate the license if it does not exist.
 *
 * @since 0.0.1
 * @author Nils Jäkel
 * */
public abstract class GenerateLicense : Task() {
    /**
     * The configured license.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    private val license = extension.core.license.get()

    /**
     * The path handler.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    private val paths = Paths(project)

    /**
     * The task action. It generates the license if it does not exist and
     * writes the configured license to it.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    @TaskAction
    override fun execute() {
        if (paths.license.exists() && paths.license.readText().isNotEmpty()) return

        if (!paths.license.exists()) {
            paths.license.createNewFile()
        }

        paths.license.writeText(license.text)
    }
}

/**
 * The header for the license files.
 *
 * @since 0.0.1
 * @author Nils Jäkel
 * */
internal const val HEADER = "Copyright 2024 ${BuildConstants.ORGANIZATION_NAME}"

/**
 * The copyright text for the `MIT` license.
 *
 * @since 0.0.1
 * @author Nils Jäkel
 * */
internal val mit = """
    MIT License
    $HEADER
    
    Permission is hereby granted, free of charge, to any person obtaining
    a copy of this software and associated documentation files (the “Software”),
    to deal in the Software without restriction, including without limitation
    the rights to use, copy, modify, merge, publish, distribute, sublicense,
    and/or sell copies of the Software, and to permit persons to whom the Software
    is furnished to do so, subject to the following conditions:
    
    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.
    
    THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
    FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
    COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN
    AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH
    THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
""".trimIndent()

/**
 * The copyright text for the `All rights reserved` license.
 *
 * @since 0.0.1
 * @author Nils Jäkel
 * */
internal val allRightsReserved: String = """
    All rights reserved
    $HEADER
    
    Unauthorized copying, distribution, or modification of this work is strictly prohibited.
""".trimIndent()
