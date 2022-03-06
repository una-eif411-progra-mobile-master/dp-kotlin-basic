package design.patterns.observer

import com.github.stefanbirkner.systemlambda.SystemLambda
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class NewsletterDisplayListenerTest {
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
    fun `should notify both displays with the new course opened`() {
        val monthlyNewsletter = UniversityNewsletter()
        val mobileDisplay = NewsletterDisplayListener(displayName = "Teléfono", newsletter = monthlyNewsletter)
        val webDisplay = NewsletterDisplayListener(displayName = "Pagina Web", newsletter = monthlyNewsletter)

        monthlyNewsletter.subscribe(mobileDisplay)
        monthlyNewsletter.subscribe(webDisplay)

        val output = SystemLambda.tapSystemOut {
            monthlyNewsletter.updateData("Progra 2", 30)
        }

        Assertions.assertEquals(
            "Mostrando en Teléfono [Curso Progra 2 abierto con capacidad de 30]\n"
                    + "Mostrando en Pagina Web [Curso Progra 2 abierto con capacidad de 30]",
            output.trim()
        )

    }
}