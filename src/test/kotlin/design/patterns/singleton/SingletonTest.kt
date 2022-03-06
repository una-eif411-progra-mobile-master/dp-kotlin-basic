package design.patterns.singleton

import com.github.stefanbirkner.systemlambda.SystemLambda
import design.patterns.design.patterns.singleton.MyClass
import design.patterns.design.patterns.singleton.Singleton
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.assertEquals

class SingletonTest {
    private val standardOut = System.out
    private val outputStreamCaptor = ByteArrayOutputStream()

    @BeforeEach
    fun setUp() {
        System.setOut(PrintStream(outputStreamCaptor))
    }

    @AfterEach
    fun tearDown() {
        System.setOut(standardOut)
    }

    @Test
    fun `testing a class with only one instance`() {
        val output = SystemLambda.tapSystemOut {
            Singleton.printName()
            Singleton.courseName = "Programación Web"

            var myClass1 = MyClass()

            Singleton.courseName = "Programación Funcional"

            var myClass2 = MyClass()
        }

        assertEquals("[SE INVOCA LA CLASE SOLO LA PRIMERA VEZ]\n" +
                "Programación App Móvil\n" +
                "[MÉTODO INICIAL]\n" +
                "Programación Web\n" +
                "[MÉTODO INICIAL]\n" +
                "Programación Funcional\n",output)
    }
}