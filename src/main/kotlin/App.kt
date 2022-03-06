package design.patterns

class App {
    val greeting: String
        get() {
            return "Hola Universidad Nacional de Costa Rica."
        }
}

fun main() {
    println(App().greeting)
}