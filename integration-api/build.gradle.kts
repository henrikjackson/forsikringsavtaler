plugins {
    id("org.springframework.boot")
    kotlin("jvm")
    kotlin("plugin.spring")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.assertj:assertj-core")
}

kotlin {
    jvmToolchain(21)
}

tasks.test {
    useJUnitPlatform()
}
// --- System Test source set + task ---
val systemTest by sourceSets.creating {
    java.srcDir("src/systemTest/kotlin")
    resources.srcDir("src/systemTest/resources")
    // Ta med main + test output på classpath
    compileClasspath += sourceSets["main"].output + sourceSets["test"].output + configurations.testRuntimeClasspath.get()
    runtimeClasspath += output + compileClasspath
}

// IKKE creating her — kun konfigurer de som allerede finnes
configurations.named("systemTestImplementation") {
    extendsFrom(configurations.testImplementation.get())
}
configurations.named("systemTestRuntimeOnly") {
    extendsFrom(configurations.testRuntimeOnly.get())
}

tasks.register<Test>("systemTest") {
    description = "Runs system (end-to-end) tests"
    group = "verification"
    testClassesDirs = systemTest.output.classesDirs
    classpath = systemTest.runtimeClasspath
    useJUnitPlatform()
    shouldRunAfter("test")
}

tasks.named("check") { dependsOn("systemTest") }