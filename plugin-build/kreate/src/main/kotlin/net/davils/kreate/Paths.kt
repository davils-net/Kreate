/*
 * All rights reserved
 * Copyright 2024 Davils
 *
 * Unauthorized copying, distribution, or modification of this work is strictly prohibited.
 */

package net.davils.kreate

import org.gradle.api.Project
import java.io.File

/**
 * Represents the paths for the kreate project.
 *
 * @param project The gradle current gradle project.
 *
 * @since 0.0.1
 * @author Nils Jäkel
 * */
public class Paths(project: Project) {
    /**
     * The kreate extension.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    private val extension = project.kreateExtension

    /**
     * The root project.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    private val rootProject: Project = project.rootProject

    /**
     * Gets the gitignore file.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    public val gitignore: File = rootProject.file(".gitignore")

    /**
     * Gets the license file.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    public val license: File = rootProject.file("LICENSE")

    /**
     * The root project directory.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    public val rootProjectDir: File = rootProject.projectDir

    /**
     * The cinterop directory.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    public val cinteropDir: File = rootProject.file("cinterop")

    /**
     * The cinterop definition file in the cinterop directory.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    public val cinteropFile: File = cinteropDir.resolve("cinterop.def")

    /**
     * The rust project directory.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    public val rustDir: File = rootProject.file("${extension.core.name.get().lowercase()}-rust")

    /**
     * The rust include directory where the header files are stored.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    public val includeDir: File = rustDir.resolve("include")

    /**
     * The cargo.toml file.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    public val cargoToml: File = rustDir.resolve("Cargo.toml")

    /**
     * The build.rs file.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    public val buildRs: File = rustDir.resolve("build.rs")

    /**
     * The rust release directory.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    public val rustRelease: File = rustDir.resolve("target/release")
}
