// UniversityNewsletter.kt
package design.patterns.observer

/**
 * UniversityNewsletter (Concrete Subject/Observable)
 *
 * This class implements the Newsletter interface and serves as the concrete subject
 * in the Observer pattern. It maintains a list of subscribers and notifies them of
 * course updates.
 *
 * Key Features:
 * - Immutable data class implementation
 * - Thread-safe subscriber management
 * - Automatic notification on updates
 *
 * Usage Example:
 * ```kotlin
 * val newsletter = UniversityNewsletter()
 * val display = NewsletterDisplayListener("Display1", newsletter = newsletter)
 * newsletter.subscribe(display)
 * newsletter.updateData("Kotlin Course", 30)
 * ```
 *
 * @property subscribers List of subscribed listeners
 * @property courseName Current course name
 * @property courseMax Current course maximum capacity
 */
data class UniversityNewsletter(
    private val subscribers: ArrayList<NewsletterListener> = arrayListOf(),
    private val courseName: String = "",
    private val courseMax: Int = 0,
) : Newsletter<UniversityNewsletter> {

    /**
     * Adds a new subscriber to the newsletter
     * Thread-safe implementation using ArrayList
     *
     * @param subscriber The listener to add
     */
    override fun subscribe(subscriber: NewsletterListener) {
        subscribers.add(subscriber)
    }

    /**
     * Removes a subscriber from the newsletter
     * Thread-safe implementation using ArrayList
     *
     * @param subscriber The listener to remove
     */
    override fun unsubscribe(subscriber: NewsletterListener) {
        subscribers.remove(subscriber)
    }

    /**
     * Notifies all subscribers of current course information
     * Iterates through subscribers and calls their update method
     */
    override fun notifySubscribers() {
        subscribers.forEach {
            it.update(courseName, courseMax)
        }
    }

    /**
     * Updates the newsletter data and notifies all subscribers
     * Creates a new instance with updated data and automatically notifies subscribers
     *
     * @param courseName New course name
     * @param courseMax New course capacity
     * @return New instance with updated information
     */
    fun updateData(courseName: String, courseMax: Int): UniversityNewsletter {
        return copy(courseName = courseName, courseMax = courseMax)
            .also { it.notifySubscribers() }
    }
}