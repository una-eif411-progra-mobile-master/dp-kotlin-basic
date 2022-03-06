package design.patterns.observer

interface NewsletterListener {
    fun update (courseName: String, courseMax: Int): NewsletterListener
}