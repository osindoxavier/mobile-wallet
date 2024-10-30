plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.devToolsKsp)
    alias(libs.plugins.daggerHilt)
}

android {
    namespace = "com.comulynx.wallet.android"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.comulynx.wallet.android"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
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
    //Material
    implementation(libs.bundles.material)
    //lifecycle-runtime
    implementation(libs.bundles.lifecycle.runtime)
    //AndroidX Compose
    implementation(libs.bundles.compose)
    implementation(platform(libs.androidx.compose.bom))
    //AndroidX UI
    implementation(libs.bundles.androidx.ui)

    //Local test
    testImplementation(libs.bundles.local.test)
    //Android test
    androidTestImplementation(libs.bundles.android.test)

    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    androidTestImplementation(platform(libs.androidx.compose.bom))
}