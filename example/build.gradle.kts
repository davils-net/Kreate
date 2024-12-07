/*
 * All rights reserved
 * Copyright 2024 Davils
 *
 * Unauthorized copying, distribution, or modification of this work is strictly prohibited.
 */

import net.davils.kreate.feature.cinterop.Target
import net.davils.kreate.feature.core.License

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
        description = "An example project"
        license = License.ALL_RIGHTS_RESERVED
    }

    publish  {
        enabled = true
        inceptionYear = 2024
    }

    buildConstants {
        enabled = false
        buildPath = "generated/templates"
        sourceSets = kotlin.sourceSets.getByName("commonMain")
        onlyInternal = true
        properties = emptyMap<String, String>()
    }

    cinterop {
        enabled = true
        edition = "2021"
        initialCBindVersion = "0.27.0"
        initialLibCVersion = "0.2.164"

        targets(listOf(Target.LINUX))
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