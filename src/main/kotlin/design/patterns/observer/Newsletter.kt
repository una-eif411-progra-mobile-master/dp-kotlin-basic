// Newsletter.kt
package design.patterns.observer

/**
 * Newsletter Interface (Subject/Observable)
 *
 * This interface defines the core functionality of the Observable (Publisher) in the Observer pattern.
 * It provides methods to manage subscriptions and notify subscribers of changes.
 *
 * Key Features:
 * - Subscription management (subscribe/unsubscribe)
 * - Notification mechanism
 * - Generic type support for flexibility
 *
 * @param T The type of the newsletter implementation
 */
interface Newsletter<T> {
    /**
     * Adds a new subscriber to the newsletter
     * @param subscriber The listener to be added
     */
    fun subscribe(subscriber: NewsletterListener)

    /**
     * Removes a subscriber from the newsletter
     * @param subscriber The listener to be removed
     */
    fun unsubscribe(subscriber: NewsletterListener)

    /**
     * Notifies all subscribers about updates
     * This method should be called whenever the newsletter's state changes
     */
    fun notifySubscribers()
}