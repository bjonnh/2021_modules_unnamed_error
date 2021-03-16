plugins {
    kotlin("jvm") version "1.4.31"
    application
    id("de.jjohannes.extra-java-module-info") version "0.6"
}

group = "net.nprod"
version = "0.1"

application {
    mainModule.set("demo")
    mainClass.set("net.nprod.demo.MainKt")
}

// https://stackoverflow.com/questions/47657755/building-a-kotlin-java-9-project-with-gradle
// We want to do that to not have to declare empty java packages just for module-info.java

val compileKotlin: org.jetbrains.kotlin.gradle.tasks.KotlinCompile by tasks
val compileJava: JavaCompile by tasks
compileKotlin.destinationDir = compileJava.destinationDir


repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))

    implementation("com.github.haifengl", "smile-math", "2.5.0") {
        // Excluding these for the demo as they are rather large and not needed for the demonstration
        exclude("com.github.haifengl", "smile-netlib")
        exclude("org.bytedeco", "arpack-ng")
        exclude("org.bytedeco", "openblas")
        exclude("org.bytedeco", "javacpp")
    }
}

// This is only needed if you use Gradle < 7.0 (but doesn't hurt if you use 7.0 as it is the default)

tasks.withType<JavaCompile> {
    modularity.inferModulePath.set(true)
}

extraJavaModuleInfo {
    failOnMissingModuleInfo.set(false) // Useful if you need to find which modules you may have forgotten
    automaticModule("smile-math-2.5.0.jar", "smile.math")
}
