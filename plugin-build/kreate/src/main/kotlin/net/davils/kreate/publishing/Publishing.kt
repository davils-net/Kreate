package net.davils.kreate.publishing

import net.davils.kreate.KreateExtension
import net.davils.kreate.KreateFeature
import org.gradle.api.Project

public object Publishing : KreateFeature {
    override fun apply(project: Project, extension: KreateExtension) {
        val isPublishingEnabled = extension.publishing.enabled.orElse(false).get()
        if (!isPublishingEnabled) {
            return
        }


    }
}