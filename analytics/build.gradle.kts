plugins {
    id(BuildPlugins.androidLibrary)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinxSerialization)
    id(BuildPlugins.kotlinAndroidExtensions)
    id(BuildPlugins.kotlinKapt)
}

android {
    compileSdkVersion(BuildVersions.targetSdk)
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>() {
        kotlinOptions.jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
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
    implementation(project(":network"))
    implementation(Kotlin.stdlib)
    implementation(Kotlin.serializationRuntime)
    implementation(RxLibraries.rxjava)
    implementation(RxLibraries.rxandroid)
    implementation(RxLibraries.rxjava3Bridge)
    implementation(NetworkLibraries.retrofit)
    implementation(NetworkLibraries.retrofitKotlinxSerializationConverter)
    implementation(NetworkLibraries.rxjavaCallAdapter)
    implementation(RoomLibraries.runtime)
    kapt(RoomLibraries.compiler)
    implementation(RoomLibraries.ktx)
    implementation(RoomLibraries.rxjava2)
    implementation(WorkManagerLibraries.runtimeKtx)
    implementation(WorkManagerLibraries.rxjava)

    testImplementation(RoomLibraries.testing)
    testImplementation(TestLibraries.junit)
    testImplementation(TestLibraries.mockk)

    androidTestImplementation(WorkManagerLibraries.testing)
}
