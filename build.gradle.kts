// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    // Estas são as formas recomendadas de declarar plugins, usando o version catalog.
    // As versões para estes plugins são definidas no seu arquivo libs.versions.toml.
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false

    // Se você tiver o plugin Kotlin KSP (Kotlin Symbol Processing) para Room ou Hilt, adicione aqui também:
    // alias(libs.plugins.googleDevtoolsKsp) apply false
}