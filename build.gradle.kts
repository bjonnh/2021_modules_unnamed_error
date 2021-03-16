plugins {
    application
    kotlin("jvm") version "1.4.21"
    //id("org.javamodularity.moduleplugin") version "1.7.0"
   // id("ch.tutteli.kotlin.module.info") version "0.33.1"
}

group = "org.example"
version = "1.0-SNAPSHOT"

val compileKotlin: org.jetbrains.kotlin.gradle.tasks.KotlinCompile by tasks
val compileJava: JavaCompile by tasks
//compileJava.destinationDir = compileKotlin.destinationDir

application {
    mainModule.set("demo")
    mainClass.set("net.nprod.demo.MainKt")
}


repositories {
    mavenCentral()
}

dependencies {
    implementation("org.bytedeco", "arpack-ng", "3.8.0-1.5.5")
    implementation("com.github.haifengl", "smile-core", "2.5.0") {
        exclude("com.github.haifengl", "smile-netlib")
    }
}

/*java {
    modularity.inferModulePath.set(true)
}*/

tasks.withType<JavaCompile> {
    //dependsOn(":compileKotlin")
    inputs.property("moduleName", "demo")
    doFirst {
        options.compilerArgs.addAll(arrayOf("--module-path", classpath.asPath,
       "--patch-module", "org.module.kotlin=${sourceSets.main.get().output.asPath}" ))
        classpath = files()
    }
}
/*
tasks.withType<org.gradle.jvm.tasks.Jar> {
    duplicatesStrategy = org.gradle.api.file.DuplicatesStrategy.EXCLUDE
}*/
