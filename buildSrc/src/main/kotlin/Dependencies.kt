object Dependencies {
    object Main {
        const val core_ktx = "androidx.core:core-ktx:${Version.core_ktx_version}"
        const val appcompat = "androidx.appcompat:appcompat:${Version.appcompat_version}"
    }

    object Lifecycle {
        const val lifecycle_livedata =
            "androidx.lifecycle:lifecycle-livedata-ktx:${Version.lifecycle_version}"
        const val lifecycle_viewmodel =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.lifecycle_version}"
        const val lifecycle_runtime =
            "androidx.lifecycle:lifecycle-runtime-ktx:${Version.lifecycle_version}"
    }

    object Compose {
        const val activity_compose =
            "androidx.activity:activity-compose:${Version.activity_compose_version}"
        const val compose_bom = "androidx.compose:compose-bom:${Version.compose_bom_version}"
        const val compose_ui = "androidx.compose.ui:ui"
        const val compose_graphics = "androidx.compose.ui:ui-graphics"
        const val compose_ui_tooling_preview = "androidx.compose.ui:ui-tooling-preview"
        const val compose_material3 = "androidx.compose.material3:material3"
        const val ui_tooling = "androidx.compose.ui:ui-tooling"
        const val ui_test_manifest = "androidx.compose.ui:ui-test-manifest"
        const val material3="androidx.compose.material3:material3"
        const val material_icons_extended="androidx.compose.material:material-icons-extended"
        const val constraintlayout_compose="androidx.constraintlayout:constraintlayout-compose:${Version.constraintlayout_compose_version}"
    }

    object Network {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Version.retrofit_version}"
        const val converter_gson =
            "com.squareup.retrofit2:converter-gson:${Version.retrofit_version}"
        const val okhttp3_logging_interceptor =
            "com.squareup.okhttp3:logging-interceptor:${Version.okhttp3_logging_interceptor_version}"
    }

    object ThirdParty {
        const val glide_compiler = "com.github.bumptech.glide:compiler:${Version.glide_version}"
        const val glide = "com.github.bumptech.glide:glide:${Version.glide_version}"
        const val dagger_hilt = "com.google.dagger:hilt-android:${Version.dagger_hilt_version}"
        const val dagger_hilt_compiler =
            "com.google.dagger:hilt-compiler:${Version.dagger_hilt_version}"
        const val coil_compose = "io.coil-kt:coil-compose:${Version.coil_version}"
        const val coil_svg = "io.coil-kt:coil-svg:${Version.coil_version}"
        const val system_ui_controller = "com.google.accompanist:accompanist-systemuicontroller:${Version.accompanist_version}"
    }

    object Testing {
        const val assertK = "com.willowtreeapps.assertk:assertk-jvm:${Version.assertK_version}"
        const val junit = "junit:junit:${Version.junit_version}"
        const val dagger_hilt_testing =
            "com.google.dagger:hilt-android-testing:${Version.dagger_hilt_version}"

        object Compose {
            const val ui_test_junit4 = "androidx.compose.ui:ui-test-junit4"
        }

        object AutomatedTest {
            const val ext_junit = "androidx.test.ext:junit:${Version.ext_junit_version}"
            const val espresso_core =
                "androidx.test.espresso:espresso-core:${Version.espresso_core_version}"
            const val barista = "com.adevinta.android:barista:${Version.barista_version}"
            const val mockk_android = "io.mockk:mockk-android:${Version.mockk_version}"
        }
    }
}