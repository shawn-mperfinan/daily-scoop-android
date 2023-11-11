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
    id("org.jlleitschuh.gradle.ktlint") version "11.4.0" apply false
    id("io.gitlab.arturbosch.detekt") version "1.23.0" apply false
    id("de.mannodermaus.android-junit5") version "1.9.3.0" apply false
}

// TODO: migrate this to allProjects once modularization is implemented
subprojects {
    apply(from = "../buildscripts/ktlint.gradle")
    apply(from = "../buildscripts/detekt.gradle")
}

/**
 * This is to inject pre-commit hooks script in .git/hooks and runs the script that will enforces it first to have a
 * passed result before allowing git from pushing any changes to the remote repository.
 *
 * To make use of this, create [pre-commit.sh] bash file under buildscripts directory then copy its content within.
 *
 * Also, said file above is ignored by git to avoid pushing changes as this is only intended to be utilized locally.
 */
tasks.register("addPreCommitGitHookOnBuild") {
    println("⚈ ⚈ ⚈ Running Add Pre Commit Git Hook Script on Build ⚈ ⚈ ⚈")
    exec {
        commandLine("cp", "../buildscripts/pre-commit.sh", "./.git/hooks")
    }
    println("✅ Added Git Pre-Commit Hook Script.")
}
