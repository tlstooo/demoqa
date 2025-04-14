plugins {
    id("java")
}

group = "guru.qa.tlsto"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("com.codeborne:selenide:7.8.1")
}

tasks.test {
    useJUnitPlatform()
}