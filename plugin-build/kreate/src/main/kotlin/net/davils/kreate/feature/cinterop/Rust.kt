package net.davils.kreate.feature.cinterop

import net.davils.kreate.KreateExtension
import net.davils.kreate.build.BuildConstants
import net.davils.kreate.utils.projectVersion
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.gradle.kotlin.dsl.getByType

public abstract class SetupRustProject : DefaultTask() {
    private val extension = project.extensions.getByType<KreateExtension>()
    private val rustConf = rustProject(project)

    @TaskAction
    internal fun generate() {
        if (rustConf.second.resolve(rustConf.first).exists()) {
            return
        }

        val builder = ProcessBuilder("cargo", "new", rustConf.first, "--lib")
        builder.directory(rustConf.second)

        val process = builder.start()
        process.waitFor()
        configureCargo()
        configureBuildScript()
    }

    private fun configureBuildScript() {
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

        val buildRs = rustConf.second.resolve(rustConf.first).resolve("build.rs")
        buildRs.writeText(buildLogic)
    }

    private fun configureCargo() {
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

        val cargoToml = rustConf.second.resolve(rustConf.first).resolve("Cargo.toml")
        cargoToml.writeText(cargoConfig)
    }
}
