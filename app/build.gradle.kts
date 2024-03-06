import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("dagger.hilt.android.plugin")
    id("de.mannodermaus.android-junit5")
}

// Initialize for property retrieval on variables defined in [local.properties]
val localProperties = Properties()
localProperties.load(project.rootProject.file("local.properties").inputStream())

// Dependencies Extensions - this will be migrated via version catalog
val composeVersion = "1.6.2"
val roomVersion = "2.6.0"
val hiltVersion = "2.48"

android {

    namespace = "com.dailyscoop.app"

    compileSdk = 34

    defaultConfig {

        applicationId = "com.dailyscoop.app"

        // TODO: update this to 28, therefore Android Pie will be the minimum os version soon
        minSdk = 27

        targetSdk = 34

        versionCode = getVersionCode()

        versionName = "1.0.0"

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
            versionName = "1.0.0-dev"
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

    lint {
        checkReleaseBuilds = false
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.9"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    // TODO: configure testOptions for junit5
}

// Calculates and get the versionCode based on commit counts
@Suppress("UnstableApiUsage")
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

dependencies {
    implementation("androidx.compose.ui:ui:$composeVersion")
    implementation("androidx.compose.material3:material3:1.2.0")
    implementation("androidx.compose.ui:ui-tooling-preview:$composeVersion")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.7.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
    // TODO: this will be needed in order to support multiple screen densities
    // implementation "androidx.compose.material3:material3-window-size-class:1.1.1"

    // Splash Screen
    implementation("androidx.core:core-splashscreen:1.0.1")

    implementation("androidx.activity:activity-ktx:1.8.2")
    implementation("androidx.activity:activity-compose:1.8.2")

    // Navigation Compose
    implementation("androidx.navigation:navigation-compose:2.7.7")

    // Hilt dependencies
    implementation("com.google.dagger:hilt-android:$hiltVersion")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")
    ksp("com.google.dagger:hilt-android-compiler:$hiltVersion")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")

    // Room
    implementation("androidx.room:room-ktx:$roomVersion")
    implementation("androidx.room:room-runtime:$roomVersion")
    implementation("androidx.room:room-paging:$roomVersion")
    ksp("androidx.room:room-compiler:$roomVersion")

    // MockWebserver
    testImplementation("com.squareup.okhttp3:mockwebserver:4.10.0")

    // Writing and executing Unit Tests on the JUnit 5 Platform
    // Stay tuned for official google android support for JUnit 5
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.3")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.3")

    // Needed for unit testing
    testImplementation("androidx.arch.core:core-testing:2.2.0")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")

    // Room test helpers
    testImplementation("androidx.room:room-testing:$roomVersion")

    // Test Assertion
    testImplementation("com.google.truth:truth:1.1.3")

    // Turbine
    testImplementation("app.cash.turbine:turbine:0.12.1")

    // JUnit 5 instrumentation test dependencies
    androidTestImplementation("org.junit.jupiter:junit-jupiter-api:5.9.3")
    androidTestImplementation("de.mannodermaus.junit5:android-test-core:1.3.0")
    androidTestRuntimeOnly("de.mannodermaus.junit5:android-test-runner:1.3.0")

    androidTestImplementation("androidx.test:runner:1.5.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:$composeVersion")

    debugImplementation("androidx.compose.ui:ui-tooling:$composeVersion")
    debugImplementation("androidx.compose.ui:ui-test-manifest:$composeVersion")
}
