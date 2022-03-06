package design.patterns.observer

/**
 * This is the observer interface with the ability to register
 * or remove (subscribe or unsubscribe) the observable objects
 * and with the method for all
 */
internal interface Newsletter<T> {
    fun subscribe(subscriber: NewsletterListener)
    fun unsubscribe(subscriber: NewsletterListener)
    fun notifySubscribers()
}