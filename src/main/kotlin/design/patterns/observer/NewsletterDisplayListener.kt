// NewsletterDisplayListener.kt
package design.patterns.observer

/**
 * NewsletterDisplayListener (Concrete Observer)
 *
 * This class implements both the Observer (NewsletterListener) and Display interfaces.
 * It represents a specific type of subscriber that displays course updates in a formatted way.
 *
 * Key Features:
 * - Immutable data class implementation
 * - Combines observer and display functionality
 * - Custom display format for each instance
 *
 * @property displayName Name of the display device/location
 * @property courseName Current course name
 * @property courseMax Current course capacity
 * @property newsletter Reference to the newsletter being observed
 */
data class NewsletterDisplayListener(
    private val displayName: String = "",
    private val courseName: String = "",
    private val courseMax: Int = 0,
    private val newsletter: UniversityNewsletter,
) : Display, NewsletterListener {

    /**
     * Updates the listener with new course information
     * Creates a new instance with updated data and displays it
     *
     * @param courseName New course name
     * @param courseMax New course capacity
     * @return New instance with updated information
     */
    override fun update(courseName: String, courseMax: Int): NewsletterDisplayListener {
        val updateInformation = copy(courseName = courseName, courseMax = courseMax)
        display(updateInformation.courseName, updateInformation.courseMax)
        return updateInformation
    }

    /**
     * Displays the course information in a formatted way
     * Format: "Mostrando en [DisplayName] [Curso [CourseName] abierto con capacidad de [CourseMax]]"
     *
     * @param courseName Course name to display
     * @param courseMax Course capacity to display
     */
    override fun display(courseName: String, courseMax: Int) {
        println("Mostrando en $displayName [Curso $courseName abierto con capacidad de $courseMax]")
    }
}