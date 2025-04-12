buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
} // Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.android.kotlin) apply false
    alias(libs.plugins.devtools.ksp) apply false

    alias(libs.plugins.android.hilt) apply false
    alias(libs.plugins.compose.plugin) apply false
    alias(libs.plugins.ktlint) apply false
    alias(libs.plugins.detekt) apply false
    alias(libs.plugins.android.junit5) apply false
}

// TODO: migrate this to allProjects once modularization is implemented
subprojects {
    apply(from = "../static_analysis/ktlint.gradle")
    apply(from = "../static_analysis/detekt.gradle")
}
