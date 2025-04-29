plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.myapp"
    compileSdk = 34 // Example SDK version, adjust if needed

    defaultConfig {
        applicationId = "com.example.myapp"
        minSdk = 21 // Example minSdk, adjust if needed
        targetSdk = 34 // Example targetSdk, adjust if needed
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.15" // Use a version compatible with your Kotlin/Compose setup
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(platform("androidx.compose:compose-bom:2025.02.00"))
    androidTestImplementation(platform("androidx.compose:compose-bom:2025.02.00"))

    // Jetpack Compose UI
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.ui:ui-tooling-preview")
    debugImplementation("androidx.compose.ui:ui-tooling")

    // Jetpack Compose Navigation
    implementation("androidx.navigation:navigation-compose:2.8.9")

    // ViewModel Compose Integration
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.5")

    // Kotlin Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")

    // Default dependencies (usually included in new projects)
    implementation("androidx.core:core-ktx:1.9.0") // Example version
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2") // Example version
    implementation("androidx.activity:activity-compose:1.8.0") // Example version for activity-compose integration

    // Testing dependencies
    testImplementation("junit:junit:4.13.2") // Example version
    androidTestImplementation("androidx.test.ext:junit:1.1.5") // Example version
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1") // Example version
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}
