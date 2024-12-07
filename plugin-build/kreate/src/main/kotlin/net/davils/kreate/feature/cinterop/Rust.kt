/*
 * All rights reserved
 * Copyright 2024 Davils
 *
 * Unauthorized copying, distribution, or modification of this work is strictly prohibited.
 */

package net.davils.kreate.feature.cinterop

import net.davils.kreate.build.BuildConstants
import net.davils.kreate.feature.Task
import net.davils.kreate.Paths
import net.davils.kreate.projectVersion
import org.gradle.api.tasks.TaskAction

/**
 * Represents the task to set up the rust project.
 *
 * @since 0.0.1
 * @author Nils Jäkel
 * */
public abstract class SetupRustProject : Task() {
    /**
     * The path handler.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    private val paths = Paths(project)

    /**
     * The task action.
     * It sets up the rust project.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    @TaskAction
    override fun execute() {
        if (paths.rustDir.exists()) return

        val builder = ProcessBuilder("cargo", "new", paths.rustDir.name, "--lib")
        builder.directory(paths.rootProjectDir)

        val process = builder.start()
        process.waitFor()
    }
}

/**
 * Represents the task to configure Cargo.
 *
 * @since 0.0.1
 * @author Nils Jäkel
 * */
public abstract class ConfigureCargo : Task() {
    /**
     * The path handler.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    private val paths = Paths(project)

    /**
     * The task action.
     * It configures the Cargo.toml file.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    @TaskAction
    override fun execute() {
        val name = extension.core.name.get().lowercase()
        val description = extension.core.description.get()
        val license = extension.core.license.get()
        val edition = extension.cinterop.edition.get()
        val cBindVersion = extension.cinterop.initialCBindVersion.get()
        val libCVersion = extension.cinterop.initialLibCVersion.get()

        val cargoConfig = """
             [package]
             name = "$name"
             description = "$description"
             authors = ["${BuildConstants.ORGANIZATION_NAME} <${BuildConstants.ORGANISATION_EMAIL}>"]
             license = "${license.value}"
             version = "$projectVersion"
             edition = "$edition"

             [lib]
             crate-type = ["staticlib"]

             [build-dependencies]
             cbindgen = "$cBindVersion"
              
             [dependencies]
             libc = "$libCVersion"
        """.trimIndent()
        paths.cargoToml.writeText(cargoConfig)
    }
}

/**
 * Represents the task to configure the build script.
 *
 * @since 0.0.1
 * @author Nils Jäkel
 * */
public abstract class ConfigureBuildScript : Task() {
    /**
     * The path handler.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    private val paths = Paths(project)

    /**
     * The task action.
     * It configures the build.rs file.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    @TaskAction
    override fun execute() {
        val name = extension.core.name.get().lowercase()

        val buildLogic = """
             extern crate cbindgen;

             use std::env;
             use cbindgen::Language::C;

             fn main() {
                 let crate_dir = env::var("CARGO_MANIFEST_DIR").unwrap();

                 cbindgen::Builder::new()
                     .with_crate(crate_dir)
                     .with_language(C)
                     .generate()
                     .expect("Unable to generate bindings")
                     .write_to_file("include/$name.h");
             }
        """.trimIndent()
        paths.buildRs.writeText(buildLogic)
    }
}
