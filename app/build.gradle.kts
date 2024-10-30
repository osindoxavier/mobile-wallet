import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.devToolsKsp)
    alias(libs.plugins.daggerHilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.comulynx.wallet.android"
    val localProperties = Properties()
    localProperties.load(FileInputStream(rootProject.file("local.properties")))

    compileSdk = 34

    defaultConfig {
        applicationId = "com.comulynx.wallet.android"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        multiDexEnabled = true
    }

    buildTypes {
        debug {
            buildConfigField(
                type = "String",
                name = "BASE_URL",
                value = "\"${localProperties["BASE_URl"]}\""
            )
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField(
                type = "String",
                name = "BASE_URL",
                value = "\"${localProperties["BASE_URl"]}\""
            )
            signingConfig = signingConfigs.getByName("debug")
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            merges += "META-INF/LICENSE.md"
            merges += "META-INF/LICENSE-notice.md"
        }
    }

}

dependencies {
    //Dagger Hilt
    implementation(libs.bundles.dagger.hilt)
    ksp(libs.dagger.hilt.compiler)
    //Android Core
    implementation(libs.bundles.androidx.core)
    //Room
    implementation(libs.bundles.androidx.room)
    ksp(libs.androidx.room.compiler)
    //Retrofit
    implementation(libs.bundles.square.retrofit)
    //gson
    implementation(libs.gson)
    //Material
    implementation(libs.bundles.material)
    //lifecycle-runtime
    implementation(libs.bundles.lifecycle.runtime)
    //AndroidX Compose
    implementation(libs.bundles.compose)
    implementation(platform(libs.androidx.compose.bom))
    //AndroidX UI
    implementation(libs.bundles.androidx.ui)
    //Serialization
    implementation(libs.kotlinx.serialization.json)
    //Local test
    testImplementation(libs.bundles.local.test)
    //Android test
//    androidTestImplementation(libs.bundles.android.test)

//    debugImplementation(libs.androidx.ui.tooling)
//    debugImplementation(libs.androidx.ui.test.manifest)
//    androidTestImplementation(platform(libs.androidx.compose.bom))
}