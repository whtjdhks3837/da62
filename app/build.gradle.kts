plugins {
    id(BuildPlugins.androidApplication)
    kotlin(BuildPlugins.android)
    kotlin(BuildPlugins.androidExtension)
    kotlin(BuildPlugins.kapt)
    id("androidx.navigation.safeargs.kotlin")
}

android {

    compileSdkVersion(AndroidSdk.compileVersion)
    defaultConfig {
        applicationId = "com.da62"
        minSdkVersion(AndroidSdk.minVersion)
        targetSdkVersion(AndroidSdk.targetVersion)
        versionCode = Versions.code
        versionName = Versions.name
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        multiDexEnabled = true
    }
    buildTypes {

        getByName("release") {
            isDebuggable = false
        }
    }

    dataBinding.isEnabled = true
}

androidExtensions {
    isExperimental = true
}

kapt {
    useBuildCache = true
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Kotlin.kotlin)

    implementation(Libraries.appcompat)
    implementation(Libraries.material)

    implementation(Libraries.constraintLayout)

    // Architecture Components
    implementation(Libraries.ktxCore)
    implementation(Libraries.lifeCycleExtension)

    // Glide
    implementation(Libraries.glide)
    kapt(Libraries.glideCompiler)
    implementation(Libraries.glideTransform)

    // Koin
    implementation(Libraries.koinAndroid)
    implementation(Libraries.koinAndroidXScope)
    implementation(Libraries.koinAndroidXViewModel)

    // Rxjava
    implementation(Libraries.rxJava)
    implementation(Libraries.rxAndroid)

    // Retrofit with Moshi
    implementation(Libraries.retrofit)
    implementation(Libraries.retrofitRxJava)

    implementation(Libraries.anko)

    implementation(Libraries.navigationFragment)
    implementation(Libraries.navigationUi)


    testImplementation(TestLibraries.junit)
    androidTestImplementation(TestLibraries.rules)
    androidTestImplementation(TestLibraries.espressoCore)

    implementation(DebugLibraries.okhttpLogger)
}
