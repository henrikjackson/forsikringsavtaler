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

val test by sourceSets.creating {
    java.srcDir("src/test/kotlin")
    resources.srcDir("src/test/resources")
    compileClasspath += sourceSets["main"].output + sourceSets["test"].output + configurations.testRuntimeClasspath.get()
    runtimeClasspath += output + compileClasspath
}

configurations.named("testImplementation") {
    extendsFrom(configurations.testImplementation.get())
}
configurations.named("testRuntimeOnly") {
    extendsFrom(configurations.testRuntimeOnly.get())
}

tasks.register<Test>("test") {
    description = "Runs system (end-to-end) tests"
    group = "verification"
    testClassesDirs = test.output.classesDirs
    classpath = test.runtimeClasspath
    useJUnitPlatform()
    shouldRunAfter("test")
}

tasks.named("check") { dependsOn("test") }