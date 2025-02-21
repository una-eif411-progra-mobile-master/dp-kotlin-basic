// NewsletterListener.kt
package design.patterns.observer

/**
 * NewsletterListener Interface (Observer)
 *
 * This interface defines the contract for observers in the Observer pattern.
 * It specifies how observers will receive updates from the subject.
 *
 * Key Features:
 * - Update method for receiving notifications
 * - Returns updated instance for immutability
 */
interface NewsletterListener {
    /**
     * Receives updates from the subject (Newsletter)
     *
     * @param courseName Updated course name
     * @param courseMax Updated maximum course capacity
     * @return Updated instance of the listener
     */
    fun update(courseName: String, courseMax: Int): NewsletterListener
}