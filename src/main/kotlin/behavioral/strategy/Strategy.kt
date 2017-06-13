package behavioral.strategy

/**
 The strategy pattern is used to create an interchangeable family
 of algorithms from which the required process is chosen at run-time.
 */
 

class Printer(val stringFormatterStrategy: (String) -> String) {
    fun printString(string: String) = println(stringFormatterStrategy.invoke(string))
}

val lowerCaseFormatter: (String) -> String = { it.toLowerCase() }

val upperCaseFormatter = { it: String -> it.toUpperCase() }

fun main(args: Array<String>) {

    val lowerCasePrinter = Printer(lowerCaseFormatter)
    lowerCasePrinter.printString("LOREM ipsum DOLOR sit amet")

    val upperCasePrinter = Printer(upperCaseFormatter)
    upperCasePrinter.printString("LOREM ipsum DOLOR sit amet")

    val prefixPrinter = Printer({ "Prefix: $it" })
    prefixPrinter.printString("LOREM ipsum DOLOR sit amet")
}