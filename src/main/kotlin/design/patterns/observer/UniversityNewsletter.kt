package design.patterns.observer

/**
 * PUBLISHER
 * Implementation of the Publisher interface
 */
internal data class UniversityNewsletter(
    private val subscribers: ArrayList<NewsletterListener> = arrayListOf(),
    private val courseName: String = "",
    private val courseMax: Int = 0,
) : Newsletter<UniversityNewsletter> {

    override fun subscribe(subscriber: NewsletterListener) {
        subscribers.add(subscriber)
    }

    override fun unsubscribe(subscriber: NewsletterListener) {
        subscribers.remove(subscriber)
    }

    override fun notifySubscribers() {
        subscribers.forEach {
            it.update(courseName, courseMax)
        }
    }

    fun updateData(courseName: String, courseMax: Int): UniversityNewsletter {
        return copy(courseName = courseName, courseMax = courseMax).also { it.notifySubscribers() }
    }

}
