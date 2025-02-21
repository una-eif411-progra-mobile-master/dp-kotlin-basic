package design.patterns.singleton

/**
 * UNIT TESTING EXAMPLE: SINGLETON PATTERN
 *
 * Purpose:
 * This test class demonstrates how to properly test a Singleton pattern implementation
 * in Kotlin. It serves as an educational example of:
 * - Unit test structure and organization
 * - Test documentation best practices
 * - System output testing
 * - Cross-platform compatibility
 * - JUnit 5 features
 *
 * Key Concepts Demonstrated:
 * 1. Test Setup and Teardown
 * 2. Test Case Organization
 * 3. Platform Independence
 * 4. Output Validation
 * 5. Singleton Pattern Verification
 *
 * @author Your Name
 * @version 1.0
 * @see Singleton
 * @see MyClass
 */

import com.github.stefanbirkner.systemlambda.SystemLambda
import design.patterns.design.patterns.singleton.MyClass
import design.patterns.design.patterns.singleton.Singleton
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.assertEquals
import kotlin.test.assertSame

@DisplayName("Singleton Pattern Tests")
class SingletonTest {
    // SECTION: Test Environment Setup

    /**
     * Stores the original System.out PrintStream to restore it after tests
     * This is important to prevent affecting other tests in the suite
     */
    private val standardOut = System.out

    /**
     * Captures system output during tests
     * Used to verify what would normally be printed to console
     */
    private val outputStreamCaptor = ByteArrayOutputStream()

    /**
     * Platform-specific line separator
     * Ensures tests work correctly on different operating systems
     */
    private val lineSeparator = System.lineSeparator()

    /**
     * Test Setup Method
     * Executed before each test case
     *
     * Purpose:
     * 1. Redirects system output to our capturer
     * 2. Resets Singleton to initial state
     */
    @BeforeEach
    fun setUp() {
        // Redirect system output to our capturer
        System.setOut(PrintStream(outputStreamCaptor))
        // Reset singleton to known initial state
        Singleton.courseName = "Programación App Móvil"
    }

    /**
     * Test Cleanup Method
     * Executed after each test case
     *
     * Purpose:
     * 1. Restores original system output
     * 2. Clears output capturer buffer
     */
    @AfterEach
    fun tearDown() {
        System.setOut(standardOut)
        outputStreamCaptor.reset()
    }

    // SECTION: Test Cases

    /**
     * Nested test class for Singleton behavior
     * Groups related test cases for better organization
     */
    @Nested
    @DisplayName("When using Singleton")
    inner class SingletonBehavior {

        /**
         * Test Case 1: State Management
         *
         * Purpose:
         * Verifies that the Singleton maintains consistent state across multiple
         * class instances and properly outputs state changes.
         *
         * Test Steps:
         * 1. Print initial state
         * 2. Change state and create first instance
         * 3. Change state again and create second instance
         * 4. Verify complete output sequence
         */
        @Test
        @DisplayName("Should maintain single instance state across multiple classes")
        fun `should maintain single instance state`() {
            // GIVEN: A clean Singleton instance (handled by setUp)

            // WHEN: Execute the test sequence
            val output = SystemLambda.tapSystemOut {
                // Step 1: Initial state verification
                Singleton.printName()

                // Step 2: First state change
                Singleton.courseName = "Programación Web"
                val myClass1 = MyClass()

                // Step 3: Second state change
                Singleton.courseName = "Programación Funcional"
                val myClass2 = MyClass()
            }

            // THEN: Verify output matches expected sequence
            // Define expected output lines
            val expectedLines = listOf(
                "Programación App Móvil",
                "Programación Web",
                "[MÉTODO INICIAL]",
                "Programación Web",
                "Programación Funcional",
                "[MÉTODO INICIAL]",
                "Programación Funcional"
            )

            // Create platform-independent expected output
            val expectedOutput = expectedLines.joinToString(lineSeparator) + lineSeparator

            // Normalize line endings for platform independence
            val normalizedExpected = expectedOutput.replace("\r\n", "\n")
            val normalizedActual = output.replace("\r\n", "\n")

            // Verify output matches expectations
            assertEquals(
                normalizedExpected,
                normalizedActual,
                "Output sequence should match expected pattern regardless of platform"
            )
        }

        /**
         * Test Case 2: Instance Unity
         *
         * Purpose:
         * Verifies that multiple references to the Singleton
         * actually point to the same instance in memory.
         *
         * This is a critical test for the Singleton pattern as it
         * ensures the fundamental "single instance" requirement.
         */
        @Test
        @DisplayName("Should have only one instance")
        fun `should verify single instance`() {
            // GIVEN: Two references to the Singleton
            val instance1 = Singleton
            val instance2 = Singleton

            // THEN: Verify both references point to same instance
            assertSame(
                instance1,
                instance2,
                "Multiple Singleton references must point to the same instance"
            )
        }

        /**
         * Test Case 3: State Updates
         *
         * Purpose:
         * Verifies that the Singleton properly stores and
         * returns state updates.
         *
         * This test ensures the basic functionality of
         * state management within the Singleton.
         */
        @Test
        @DisplayName("Should update course name correctly")
        fun `should update course name`() {
            // GIVEN: A new course name
            val newCourseName = "New Course"

            // WHEN: Update the Singleton's state
            Singleton.courseName = newCourseName

            // THEN: Verify state was updated correctly
            assertEquals(
                newCourseName,
                Singleton.courseName,
                "Singleton should store and return the updated course name"
            )
        }
    }
}

/**
 * LEARNING OUTCOMES
 *
 * After studying this test class, students should understand:
 *
 * 1. Test Structure:
 *    - How to organize tests using @Nested classes
 *    - Proper use of @BeforeEach and @AfterEach
 *    - How to write clear test case descriptions
 *
 * 2. Testing Practices:
 *    - How to capture and verify system output
 *    - How to handle platform-specific issues
 *    - How to write clear assertions with meaningful messages
 *
 * 3. Documentation:
 *    - How to write clear KDoc comments
 *    - How to document test purposes and steps
 *    - How to organize code sections logically
 *
 * 4. Singleton Pattern Testing:
 *    - How to verify single instance behavior
 *    - How to test state management
 *    - How to verify behavior across multiple classes
 */