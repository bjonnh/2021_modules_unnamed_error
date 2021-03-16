/*
 * BCMStudio - A Biochemometrics Analytics Platform
 *     Copyright (C) 2019 Jonathan Bisson
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.util.*


plugins {
    kotlin("jvm") version "1.4.31"
    application
    id("de.jjohannes.extra-java-module-info") version "0.6"
}

group = "net.nprod"

val version = "0.0.1"

repositories {
    mavenCentral()
}

application {
    mainModule.set("demo")
    mainClass.set("net.nprod.demo.MainKt")
}

val compileKotlin: org.jetbrains.kotlin.gradle.tasks.KotlinCompile by tasks
val compileJava: JavaCompile by tasks
compileKotlin.destinationDir = compileJava.destinationDir

dependencies {
    implementation(kotlin("stdlib"))

    implementation("com.github.haifengl", "smile-math", "2.5.0") {
        exclude("com.github.haifengl", "smile-netlib")
    }

}


tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "13"
}

// Use the new compiler
tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        useIR = true
    }
}

kotlin {
    sourceSets.all {
        languageSettings.apply {
            useExperimentalAnnotation("kotlin.time.ExperimentalTime")
        }
    }
}

tasks.withType<JavaCompile> {
    modularity.inferModulePath.set(true)
}

extraJavaModuleInfo {
    failOnMissingModuleInfo.set(false) // Useful if you need to find which modules you may have forgotten
    automaticModule("smile-math-2.5.0.jar", "smile.math")
}
