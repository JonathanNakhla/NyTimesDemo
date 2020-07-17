plugins {
    id(BuildPlugins.androidLibrary)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinxSerialization)
    id(BuildPlugins.kotlinAndroidExtensions)
}

android {
    compileSdkVersion(BuildVersions.targetSdk)
    defaultConfig {
        minSdkVersion(BuildVersions.minSdk)
        targetSdkVersion(BuildVersions.targetSdk)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Kotlin.stdlib)
    implementation(Kotlin.serializationRuntime)
    implementation(RxLibraries.rxjava)
    implementation(RxLibraries.rxandroid)
    implementation(NetworkLibraries.retrofit)
    implementation(NetworkLibraries.retrofitKotlinxSerializationConverter)
    implementation(NetworkLibraries.rxjavaCallAdapter)
    testImplementation(TestLibraries.junit)
    testImplementation(TestLibraries.mockk)
    debugImplementation(ToolsLibraries.chuck)
    releaseImplementation(ToolsLibraries.chuckNoop)
}
