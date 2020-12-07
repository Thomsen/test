//import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

//buildscript {
//    var kotlinVersion: String by extra
//    kotlinVersion = "1.3.70"
//
//    repositories {
//        google()
//        jcenter()
//        mavenCentral()
//    }
//
//    dependencies {
//        // update the gradle to 5.4.1 Unresolved reference: kotlinModule
////        classpath(kotlinModule("gradle-plugin", kotlinVersion))
//        //classpath(kotlin("gradle-plugin", version = kotlin_version))
//    }
//}

//val kotlinVersion: String by extra

plugins {
    application
    kotlin("jvm") version "1.4.0"
}

apply {
    plugin("kotlin")
}


repositories {
    google()
    jcenter()
    mavenCentral()
}

sourceSets {
    main {
        java.srcDir("src/main/kotlin")
    }
}

dependencies {
//    implementation(kotlinModule("stdlib-jdk8", kotlinVersion))
    implementation(kotlin("stdlib"))
    implementation("io.reactivex.rxjava3:rxkotlin:3.0.0")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.9")

    // gson
    implementation("com.google.code.gson:gson:2.8.6")

    // Use the Kotlin test library.
    testImplementation("org.jetbrains.kotlin:kotlin-test")

    // junit 5 run platfrom
    testImplementation("org.junit.platform:junit-platform-runner:1.6.2")
    // junit 5
    testImplementation("org.junit.jupiter:junit-jupiter:5.6.2")
    // Only needed to run tests in a version of IntelliJ IDEA that bundles older versions
    testRuntimeOnly("org.junit.platform:junit-platform-launcher:1.6.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.6.2")
    testRuntimeOnly("org.junit.vintage:junit-vintage-engine:5.6.2")
}

// $ gradle wrapper --gradle-version 6.4.1
//task<Wrapper>("wrapper") {
//    gradleVersion = "6.4.1"
//}

//tasks.withType<KotlinCompile> {
//    kotlinOptions.jvmTarget = "1.8"
//}

//tasks.withType<Test> {
//    useJUnitPlatform()
//}

tasks {
    // Use the built-in JUnit support of Gradle.
    "test"(Test::class) {
        useJUnitPlatform()
        jvmArgs("--enable-preview")
    }
}



