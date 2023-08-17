plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
}

android {
    namespace = "br.com.myrepositories"
    compileSdk = 33

    defaultConfig {
        applicationId = "br.com.myrepositories"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    hilt {
        enableAggregatingTask = true
    }
}

dependencies {

    implementation(project(":libraries:network"))
    implementation(project(":libraries:extensions"))

    implementation(Dependencies.Main.core_ktx)
    implementation(Dependencies.Lifecycle.lifecycle_runtime)

    //Compose
    implementation(Dependencies.Compose.activity_compose)
    implementation(platform(Dependencies.Compose.compose_bom))
    implementation(Dependencies.Compose.compose_ui)
    implementation(Dependencies.Compose.compose_graphics)
    implementation(Dependencies.Compose.compose_ui_tooling_preview)
    implementation(Dependencies.Compose.compose_material3)
    implementation(Dependencies.Compose.constraintlayout_compose)

    //Material3
    implementation(Dependencies.Compose.material3)
    //Icons Material 3
    implementation(Dependencies.Compose.material_icons_extended)

    // System UI Controller - Accompanist
    implementation(Dependencies.ThirdParty.system_ui_controller)

    //Coil
    implementation(Dependencies.ThirdParty.coil_compose)
    implementation(Dependencies.ThirdParty.coil_svg)

    //Network
    implementation(Dependencies.Network.retrofit)
    implementation(Dependencies.Network.converter_gson)
    implementation(Dependencies.Network.okhttp3_logging_interceptor)

    //Glide
    implementation(Dependencies.ThirdParty.glide_compiler)
    implementation(Dependencies.ThirdParty.glide)

    //Dagger Hilt
    implementation(Dependencies.ThirdParty.dagger_hilt)
    kapt(Dependencies.ThirdParty.dagger_hilt_compiler)

    testImplementation(Dependencies.Testing.junit)
    testImplementation(Dependencies.Testing.dagger_hilt_testing)
    kaptAndroidTest(Dependencies.ThirdParty.dagger_hilt_compiler)
    kaptTest(Dependencies.ThirdParty.dagger_hilt_compiler)
    androidTestImplementation(Dependencies.Testing.AutomatedTest.ext_junit)
    androidTestImplementation(Dependencies.Testing.AutomatedTest.espresso_core)
    androidTestImplementation(platform(Dependencies.Compose.compose_bom))
    androidTestImplementation(Dependencies.Testing.Compose.ui_test_junit4)
    androidTestImplementation(Dependencies.Testing.assertK)
    androidTestImplementation(Dependencies.Testing.AutomatedTest.mockk_android)
    androidTestImplementation(Dependencies.Testing.AutomatedTest.barista)
    androidTestImplementation(Dependencies.Testing.dagger_hilt_testing)
    debugImplementation(Dependencies.Compose.ui_tooling)
    debugImplementation(Dependencies.Compose.ui_test_manifest)
}

kapt {
    correctErrorTypes = true
}