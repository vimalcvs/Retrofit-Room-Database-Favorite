 plugins {
    alias(libs.plugins.androidApplication) apply false
    id("org.jetbrains.kotlin.android") version "1.9.22" apply false
     id("com.google.devtools.ksp") version "1.9.10-1.0.13" apply false
}