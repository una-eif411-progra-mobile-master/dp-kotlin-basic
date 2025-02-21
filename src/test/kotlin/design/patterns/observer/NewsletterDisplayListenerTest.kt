package design.patterns.observer

import com.github.stefanbirkner.systemlambda.SystemLambda
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import java.io.ByteArrayOutputStream
import java.io.PrintStream

/**
 * Unit Tests for the Observer Pattern Implementation
 *
 * This test class verifies the functionality of the University Newsletter system
 * that implements the Observer pattern. It focuses on testing the interaction between
 * the UniversityNewsletter (Subject) and NewsletterDisplayListener (Observer).
 *
 * Test Coverage Areas:
 * - Observer subscription and unsubscription
 * - Notification mechanism
 * - Display output formatting
 * - Cross-platform compatibility
 */
class NewsletterDisplayListenerTest {

    /**
     * Original standard output stream.
     * Stored to restore after tests complete.
     */
    private val standardOut = System.out

    /**
     * Stream for capturing output during tests.
     * Used to verify display outputs.
     */
    private val outputStreamCaptor = ByteArrayOutputStream()

    /**
     * System-specific line separator.
     * Ensures tests work across different operating systems.
     */
    private val lineSeparator = System.getProperty("line.separator")

    /**
     * Test setup method.
     * Redirects system output to our capture stream.
     */
    @BeforeEach
    fun setUp() {
        System.setOut(PrintStream(outputStreamCaptor))
    }

    /**
     * Test cleanup method.
     * Restores original system output and clears the capture buffer.
     */
    @AfterEach
    fun tearDown() {
        System.setOut(standardOut)
        outputStreamCaptor.reset()
    }

    /**
     * Nested test class for organizing Observer pattern tests.
     * Groups related test cases for better readability and organization.
     */
    @Nested
    @DisplayName("When using Newsletter Observer")
    inner class NewsletterObserverTests {

        /**
         * Helper method to normalize line endings for cross-platform compatibility.
         * Converts different line ending styles to a consistent format.
         *
         * @param text The text to normalize
         * @return Normalized text with consistent line endings
         */
        private fun normalizeLineEndings(text: String): String {
            return text.replace("\r\n", "\n").replace("\r", "\n")
        }

        /**
         * Tests notification to multiple displays.
         * Verifies that all subscribed displays receive updates correctly.
         */
        @Test
        @DisplayName("Should notify all displays when new course is opened")
        fun `should notify both displays with the new course opened`() {
            // Given: Initialize newsletter and displays
            val monthlyNewsletter = UniversityNewsletter()
            val mobileDisplay = NewsletterDisplayListener(
                displayName = "Teléfono",
                newsletter = monthlyNewsletter
            )
            val webDisplay = NewsletterDisplayListener(
                displayName = "Pagina Web",
                newsletter = monthlyNewsletter
            )

            // When: Subscribe displays and update course data
            monthlyNewsletter.subscribe(mobileDisplay)
            monthlyNewsletter.subscribe(webDisplay)

            val output = SystemLambda.tapSystemOut {
                monthlyNewsletter.updateData("Progra 2", 30)
            }

            // Then: Verify both displays show correct information
            val expectedLines = listOf(
                "Mostrando en Teléfono [Curso Progra 2 abierto con capacidad de 30]",
                "Mostrando en Pagina Web [Curso Progra 2 abierto con capacidad de 30]"
            )
            val expectedOutput = expectedLines.joinToString(lineSeparator)

            assertEquals(
                normalizeLineEndings(expectedOutput),
                normalizeLineEndings(output.trim()),
                "Both displays should show the updated course information"
            )
        }

        /**
         * Tests the unsubscription mechanism.
         * Verifies that unsubscribed displays do not receive updates.
         */
        @Test
        @DisplayName("Should handle unsubscription correctly")
        fun `should not notify unsubscribed displays`() {
            // Given: Set up newsletter and displays
            val newsletter = UniversityNewsletter()
            val display1 = NewsletterDisplayListener("Display1", newsletter = newsletter)
            val display2 = NewsletterDisplayListener("Display2", newsletter = newsletter)

            // When: Subscribe both but unsubscribe one
            newsletter.subscribe(display1)
            newsletter.subscribe(display2)
            newsletter.unsubscribe(display2)

            val output = SystemLambda.tapSystemOut {
                newsletter.updateData("Test Course", 25)
            }

            // Then: Verify only subscribed display shows information
            val expected = "Mostrando en Display1 [Curso Test Course abierto con capacidad de 25]"
            assertEquals(
                normalizeLineEndings(expected),
                normalizeLineEndings(output.trim()),
                "Only subscribed display should receive notifications"
            )
        }

        /**
         * Tests multiple updates to the same display.
         * Verifies that updates are processed correctly in sequence.
         */
        @Test
        @DisplayName("Should handle multiple updates correctly")
        fun `should process multiple updates correctly`() {
            // Given: Set up newsletter and display
            val newsletter = UniversityNewsletter()
            val display = NewsletterDisplayListener("Display1", newsletter = newsletter)
            newsletter.subscribe(display)

            // When: Perform multiple updates
            val output = SystemLambda.tapSystemOut {
                newsletter.updateData("Course 1", 30)
                newsletter.updateData("Course 2", 25)
            }

            // Then: Verify all updates were displayed correctly
            val expectedLines = listOf(
                "Mostrando en Display1 [Curso Course 1 abierto con capacidad de 30]",
                "Mostrando en Display1 [Curso Course 2 abierto con capacidad de 25]"
            )
            val expectedOutput = expectedLines.joinToString(lineSeparator)

            assertEquals(
                normalizeLineEndings(expectedOutput),
                normalizeLineEndings(output.trim()),
                "Display should show all updates in sequence"
            )
        }
    }
}

/**
 * TEST SUITE DOCUMENTATION
 *
 * Purpose:
 * This test suite validates the Observer pattern implementation in the university
 * newsletter system. It ensures that the communication between the newsletter
 * (Subject) and displays (Observers) works correctly.
 *
 * Key Test Areas:
 * 1. Multiple Observer Notification
 *    - Verifies all subscribed observers receive updates
 *    - Checks correct formatting of display output
 *
 * 2. Unsubscription Handling
 *    - Ensures observers can be removed properly
 *    - Verifies removed observers don't receive updates
 *
 * 3. Update Processing
 *    - Tests multiple sequential updates
 *    - Validates update content accuracy
 *
 * Testing Approach:
 * - Uses SystemLambda for output capture
 * - Implements cross-platform compatibility
 * - Follows Given-When-Then pattern
 * - Provides clear error messages
 *
 * Cross-Platform Notes:
 * The test suite handles different operating systems by:
 * - Using system-specific line separators
 * - Normalizing line endings for comparison
 * - Avoiding platform-specific assumptions
 */