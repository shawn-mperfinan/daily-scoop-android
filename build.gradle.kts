buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
} // Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    id("com.android.application") version "8.1.3" apply false
    id("com.android.library") version "8.1.3" apply false
    id("org.jetbrains.kotlin.android") version "1.9.10" apply false
    id("com.google.devtools.ksp") version "1.9.10-1.0.13" apply false

    id("com.google.dagger.hilt.android") version "2.48" apply false
    id("org.jlleitschuh.gradle.ktlint") version "12.1.0" apply false
    id("io.gitlab.arturbosch.detekt") version "1.23.4" apply false
    id("de.mannodermaus.android-junit5") version "1.9.3.0" apply false
}

// TODO: migrate this to allProjects once modularization is implemented
subprojects {
    apply(from = "../static_analysis/ktlint.gradle")
    apply(from = "../static_analysis/detekt.gradle")
}
