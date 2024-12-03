package net.davils.kreate.feature.cinterop

import net.davils.kreate.KreateExtension
import net.davils.kreate.build.BuildConstants
import net.davils.kreate.feature.Task
import net.davils.kreate.utils.projectVersion
import org.gradle.api.Project
import org.gradle.api.tasks.TaskAction
import java.io.File

/**
 * Task to set up the rust project.
 *
 * @since 0.0.1
 * @author Nils Jäkel
 * */
public abstract class SetupRustProject : Task() {
    /**
     * The rust project.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    private val rustProject = rustProject(project, extension)

    @TaskAction
    override fun execute() {
        if (rustProject.file.resolve(rustProject.name).exists()) return

        val builder = ProcessBuilder("cargo", "new", rustProject.name, "--lib")
        builder.directory(rustProject.file)

        val process = builder.start()
        process.waitFor()
    }
}

/**
 * Task to configure cargo.
 *
 * @since 0.0.1
 * @author Nils Jäkel
 * */
public abstract class ConfigureCargo : Task() {
    /**
     * The rust project.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    private val rustProject = rustProject(project, extension)

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

        val cargoToml = rustProject.file.resolve(rustProject.name).resolve("Cargo.toml")
        cargoToml.writeText(cargoConfig)
    }
}

/**
 * Task to configure the rust build script.
 *
 * @since 0.0.1
 * @author Nils Jäkel
 * */
public abstract class ConfigureBuildScript : Task() {
    private val rustProject = rustProject(project, extension)

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

        val buildRs = rustProject.file.resolve(rustProject.name).resolve("build.rs")
        buildRs.writeText(buildLogic)
    }
}

/**
 * The rust project.
 *
 * @since 0.0.1
 * @author Nils Jäkel
 * */
internal data class RustProject(
    /**
     * The name of the rust project.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    val name: String,

    /**
     * The directory of the rust project.
     *
     * @since 0.0.1
     * @author Nils Jäkel
     * */
    val file: File
)

/**
 * Resolves the rust project.
 *
 * @since 0.0.1
 * @author Nils Jäkel
 * */
internal fun rustProject(project: Project, extension: KreateExtension): RustProject {
    val name = extension.core.name.get()
    val file = project.rootProject.projectDir.absoluteFile
    return RustProject("$name-rust".lowercase(), file)
}