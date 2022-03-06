package design.patterns.observer

internal interface Newsletter<T> {
    fun subscribe(subscriber: NewsletterListener)
    fun unsubscribe(subscriber: NewsletterListener)
    fun notifySubscribers()
}