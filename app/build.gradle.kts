import org.gradle.kotlin.dsl.implementation

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    alias(libs.plugins.google.gms.google.services)

}

android {
    namespace = "com.example.moneyexpanse"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.moneyexpanse"
        minSdk = 24
        targetSdk = 36
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
    configurations.all {
        exclude(group = "com.intellij", module = "annotations")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
//    implementation(libs.androidx.core.ktx)
//    implementation(libs.androidx.lifecycle.runtime.ktx)
//
//    // Compose
//    implementation(platform(libs.androidx.compose.bom))
//    implementation(libs.androidx.compose.material3)
//    implementation(libs.androidx.compose.foundation)
//    implementation(libs.androidx.navigation.compose)
//
//    // Firebase / Auth
////    implementation(libs.androidx.credentials)
////    implementation(libs.androidx.credentials.play.services.auth)
////    implementation(libs.googleid)
////    implementation(libs.firebase.auth)
//
//    // Firebase BOM (ADD THIS)
//    implementation(platform("com.google.firebase:firebase-bom:33.5.1"))
//
//    // Firebase services (NO VERSION)
//    implementation("com.google.firebase:firebase-auth-ktx")
//    implementation("com.google.firebase:firebase-firestore-ktx")
//    implementation("com.google.firebase:firebase-database-ktx")
//    // Coil
//    implementation("io.coil-kt:coil:2.7.0")
//
//    // Hilt
//    implementation("com.google.dagger:hilt-android:2.51")
//    implementation(libs.androidx.ui.tooling.preview)
//    implementation(libs.firebase.firestore)
//    implementation(libs.firebase.database)
//    implementation(libs.androidx.ui)
//    implementation(libs.firebase.firestore.ktx)
//    debugImplementation(libs.androidx.ui.tooling)
//    kapt("com.google.dagger:hilt-compiler:2.51")
//    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
//
//    // Coroutines
//    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.7.3")
//
//    // Room (CORRECT)
//    implementation("androidx.room:room-runtime:2.6.1")
//    implementation("androidx.room:room-ktx:2.6.1")
//    kapt("androidx.room:room-compiler:2.6.1")
//
//    // ⚠️ Agar error phir aaye, is line ko temporarily comment karo
//    // implementation(libs.litert.support.api)
////
////    testImplementation(libs.junit)
////    androidTestImplementation(libs.androidx.junit)
//
//    implementation("androidx.navigation:navigation-compose:2.7.6")
//    implementation("com.google.accompanist:accompanist-navigation-animation:0.33.2-alpha")

 

        implementation(libs.androidx.core.ktx)
        implementation(libs.androidx.lifecycle.runtime.ktx)

        // Compose
        implementation(platform(libs.androidx.compose.bom))
        implementation(libs.androidx.compose.material3)
        implementation(libs.androidx.compose.foundation)
        implementation(libs.androidx.navigation.compose)

        // Firebase BOM
        implementation(platform("com.google.firebase:firebase-bom:33.5.1"))

        // Firebase (NO VERSION HERE)
        implementation("com.google.firebase:firebase-auth-ktx")
        implementation("com.google.firebase:firebase-firestore-ktx")
        implementation("com.google.firebase:firebase-database-ktx")

        // Coil
        implementation("io.coil-kt:coil:2.7.0")

        // Hilt
        implementation("com.google.dagger:hilt-android:2.51")
        kapt("com.google.dagger:hilt-compiler:2.51")
        implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

        // Room
        implementation("androidx.room:room-runtime:2.6.1")
        implementation("androidx.room:room-ktx:2.6.1")
        kapt("androidx.room:room-compiler:2.6.1")

        // Coroutines
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.7.3")

        implementation(libs.androidx.ui.tooling.preview)
        implementation(libs.androidx.ui)
        debugImplementation(libs.androidx.ui.tooling)

        implementation("androidx.navigation:navigation-compose:2.7.6")
        implementation("com.google.accompanist:accompanist-navigation-animation:0.33.2-alpha")

}