/*
 * All rights reserved
 * Copyright 2024 Davils
 *
 * Unauthorized copying, distribution, or modification of this work is strictly prohibited.
 */

package net.davils.kreate.feature.cinterop

import net.davils.kreate.KreateExtension
import net.davils.kreate.Paths
import net.davils.kreate.build.BuildConstants
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTargetWithHostTests
import org.jetbrains.kotlin.konan.target.KonanTarget
import java.nio.file.Path

/**
 * Applies the native targets to the gradle project.
 *
 * @param project The gradle current gradle project.
 * @param extension The kreate extension.
 *
 * @since 0.0.1
 * @author Nils Jäkel
 * */
internal fun applyNativeTargets(project: Project, config: CInteropConfiguration) {
    val paths = Paths(project)
    if (!paths.cinteropFile.exists()) {
        return
    }

    val projectTargets = config.targets.get()
    val cinteropFile = paths.cinteropFile.toPath()
    val isWithoutCinterop = config.applyTargetsWithoutCInterop.get()

    project.extensions.configure<KotlinMultiplatformExtension>("kotlin") {
        projectTargets.forEach { target ->
            target.target.forEach { nativeTarget ->
                when (nativeTarget) {
                    KonanTarget.MINGW_X64 -> mingwX64 {
                        if (!isWithoutCinterop) {
                            applyCInterop(cinteropFile)
                        }
                    }

                    KonanTarget.LINUX_X64 -> linuxX64 {
                        if (!isWithoutCinterop) {
                            applyCInterop(cinteropFile)
                        }
                    }

                    KonanTarget.MACOS_X64 -> macosX64 {
                        if (!isWithoutCinterop) {
                            applyCInterop(cinteropFile)
                        }
                    }

                    KonanTarget.MACOS_ARM64 -> macosArm64 {
                        if (!isWithoutCinterop) {
                            applyCInterop(cinteropFile)
                        }
                    }

                    KonanTarget.IOS_ARM64 -> iosArm64 {
                        if (!isWithoutCinterop) {
                            applyCInterop(cinteropFile)
                        }
                    }

                    KonanTarget.IOS_X64 -> iosX64 {
                        if (!isWithoutCinterop) {
                            applyCInterop(cinteropFile)
                        }
                    }

                    KonanTarget.IOS_SIMULATOR_ARM64 -> iosSimulatorArm64 {
                        if (!isWithoutCinterop) {
                            applyCInterop(cinteropFile)
                        }
                    }

                    KonanTarget.WATCHOS_ARM32 -> watchosArm32 {
                        if (!isWithoutCinterop) {
                            applyCInterop(cinteropFile)
                        }
                    }

                    KonanTarget.WATCHOS_ARM64 -> watchosArm64 {
                        if (!isWithoutCinterop) {
                            applyCInterop(cinteropFile)
                        }
                    }

                    KonanTarget.WATCHOS_X64 -> watchosX64 {
                        if (!isWithoutCinterop) {
                            applyCInterop(cinteropFile)
                        }
                    }

                    KonanTarget.WATCHOS_SIMULATOR_ARM64 -> watchosSimulatorArm64 {
                        if (!isWithoutCinterop) {
                            applyCInterop(cinteropFile)
                        }
                    }

                    KonanTarget.TVOS_ARM64 -> tvosArm64 {
                        if (!isWithoutCinterop) {
                            applyCInterop(cinteropFile)
                        }
                    }

                    KonanTarget.TVOS_X64 -> tvosX64 {
                        if (!isWithoutCinterop) {
                            applyCInterop(cinteropFile)
                        }
                    }

                    KonanTarget.TVOS_SIMULATOR_ARM64 -> tvosSimulatorArm64 {
                        if (!isWithoutCinterop) {
                            applyCInterop(cinteropFile)
                        }
                    }

                    else -> throw IllegalStateException("Unknown target: $target")
                }
            }
        }
    }
}

/**
 * The available targets for the native cinterop.
 *
 * @since 0.0.1
 * @author Nils Jäkel
 * */
public enum class Target(
    /**
     * A [List] of [KonanTarget] that should be applied to the target.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     *
     * @see KonanTarget
     * */
    public val target: MutableList<KonanTarget>,
) {
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

/**
 * Applies the cinterop to the specific native target.
 *
 * @param path The path to the cinterop file.
 *
 * @since 0.0.1
 * @author Nils Jäkel
 * */
internal fun KotlinNativeTargetWithHostTests.applyCInterop(path: Path) {
    compilations.getByName("main") {
        cinterops {
            create("native") {
                packageName = "${BuildConstants.GROUP}.cinterop"
                defFile(path)
            }
        }
    }
}

/**
 * Applies the cinterop to the specific native target.
 *
 * @param path The path to the cinterop file.
 *
 * @since 0.0.1
 * @author Nils Jäkel
 * */
internal fun KotlinNativeTarget.applyCInterop(path: Path) {
    compilations.getByName("main") {
        cinterops {
            create("native") {
                packageName = "${BuildConstants.GROUP}.cinterop"
                defFile(path)
            }
        }
    }
}
