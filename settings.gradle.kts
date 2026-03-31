enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Consulta_Inventario"
include(":app")
include(":core:common")
include(":core:preferences")
include(":core:network")
include(":core:design")
include(":layers:model")
include(":layers:data")
include(":layers:domain")
include(":feature:navigation:root-navigation")
include(":feature:login")
include(":feature:full-image")
include(":feature:navigation:main-drawer-navigation")
include(":feature:home")
