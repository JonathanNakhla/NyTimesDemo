object BuildPlugins {
    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.androidGradlePlugin}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val kotlinSerializationPlugin = "org.jetbrains.kotlin:kotlin-serialization:${Versions.kotlin}"

    const val androidApplication = "com.android.application"
    const val kotlinAndroid = "kotlin-android"
    const val kotlinxSerialization = "kotlinx-serialization"
    const val kotlinAndroidExtensions = "kotlin-android-extensions"
}

object BuildVersions {
    const val minSdk = 21
    const val targetSdk = 28
}

object Versions {
    const val kotlin = "1.3.60"
    const val serializationRuntime = "0.14.0"
    const val rxjava = "3.0.0-RC6"
    const val rxandroid = "3.0.0-SNAPSHOT"
    const val retrofit = "2.7.1"
    const val retrofitKotlinxSerializationConverter = "0.4.0"
    const val rxjavaCallAdapter = "3.0.0-RC8"
    const val androidGradlePlugin = "3.5.2"
    const val support = "1.1.0"
    const val constraintLayout = "1.1.3"
    const val recyclerview = "1.1.0"
    const val cardview = "28.0.0"
    const val swipeRefreshLayout = "1.0.0"
    const val material = "1.0.0"
    const val koin = "2.0.1"
    const val glide = "4.11.0"
    const val junit = "5.4.0"
    const val mockk = "1.9.3.kotlin12"
}

object Kotlin {
    const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val serializationRuntime = "org.jetbrains.kotlinx:kotlinx-serialization-runtime:${Versions.serializationRuntime}"
}

object RxLibraries {
    const val rxjava = "io.reactivex.rxjava3:rxjava:${Versions.rxjava}"
    const val rxandroid = "io.reactivex.rxjava3:rxandroid:${Versions.rxandroid}"
}

object NetworkLibraries {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitKotlinxSerializationConverter = "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:${Versions.retrofitKotlinxSerializationConverter}"
    const val rxjavaCallAdapter = "com.github.akarnokd:rxjava3-retrofit-adapter:${Versions.rxjavaCallAdapter}"
}

object SupportLibraries {
    const val supportAppCompat = "androidx.appcompat:appcompat:${Versions.support}"
}

object UiLibraries {
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"
    const val cardview = "com.android.support:cardview-v7:${Versions.cardview}"
    const val swipeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefreshLayout}"
    const val material = "com.google.android.material:material:${Versions.material}"
}

object DiLibraries {
    const val koin = "org.koin:koin-android:${Versions.koin}"
    const val koinViewModel = "org.koin:koin-android-viewmodel:${Versions.koin}"
}

object ToolsLibraries {
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
}

object TestLibraries {
    const val junit = "org.junit.jupiter:junit-jupiter:${Versions.junit}"
    const val mockk = "io.mockk:mockk:${Versions.mockk}"
}
