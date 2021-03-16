package net.nprod.demo

// A demonstration of a module that does not have a module-info.java so we have to generate it
// with https://github.com/jjohannes/extra-java-module-info
import smile.math.special.Erf
import kotlin.math.absoluteValue

fun main() {
    require(Erf.erfc(1.0).minus(0.157299).absoluteValue < 0.0001)
    println("If you see that it is working")
}
