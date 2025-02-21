package design.patterns.design.patterns.singleton

/**
 * Singleton Pattern Implementation
 *
 * This is a demonstration of the Singleton pattern using Kotlin's object declaration.
 * The Singleton pattern ensures a class has only one instance and provides a global point
 * of access to that instance.
 *
 * Key Features:
 * - Thread-safe instance creation (guaranteed by Kotlin's object declaration)
 * - Lazy initialization (handled by Kotlin runtime)
 * - Global state management
 * - Immutable instance
 *
 * Usage Example:
 * ```
 * Singleton.courseName = "New Course"
 * Singleton.printName()
 * ```
 *
 * @property courseName The current course name, with custom setter for state tracking
 */
object Singleton {
    // Initialization block - executed only once when the singleton is first accessed
    init {
        println("[SE INVOCA LA CLASE SOLO LA PRIMERA VEZ]")
    }

    /**
     * Course name property with custom setter for state tracking.
     * Demonstrates proper encapsulation with custom accessor.
     */
    var courseName: String = "Programación App Móvil"
        set(value) {
            field = value
            // Automatically print the new name when it's updated
            printName()
        }

    /**
     * Tracks the number of times the course name has been changed.
     * Demonstrates state tracking in a Singleton.
     */
    private var changeCount: Int = 0
        private set

    /**
     * Prints the current course name.
     * Also demonstrates usage of string templates in Kotlin.
     */
    fun printName() {
        println(courseName)
    }

    /**
     * Returns the number of times the course name has been changed.
     * @return The number of state changes
     */
    fun getChangeCount(): Int = changeCount

    /**
     * Resets the singleton to its initial state.
     * Useful for testing or when a fresh state is needed.
     */
    fun reset() {
        courseName = "Programación App Móvil"
        changeCount = 0
    }

    /**
     * Returns the current state of the Singleton as a string.
     * Useful for debugging and logging.
     */
    override fun toString(): String {
        return "Singleton(courseName='$courseName', changes=$changeCount)"
    }
}

/**
 * Example class demonstrating Singleton usage.
 * This class automatically interacts with the Singleton upon initialization.
 */
class MyClass {
    /**
     * Initialization block that demonstrates Singleton usage.
     * Executes when a new instance of MyClass is created.
     */
    init {
        println("[MÉTODO INICIAL]")
        Singleton.printName()
    }

    /**
     * Returns the current course name from the Singleton.
     * Demonstrates proper Singleton access from another class.
     */
    fun getCurrentCourse(): String = Singleton.courseName
}