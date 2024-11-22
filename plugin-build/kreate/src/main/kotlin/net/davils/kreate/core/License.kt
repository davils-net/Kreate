package net.davils.kreate.core

import net.davils.kreate.build.BuildConstants
import org.gradle.api.Project

public enum class License(public val value: String, public val text: String) {
    MIT("MIT", mit),
    ALL_RIGHTS_RESERVED("All rights reserved", allRightsReserved);

    public companion object {
        public fun byName(name: String): License? = values().firstOrNull { it.value == name }
    }
}

internal fun generateLicense(project: Project, license: License) {
    val licenseFile = project.rootProject.file("LICENSE")
    if (licenseFile.exists() && licenseFile.readText().isNotEmpty()) return

    if (!licenseFile.exists()) {
        licenseFile.createNewFile()
    }

    licenseFile.writeText(license.text)
}

internal const val HEADER = "Copyright © 2024 ${BuildConstants.ORGANIZATION_NAME}"

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

internal val allRightsReserved: String = """
    All rights reserved
    $HEADER
    
    Unauthorized copying, distribution, or modification of this work is strictly prohibited.
""".trimIndent()
