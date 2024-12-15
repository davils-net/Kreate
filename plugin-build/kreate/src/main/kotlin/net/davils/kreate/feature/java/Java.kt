/*
 * All rights reserved
 * Copyright 2024 Davils
 *
 * Unauthorized copying, distribution, or modification of this work is strictly prohibited.
 */

package net.davils.kreate.feature.java

import net.davils.kreate.feature.feature
import net.davils.kreate.isMultiplatform
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

/**
 * Applies the java feature to the gradle project.
 *
 * @param project The current gradle project.
 * @param config The java configuration.
 *
 * @since 0.0.3
 * @author Nils Jäkel
 * */
internal fun java(project: Project, config: JavaConfiguration): Unit = project.feature(config) { _ ->
    val isMultiplatform = isMultiplatform(project)
    val javaVersion = config.javaVersion.get()
    val isSourcesJar = config.withSourcesJar.get()
    val isJavadocJar = config.withJavadocJar.get()

    if (isMultiplatform) {
        extensions.configure<KotlinMultiplatformExtension>("kotlin") {
            if (isSourcesJar) {
                withSourcesJar()
            }

            jvm {
                withJava()
                compilerOptions {
                    jvmTarget.set(JvmTarget.fromTarget(javaVersion.majorVersion))
                }
            }
        }
        return@feature
    }

    extensions.configure<JavaPluginExtension>("java") {
        if (isSourcesJar) {
            withSourcesJar()
        }

        if (isJavadocJar) {
            withJavadocJar()
        }

        sourceCompatibility = javaVersion
        targetCompatibility = javaVersion

        toolchain {
            languageVersion.set(JavaLanguageVersion.of(javaVersion.majorVersion))
        }
    }

    extensions.configure<KotlinJvmProjectExtension>("kotlin") {
        jvmToolchain(javaVersion.majorVersion.toInt())
    }
}
