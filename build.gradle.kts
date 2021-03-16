plugins {
    application
    kotlin("jvm") version "1.4.21"
    id("de.jjohannes.extra-java-module-info") version "0.6"
}

group = "org.example"
version = "1.0-SNAPSHOT"

application {
    mainModule.set("demo")
    mainClass.set("net.nprod.demo.MainKt")
}
repositories {
    mavenCentral()
}

dependencies {
    //implementation("org.bytedeco", "arpack-ng", "3.8.0-1.5.5")
    implementation("com.github.haifengl", "smile-math", "2.5.0") {
        // Excluding these for the demo as they are rather large and not needed for the demonstration
        exclude("com.github.haifengl", "smile-netlib")
        exclude("org.bytedeco", "arpack-ng")
        exclude("org.bytedeco", "openblas")
        exclude("org.bytedeco", "javacpp")
    }
}

// This is only needed if you use Gradle < 7.0 (but doesn't hurt if you use 7.0 as it is the default)
java {
    modularity.inferModulePath.set(true)
}

extraJavaModuleInfo {
    failOnMissingModuleInfo.set(false) // Useful if you need to find which modules you may have forgotten
    automaticModule("smile-math-2.5.0.jar", "smile.math")
}
