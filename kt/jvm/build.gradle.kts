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
    kotlin("jvm") version "1.6.21"
    // java micro-benchmarking harness (https://github.com/melix/jmh-gradle-plugin)
    id("me.champeau.gradle.jmh") version "0.5.2"
    //id("org.jetbrains.kotlin.jvm") version "1.6.21"
}

apply {
    plugin("kotlin")
    plugin("me.champeau.gradle.jmh")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        // context receivers
        freeCompilerArgs = listOf("-Xcontext-receivers")

    }
}

repositories {
    google()
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

    implementation("org.jetbrains.kotlin:kotlin-reflect:1.4.20")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.3")

    // gson
    implementation("com.google.code.gson:gson:2.8.6")
    implementation("org.junit.jupiter:junit-jupiter:5.4.2")

    // jackson
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.+")

    // okhttp
    implementation(platform("com.squareup.okhttp3:okhttp-bom:4.9.0"))
    implementation("com.squareup.okhttp3:okhttp")
    implementation("com.squareup.okhttp3:logging-interceptor")

    // jol
    implementation("org.openjdk.jol:jol-core:0.16")

    // Use the Kotlin test library.
    testImplementation("org.jetbrains.kotlin:kotlin-test")

    // junit 5 run platform
    testImplementation("org.junit.platform:junit-platform-runner:1.6.2")
    // junit 5
    testImplementation("org.junit.jupiter:junit-jupiter:5.6.2")
    // Only needed to run tests in a version of IntelliJ IDEA that bundles older versions
    testRuntimeOnly("org.junit.platform:junit-platform-launcher:1.6.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.6.2")
    testRuntimeOnly("org.junit.vintage:junit-vintage-engine:5.6.2")

    // benchmark of code
    implementation("org.openjdk.jmh:jmh-core:1.25")

    testImplementation("org.openjdk.jmh:jmh-core:1.25")
    //testImplementation("org.openjdk.jmh:jmh-generator-annprocess:1.25")
    testAnnotationProcessor("org.openjdk.jmh:jmh-generator-annprocess:1.25")

    jmh(kotlin("stdlib"))
    jmh("org.openjdk.jmh:jmh-core:1.25")
    jmh("org.openjdk.jmh:jmh-generator-annprocess:1.25")

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

jmh {
    duplicateClassesStrategy = DuplicatesStrategy.EXCLUDE
    include = mutableListOf("kt\\.algo\\.fibonacci\\.JmhFibonacci.*")
    jmhVersion = "1.25"
    humanOutputFile = null
    warmupIterations = 1
    iterations = 1

//    zip64 = true
//    includeTests = true
}


