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

    private val name = extension.core.name.get().lowercase()
    private val description = extension.core.description.get()
    private val license = extension.core.license.get()
    private val edition = extension.cinterop.edition.get()
    private val cBindVersion = extension.cinterop.cBindVersion.get()
    private val libCVersion = extension.cinterop.libCVersion.get()

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

        initializeCargo()
        initializeBuildScript()
    }

    private fun initializeCargo() {
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

    private fun initializeBuildScript() {
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
