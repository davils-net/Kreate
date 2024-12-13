/*
 * All rights reserved
 * Copyright 2024 Davils
 *
 * Unauthorized copying, distribution, or modification of this work is strictly prohibited.
 */

import net.davils.kreate.feature.cinterop.CompileRust
import net.davils.kreate.feature.cinterop.ExcludeSourcesInGit
import net.davils.kreate.feature.cinterop.GenerateDefinitionFile
import net.davils.kreate.feature.cinterop.SetupRustProject
import net.davils.kreate.feature.cinterop.Target
import net.davils.kreate.feature.core.Entry
import net.davils.kreate.feature.core.GenerateLicense
import net.davils.kreate.feature.core.License
import net.davils.kreate.feature.core.FilePatch
import net.davils.kreate.projectVersion
import org.gradle.kotlin.dsl.kotlin

plugins {
    alias(deps.plugins.kreate)
    alias(deps.plugins.kotlin.multiplatform)
}

repositories {
    mavenCentral()
}

kreate {
    core {
        enabled = true
        name = "example"
        description = "An example project."
        license = License.ALL_RIGHTS_RESERVED
        patchEntries = listOf(
            Entry(
                file = rootProject.projectDir.resolve("docs/writerside.cfg"),
                regex = Regex("[0-9]+\\.[0-9]+\\.[0-9]+"),
                newContent = projectVersion
            )
        )
        isExplicitApiMode = true
    }

    publish  {
        enabled = false
        inceptionYear = 2024
    }

    buildConstants {
        enabled = true
        buildPath = "generated/templates"
        sourceSets = kotlin.sourceSets.getByName("commonMain")
        onlyInternal = true
        properties = emptyMap<String, String>()
    }

    cinterop {
        enabled = true
        edition = "2021"
        cBindVersion = "0.27.0"
        libCVersion = "0.2.168"
        applyTargetsWithoutRust = false

        targets(Target.LINUX)
    }

    testing {
        enabled = true
        createTestReport = true
    }

    docs {
        enabled = true
        isMultiModuleMode = true
    }
}

tasks {
    withType<GenerateLicense> {  }
    withType<FilePatch> {  }

    withType<SetupRustProject> {  }
    withType<ExcludeSourcesInGit> {  }
    withType<GenerateDefinitionFile> {  }
    withType<CompileRust> {  }
}