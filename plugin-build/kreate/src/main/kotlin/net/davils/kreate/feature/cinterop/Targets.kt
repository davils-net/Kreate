/*
 * All rights reserved
 * Copyright 2024 Davils
 *
 * Unauthorized copying, distribution, or modification of this work is strictly prohibited.
 */

package net.davils.kreate.feature.cinterop

import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.konan.target.KonanTarget

public enum class Target(public val target: MutableList<KonanTarget>) {
    WINDOWS(mutableListOf(KonanTarget.MINGW_X64)),
    LINUX(mutableListOf(KonanTarget.LINUX_X64)),
    MACOS(mutableListOf(KonanTarget.MACOS_X64, KonanTarget.MACOS_ARM64)),
    IOS(mutableListOf(KonanTarget.IOS_ARM64, KonanTarget.IOS_X64, KonanTarget.IOS_SIMULATOR_ARM64)),
    WATCHOS(mutableListOf(KonanTarget.WATCHOS_ARM32, KonanTarget.WATCHOS_ARM64, KonanTarget.WATCHOS_X64, KonanTarget.WATCHOS_SIMULATOR_ARM64)),
    TVOS(mutableListOf(KonanTarget.TVOS_ARM64, KonanTarget.TVOS_X64, KonanTarget.TVOS_SIMULATOR_ARM64));
}

public fun applyNativeTargets(project: Project, targets: List<Target>) {
    project.extensions.configure<KotlinMultiplatformExtension>("kotlin") {
        targets.forEach { target ->
            target.target.forEach { nativeTarget ->
                when (nativeTarget) {
                    KonanTarget.MINGW_X64 -> mingwX64()
                    KonanTarget.LINUX_X64 -> linuxX64()
                    KonanTarget.MACOS_X64 -> macosX64()
                    KonanTarget.MACOS_ARM64 -> macosArm64()
                    KonanTarget.IOS_ARM64 -> iosArm64()
                    KonanTarget.IOS_X64 -> iosX64()
                    KonanTarget.IOS_SIMULATOR_ARM64 -> iosSimulatorArm64()
                    KonanTarget.WATCHOS_ARM32 -> watchosArm32()
                    KonanTarget.WATCHOS_ARM64 -> watchosArm64()
                    KonanTarget.WATCHOS_X64 -> watchosX64()
                    KonanTarget.WATCHOS_SIMULATOR_ARM64 -> watchosSimulatorArm64()
                    KonanTarget.TVOS_ARM64 -> tvosArm64()
                    KonanTarget.TVOS_X64 -> tvosX64()
                    KonanTarget.TVOS_SIMULATOR_ARM64 -> tvosSimulatorArm64()
                    else -> throw IllegalStateException("Unknown target: $target")
                }
            }
        }
    }
}