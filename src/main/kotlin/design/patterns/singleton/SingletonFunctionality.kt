package design.patterns.design.patterns.singleton

/**
 * Singleton Object in Kotlin
 */
object Singleton {
    init {
        println("[SE INVOCA LA CLASE SOLO LA PRIMERA VEZ]")
    }
    var courseName = "Programación App Móvil"
    fun printName() {
        println(courseName)
    }
}

class MyClass {
    init {
        println("[MÉTODO INICIAL]")
        Singleton.printName()
    }
}