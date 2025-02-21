// Display.kt
package design.patterns.observer

/**
 * Display Interface
 *
 * This interface defines the contract for displaying course information.
 * It's part of the Observer pattern implementation, specifically used by
 * concrete observers to standardize how they display updates.
 *
 * @property display Method to show course information
 */
interface Display {
    /**
     * Displays the course information in a specific format
     *
     * @param courseName The name of the course to display
     * @param courseMax The maximum capacity of the course
     */
    fun display(courseName: String, courseMax: Int)
}