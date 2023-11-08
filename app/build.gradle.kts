plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
//    id("kotlin-android-extensions")
//    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs")
}

android {
    compileSdkVersion(33)
    buildToolsVersion("29.0.3")

    defaultConfig {
        applicationId = "com.axelia.openweathermapprototype"
        minSdkVersion(21)
        targetSdkVersion(33)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        javaCompileOptions {
            annotationProcessorOptions {
                arguments.plusAssign(
                    hashMapOf(
                        "room.schemaLocation" to "$projectDir/schemas",
                        "room.incremental" to "true",
                        "room.expandProjection" to "true"
                    )
                )
            }
        }
    }

    buildFeatures {
        viewBinding {
            isEnabled = true
        }
    }

    buildTypes {
        getByName("release") {
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
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    // Kotlin
    implementation(Dependencies.kotlin)

    // Coroutines
    implementation(Coroutines.core)
    implementation(Coroutines.android)

    // Android
    implementation(Android.appcompat)
    implementation(Android.activityKtx)
    implementation(Android.coreKtx)
    implementation(Android.constraintLayout)
    implementation(Android.swipeRefreshLayout)

    // Architecture Components
    implementation(Dependencies.viewModel)
    implementation(Dependencies.liveData)

    // Room components
    implementation(Room.runtime)
    kapt(Room.compiler)
    implementation(Room.ktx)

    // Material Design
    implementation(Dependencies.materialDesign)
    implementation(Dependencies.materialDialog)

    // Coil-kt
    implementation(Dependencies.coil)

    // Retrofit
    implementation(Dependencies.retrofit)

    // Moshi
    implementation(Moshi.kotlin)
    implementation(Moshi.retrofitConverter)
    kapt(Moshi.codeGen)

    // Hilt + Dagger
    implementation(Hilt.hiltAndroid)
    implementation(Hilt.hiltViewModel)
    kapt(Hilt.daggerCompiler)
    kapt(Hilt.hiltCompiler)

    // Testing
    testImplementation(Testing.core)
    testImplementation(Testing.coroutines)
    testImplementation(Testing.room)
    testImplementation(Testing.okHttp)
    testImplementation(Testing.jUnit)

    // Android Testing
    androidTestImplementation(Testing.extJUnit)
    androidTestImplementation(Testing.espresso)

    // Gson
    implementation(Gson.gson)

    implementation("androidx.fragment:fragment-ktx:1.2.4")

    implementation("androidx.lifecycle:lifecycle-runtime:2.2.0")
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    kapt("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation("androidx.lifecycle:lifecycle-common-java8:2.2.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.2.0")

    implementation("io.insert-koin:koin-android:3.1.4")

    implementation("androidx.navigation:navigation-fragment:2.5.1")
    implementation("androidx.navigation:navigation-ui-ktx:2.5.1")
//    implementation("androidx.navigation:navigation-safe-args-gradle-plugin:2.5.1")
}