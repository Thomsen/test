import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    var kotlinVersion: String by extra
    kotlinVersion = "1.3.11"

    repositories {
        google()
        jcenter()
        mavenCentral()
    }

    dependencies {
        // update the gradle to 5.4.1 Unresolved reference: kotlinModule
        classpath(kotlinModule("gradle-plugin", kotlinVersion))
        //classpath(kotlin("gradle-plugin", version = kotlin_version))
    }
}

apply {
    plugin("kotlin")
}

val kotlinVersion: String by extra

repositories {
    google()
    jcenter()
    mavenCentral()
}

dependencies {
    implementation(kotlinModule("stdlib-jdk8", kotlinVersion))
    implementation("io.reactivex.rxjava3:rxkotlin:3.0.0")

    // Use the Kotlin test library.
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}


