import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.android.kotlin)
    alias(libs.plugins.devtools.ksp)
    alias(libs.plugins.android.hilt)
    alias(libs.plugins.android.junit5)
    alias(libs.plugins.compose.plugin)
    alias(libs.plugins.kotlin.serialization)
}

// Initialize for property retrieval on variables defined in [local.properties]
val localProperties = Properties()
localProperties.load(project.rootProject.file("local.properties").inputStream())

// Calculates and get the versionCode based on commit counts
fun getVersionCode(): Int {
    // Run the Git command and get the commit count result as an integer
    val commitCount =
        providers.exec {
            commandLine("git", "rev-list", "--no-merges", "--count", "HEAD")
        }.standardOutput.asText.get().trim().toInt()

    // kindly refer to the current value of versionName based on its MAJOR, MINOR and PATCH value then append 000
    // current value --> versionName = 1.0.0
    val semanticVersionCombination = 100000

    return commitCount + semanticVersionCombination
}

android {

    namespace = "com.dailyscoop.app"

    compileSdk = 35

    defaultConfig {

        applicationId = "com.dailyscoop.app"

        minSdk = 31

        targetSdk = 35

        versionName = "1.0.0"

        versionCode = getVersionCode()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunnerArguments["runnerBuilder"] = "de.mannodermaus.junit5.AndroidJUnit5Builder"

        vectorDrawables.useSupportLibrary = true

        ksp {
            /**
             * The schemas directory contains a schema file for each version of the Room database.
             * This is required to enable Room auto migrations.
             * See https://developer.android.com/reference/kotlin/androidx/room/AutoMigration.
             */
            arg("room.schemaLocation", "$projectDir/schemas")

            /**
             * Enables Gradle incremental annotation processor.
             */
            arg("room.incremental", "true")
        }
    }

    java {
        toolchain {
            languageVersion = JavaLanguageVersion.of(JavaVersion.VERSION_21.toString())
        }
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    lint {
        checkReleaseBuilds = false
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    signingConfigs {
        create("release") {
            storeFile = file("../release/dailyscoop-keystore.jks")
            keyAlias = localProperties.getProperty("SIGNING_KEY_ALIAS")
            storePassword = localProperties.getProperty("SIGNING_STORE_PASSWORD")
            keyPassword = localProperties.getProperty("SIGNING_KEY_PASSWORD")
        }
    }

    flavorDimensions += "type"
    productFlavors {
        create("dev") {
            dimension = "type"
            buildConfigField("String", "NEWS_API_KEY", "\"${localProperties.getProperty("NEWS_API_KEY")}\"")
        }

        create("live") {
            dimension = "type"
            buildConfigField("String", "NEWS_API_KEY", "\"${localProperties.getProperty("NEWS_API_KEY")}\"")
        }
    }

    buildTypes {
        getByName("debug") {
            isDebuggable = true
            applicationIdSuffix = ".debug"
        }

        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
            signingConfig = signingConfigs.getByName("release")
        }
    }

    // TODO: configure testOptions for junit5
}

dependencies {
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.tooling.preview)

    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    // Splash Screen
    implementation(libs.androidx.core.splashscreen)

    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.activity.compose)

    // Navigation Compose
    implementation(libs.androidx.navigation.compose)
    implementation(libs.kotlinx.serialization.core)

    // Preferences Datastore
    implementation(libs.androidx.datastore.preferences)

    // Hilt dependencies
    implementation(libs.google.dagger.hilt.android)
    implementation(libs.androidx.hilt.navigation.compose)
    ksp(libs.google.dagger.hilt.android.compiler)

    // Retrofit
    implementation(libs.squareup.retrofit2)
    implementation(libs.squareup.retrofit2.converter.gson)
    implementation(libs.google.gson)
    implementation(libs.squareup.okhttp3.logging.interceptor)

    // Room
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.paging)
    ksp(libs.androidx.room.compiler)

    // MockWebserver
    testImplementation(libs.squareup.okhttp3.mockwebserver)

    // Writing and executing Unit Tests on the JUnit 5 Platform
    // Stay tuned for official google android support for JUnit 5
    testImplementation(libs.junit.jupiter.api)
    testRuntimeOnly(libs.junit.jupiter.engine)

    // Needed for unit testing
    testImplementation(libs.androidx.core.testing)
    testImplementation(libs.kotlinx.coroutines.test)

    // Room test helpers
    testImplementation(libs.androidx.room.testing)

    // Test Assertion
    testImplementation(libs.google.truth)

    // Turbine
    testImplementation(libs.app.cash.turbine)

    // JUnit 5 instrumentation test dependencies
    androidTestImplementation(libs.junit.jupiter.api)
    androidTestImplementation(libs.junit5.android.test.core)
    androidTestRuntimeOnly(libs.junit5.android.test.runner)

    androidTestImplementation(libs.androidx.test.runner)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)
    androidTestImplementation(libs.androidx.test.compose.ui.junit4)
    androidTestImplementation(libs.androidx.navigation.testing)

    debugImplementation(libs.androidx.test.compose.ui.tooling)
    debugImplementation(libs.androidx.test.compose.ui.manifest)
}
