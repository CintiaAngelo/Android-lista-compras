plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    kotlin("kapt")
}

android {
    namespace = "CintiaAngelo.com.github.android_lista_de_compras"
    compileSdk = 34

    defaultConfig {
        applicationId = "CintiaAngelo.com.github.android_lista_de_compras"
        minSdk = 24
        targetSdk = 34
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
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        // Isso está CORRETO agora, com Gradle 8.9 e Java 17
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        // Isso está CORRETO agora
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        viewBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.10" // Compatible with Kotlin 1.9.22
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
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation(libs.google.material)

    // AINDA PRECISAM SER ATUALIZADAS E MOVIDAS PARA libs.versions.toml
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.3.1") // Atualize para 2.8.0
    implementation("androidx.recyclerview:recyclerview:1.3.2")       // Atualize para 1.3.4
    implementation("androidx.appcompat:appcompat:1.4.1")           // Atualize para 1.7.0
    implementation("androidx.activity:activity-ktx:1.7.0")         // Atualize para 1.9.0

    // ROOM: Versões 2.6.1 estão corretas! (Parabéns por atualizar!)
    implementation("androidx.room:room-runtime:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1") // O ktx estava 2.4.1, agora está 2.6.1, correto!

    // KOTLIN COROUTINES: AINDA PRECISA SER ATUALIZADA E MOVIDA PARA libs.versions.toml
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2") // Atualize para 1.8.1
}