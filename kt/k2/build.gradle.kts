
plugins {
    kotlin("jvm") version "2.0.0"
//    kotlin("jvm") version "1.9.25"

}

repositories {
    mavenCentral()
}


dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))

    implementation("org.jetbrains.kotlin:kotlin-metadata-jvm:2.0.0")

    testImplementation("org.junit.jupiter:junit-jupiter:5.8.2")
}


//test {
//     useJUnitPlatform()
//}
tasks.named<Test>("test") {
    useJUnitPlatform()
}

