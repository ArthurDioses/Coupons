plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp") version "1.9.10-1.0.13"
}

android {
    namespace = "com.dioses.coupons"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.dioses.coupons"
        minSdk = 24
        targetSdk = 33
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures{
        viewBinding = true
    }
}

val androidx_core_version = "1.9.0"
val appcompat_version = "1.6.1"
val material_version = "1.10.0"
val constraint_version = "2.1.4"
val room_version = "2.6.1"
val lifecycle_version = "2.6.2"
val coroutines_version = "1.7.1"

dependencies {

    implementation("androidx.core:core-ktx:$androidx_core_version")
    implementation("androidx.appcompat:appcompat:$appcompat_version")
    implementation("com.google.android.material:material:$material_version")
    implementation("androidx.constraintlayout:constraintlayout:$constraint_version")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")

    //Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines_version")

    //LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")

    //Room
    implementation("androidx.room:room-runtime:$room_version")
    ksp("androidx.room:room-compiler:$room_version")
    // optional - Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:$room_version")
}