import java.io.FileInputStream
import java.util.Properties


plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("maven-publish")
}

val githubProperties = Properties()
githubProperties.load(FileInputStream(rootProject.file("github.properties")))

fun getVersionName(): String {
    return "1.0.0"
}

fun getArtificatId(): String {
    return "jetpackbrazilfields"
}

android {
    namespace = "dev.thiaago.jetpackbrazilfields"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

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
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

publishing {
    publications {
        create<MavenPublication>("gpr") {
            run {
                groupId = "dev.thiaago"
                artifactId = getArtificatId()
                version = getVersionName()
                artifact("$buildDir/outputs/aar/app-release.aar")
            }
        }
    }

    repositories {
        maven {
            name = "GithubPackages"
            url = uri("https://maven.pkg.github.com/thiaagodev/Jetpack-Brazil-Fields")
            credentials {
                username = githubProperties["gpr.usr"] as String? ?: System.getenv("GPR_USER")
                password = githubProperties["gpr.key"] as String? ?: System.getenv("GPR_API_KEY")
            }
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
    implementation(libs.androidx.ui.tooling)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}