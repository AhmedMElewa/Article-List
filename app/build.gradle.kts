plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.elewa.si_ware_task"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.elewa.si_ware_task"
        minSdk = 24
        targetSdk = 35
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
        viewBinding = true
        buildConfig = true
    }

    flavorDimensions += "version"
    productFlavors {
        create("dTesting") {
            buildConfigField("String", "API_BASE_URL", "\"https://newsapi.org/\"")
            buildConfigField("String", "API_Key", "\"7ec2c3c1d37a437b8d214f8664582530 \"")
        }
        create("prod") {
            buildConfigField("String", "API_BASE_URL", "\"https://newsapi.org/\"")
            buildConfigField("String", "API_Key", "\"7ec2c3c1d37a437b8d214f8664582530 \"")
        }
    }
}

dependencies {

    //core
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.viewbinding)

    //Hilt for di
    implementation(libs.hilt.android)
    implementation(libs.androidx.material3.android)
    implementation(libs.androidx.paging.compose.android)

    kapt(libs.hilt.android.compiler)

    // ViewModel
    implementation(libs.androidx.lifecycle.extensions)
    implementation(libs.androidx.lifecycle.runtime.ktx.v220)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)

    //Coroutines
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.coroutines.play.services)

    //navigation
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    // Networking
    implementation(libs.retrofit)
    implementation(libs.converter.moshi)
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)


    //gson
    implementation(libs.gson)
    implementation(libs.converter.gson)

    // Room and room pagination
    implementation(libs.androidx.room.runtime)
    kapt(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.paging)

    //glide
    implementation (libs.glide)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}