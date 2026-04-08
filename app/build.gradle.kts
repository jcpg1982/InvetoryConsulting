import com.android.build.gradle.internal.api.BaseVariantOutputImpl
import org.gradle.language.nativeplatform.internal.Dimensions.applicationVariants
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.google.services)
    alias(libs.plugins.crashlytics)
}

android {
    namespace = "pe.com.master.machines.consulta_inventario"
    compileSdk {
        version = release(libs.versions.compileSdk.get().toInt()) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        applicationId = "pe.com.master.machines.consulta_inventario"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
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
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

base {
    val dateTime = SimpleDateFormat("ddMMyyyy_HHmmss", Locale.ROOT).apply {
        timeZone = TimeZone.getTimeZone("GMT-5")
    }.format(Date())
    val versionName = android.defaultConfig.versionName
    val versionCode = android.defaultConfig.versionCode
    archivesName.set("ConsultaInventario_${dateTime}_${versionName}_${versionCode}")
}

dependencies {

    implementation(projects.core.common)
    implementation(projects.core.design)
    implementation(projects.core.firebase)
    implementation(projects.feature.navigation.rootNavigation)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)

    // Firebase (Bom is in core:firebase but can be added here too if needed for direct usage)
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    //splash screen
    implementation(libs.androidx.core.splashscreen)
    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
}
