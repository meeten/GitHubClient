pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        maven {
            url = uri("https://redirector.kotlinlang.org/maven/dev")
            url = uri("https://redirector.kotlinlang.org/maven/ktor-eap")
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
    }
}

rootProject.name = "GitHubClient"
include(":app")
include(":domain")
include(":data")
include(":feature")
include(":feature:authorization")
include(":core")
include(":core:designsystem")
include(":core:ui")
include(":core:storage")
include(":feature:home")
include(":feature:repo-info")
