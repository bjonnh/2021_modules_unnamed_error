plugins {
    application
    kotlin("jvm") version "1.4.21"
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
    implementation("org.bytedeco", "arpack-ng", "3.8.0-1.5.5")
    implementation("com.github.haifengl", "smile-core", "2.5.0") {
        exclude("com.github.haifengl", "smile-netlib")
    }
}

// If we do that we get the same error in 6.8.3 as we do in 7.0
/*java {
    modularity.inferModulePath.set(true)
}*/

tasks.withType<JavaCompile> {
    doFirst {
        options.compilerArgs.addAll(arrayOf("--module-path", classpath.asPath))
        classpath = files()
    }
}

