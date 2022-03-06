package design.patterns.observer

/**
 * SUBSCRIBER
 * This class is the Subscriber and Display
 */
internal data class NewsletterDisplayListener(
    private val displayName: String = "",
    private val courseName: String = "",
    private val courseMax: Int = 0,
    private val newsletter: UniversityNewsletter,
) :  Display, NewsletterListener {

    override fun update(courseName: String, courseMax: Int): NewsletterDisplayListener {
        val updateInformation = copy(courseName=courseName, courseMax=courseMax)
        display(updateInformation.courseName, updateInformation.courseMax)
        return updateInformation
    }

    override fun display(courseName: String, courseMax: Int) {
        println("Mostrando en $displayName [Curso $courseName abierto con capacidad de $courseMax]")
    }
}
