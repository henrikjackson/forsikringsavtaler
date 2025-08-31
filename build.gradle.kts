plugins {
    kotlin("jvm") version "2.0.0" apply false
    id("org.springframework.boot") version "3.3.2" apply false
    id("io.spring.dependency-management") version "1.1.5"
    kotlin("plugin.spring") version "2.0.0" apply false
}

allprojects {
    group = "no.bedrift"
    version = "0.0.1-SNAPSHOT"

    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "io.spring.dependency-management")
}
