package net.davils.kreate.core

import org.gradle.api.provider.Property
import java.io.File

public interface CoreConfiguration {
    public val name: Property<String>
    public val description: Property<String>
    public val license: Property<License>

    public fun patchVersionsInFiles(vararg files: File) {
        files.forEach { file ->
            patchVersionsInFile(file)
        }
    }

    public fun patchVersionsInFile(file: File) {

    }
}
