package net.davils

import org.gradle.api.Plugin
import org.gradle.api.Project

class Kreate : Plugin<Project> {
    override fun apply(target: Project) {
        println("Applied")
    }
}