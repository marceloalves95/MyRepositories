plugins {
    `kotlin-dsl`
}
repositories {
    mavenCentral()
    google()
}
dependencies {
    implementation("com.android.tools.build:gradle:8.1.0")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.10")
    implementation("com.squareup:javapoet:1.13.0")
}

gradlePlugin {
    // register JacocoReportsPlugin as a plugin
    plugins {
        register("jacoco-reports") {
            id = "jacoco-reports"
            implementationClass = "plugin.JacocoReportsPlugin"
        }
    }
}