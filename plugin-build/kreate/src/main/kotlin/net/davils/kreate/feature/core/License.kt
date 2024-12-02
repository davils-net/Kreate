package net.davils.kreate.feature.core

import net.davils.kreate.build.BuildConstants
import net.davils.kreate.feature.Task
import org.gradle.api.tasks.TaskAction

/**
 * Available licenses for davils projects.
 *
 * @since 0.0.1
 * @author Nils Jäkel
 * */
public enum class License(public val value: String, public val text: String) {
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

public abstract class GenerateLicense : Task() {
    private val license = extension.core.license.get()

    @TaskAction
    override fun execute() {
        val licenseFile = project.rootProject.file("LICENSE")
        if (licenseFile.exists() && licenseFile.readText().isNotEmpty()) return

        if (!licenseFile.exists()) {
            licenseFile.createNewFile()
        }

        licenseFile.writeText(license.text)
    }
}

/**
 * The header for license files.
 *
 * @since 0.0.1
 * @author Nils Jäkel
 * */
internal const val HEADER = "Copyright 2024 ${BuildConstants.ORGANIZATION_NAME}"

/**
 * The text for the MIT license.
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
 * The text for the `All rights reserved` license.
 *
 * @since 0.0.1
 * @author Nils Jäkel
 * */
internal val allRightsReserved: String = """
    All rights reserved
    $HEADER
    
    Unauthorized copying, distribution, or modification of this work is strictly prohibited.
""".trimIndent()
