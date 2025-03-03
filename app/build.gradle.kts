plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.dagger.hilt.android)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.punam.montra"
    compileSdk = 35
    defaultConfig {
        applicationId = "com.punam.montra"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }


    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            buildConfigField("String", "BASE_URL", "\"http:192.168.1.72:80\"")
        }
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "BASE_URL", "\"https://api.montra.com/\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        buildConfig = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.material.icons.extended)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor)
    implementation(libs.okhttp)
    implementation(libs.arrow.core)
    implementation(libs.lottie.compose)
    implementation(libs.coil.compose)
    implementation(libs.compose.m3)
    implementation(libs.views)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Pager and Indicators - Accompanist
    implementation(libs.accompanist.pager)
    implementation(libs.accompanist.pager.indicators)

    // DataStore Preferences
    implementation(libs.androidx.datastore.preferences)
//
    // Dependency Injection
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.hilt.android)
    implementation(libs.androidx.core.splashscreen)
    kapt(libs.hilt.compiler)

    // navigation
    implementation(libs.androidx.navigation.compose)
    implementation(libs.desugar.jdk.libs)
    implementation(libs.kotlinx.serialization.json)

}