/*
 * All rights reserved
 * Copyright 2024 Davils
 *
 * Unauthorized copying, distribution, or modification of this work is strictly prohibited.
 */

package net.davils.kreate.feature.cinterop

import net.davils.kreate.feature.Task
import org.gradle.api.tasks.TaskAction
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTargetWithHostTests
import org.jetbrains.kotlin.konan.target.KonanTarget
import java.nio.file.Path

public abstract class ApplyNativeTargets : Task() {
    private val projectTargets = extension.cinterop.targets.get()
    private val path = project.rootProject.file("cinterop").resolve("cinterop.def").toPath()
    private val group = project.group.toString()

    @TaskAction
    override fun execute() {
        project.extensions.configure<KotlinMultiplatformExtension>("kotlin") {
        projectTargets.forEach { target ->
            target.target.forEach { nativeTarget ->
                when (nativeTarget) {
                    KonanTarget.MINGW_X64 -> mingwX64 { applyCInterop(path, group) }
                    KonanTarget.LINUX_X64 -> linuxX64 { applyCInterop(path, group) }
                    KonanTarget.MACOS_X64 -> macosX64 { applyCInterop(path, group) }
                    KonanTarget.MACOS_ARM64 -> macosArm64 { applyCInterop(path, group) }
                    KonanTarget.IOS_ARM64 -> iosArm64 { applyCInterop(path, group) }
                    KonanTarget.IOS_X64 -> iosX64 { applyCInterop(path, group) }
                    KonanTarget.IOS_SIMULATOR_ARM64 -> iosSimulatorArm64 { applyCInterop(path, group) }
                    KonanTarget.WATCHOS_ARM32 -> watchosArm32 { applyCInterop(path, group) }
                    KonanTarget.WATCHOS_ARM64 -> watchosArm64 { applyCInterop(path, group) }
                    KonanTarget.WATCHOS_X64 -> watchosX64 { applyCInterop(path, group) }
                    KonanTarget.WATCHOS_SIMULATOR_ARM64 -> watchosSimulatorArm64 { applyCInterop(path, group) }
                    KonanTarget.TVOS_ARM64 -> tvosArm64 { applyCInterop(path, group) }
                    KonanTarget.TVOS_X64 -> tvosX64 { applyCInterop(path, group) }
                    KonanTarget.TVOS_SIMULATOR_ARM64 -> tvosSimulatorArm64 { applyCInterop(path, group) }
                    else -> throw IllegalStateException("Unknown target: $target")
                }
            }
        }
    }
    }
}

public enum class Target(public val target: MutableList<KonanTarget>) {
    WINDOWS(mutableListOf(KonanTarget.MINGW_X64)),
    LINUX(mutableListOf(KonanTarget.LINUX_X64)),
    MACOS(mutableListOf(KonanTarget.MACOS_X64, KonanTarget.MACOS_ARM64)),
    IOS(mutableListOf(KonanTarget.IOS_ARM64, KonanTarget.IOS_X64, KonanTarget.IOS_SIMULATOR_ARM64)),
    WATCHOS(
        mutableListOf(
            KonanTarget.WATCHOS_ARM32,
            KonanTarget.WATCHOS_ARM64,
            KonanTarget.WATCHOS_X64,
            KonanTarget.WATCHOS_SIMULATOR_ARM64
        )
    ),
    TVOS(mutableListOf(KonanTarget.TVOS_ARM64, KonanTarget.TVOS_X64, KonanTarget.TVOS_SIMULATOR_ARM64));
}

internal fun KotlinNativeTargetWithHostTests.applyCInterop(path: Path, group: String) {
    compilations.getByName("main") {
        cinterops {
            create("native") {
                packageName = "$group.cinterop"
                defFile(path)
            }
        }
    }
}

internal fun KotlinNativeTarget.applyCInterop(path: Path, group: String) {
    compilations.getByName("main") {
        cinterops {
            create("native") {
                packageName = "$group.cinterop"
                defFile(path)
            }
        }
    }
}
