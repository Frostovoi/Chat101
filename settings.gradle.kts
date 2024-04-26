pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")


rootProject.name = "Chat101"
include(":app")
include(":sources")
include(":sources:core_api")
include(":sources:core_factory")
include(":sources:core_impl")
include(":sources:ui")
include(":sources:login")
include(":sources:register")
include(":sources:main")
include(":sources:profile")
include(":sources:conversation")
include(":sources:network")
