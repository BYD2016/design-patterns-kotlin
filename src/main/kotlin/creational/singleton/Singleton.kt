/**
 The singleton pattern ensures that only one object of a particular
 class is ever created. All further references to objects of the
 singleton class refer to the same underlying instance. There are
 very few applications, do not overuse this pattern!
 */

object PrinterDriver {
    init {
        println("Initializing with object: $this")
    }

    fun print() = println("Printing with object: $this")
}

fun main(args: Array<String>) {
    println("Start")
    PrinterDriver.print()
    PrinterDriver.print()
}
