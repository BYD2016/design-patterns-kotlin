package structural.decorator

/**
 The decorator pattern is used to extend or alter the functionality
 of objects at run-time by wrapping them in an object of a
 decorator class. This provides a flexible alternative to using
 inheritance to modify behaviour.
 */

interface CoffeeMachine {
    fun makeSmallCoffee()
    fun makeLargeCoffee()
}

class NormalCoffeeMachine : CoffeeMachine {
    override fun makeSmallCoffee() = println("Normal: Making small coffee")

    override fun makeLargeCoffee() = println("Normal: Making large coffee")
}

//Decorator:
class EnhancedCoffeeMachine(val coffeeMachine: CoffeeMachine) : CoffeeMachine by coffeeMachine {

    fun makeCoffeeWithMilk() {
        println("Enhanced: Making coffee with milk")
        coffeeMachine.makeSmallCoffee()
        println("Enhanced: Adding milk")
    }

    fun makeDoubleLargeCoffee() {
        println("Enhanced: Making double large coffee")
        coffeeMachine.makeLargeCoffee()
        coffeeMachine.makeLargeCoffee()
    }
}

fun main(args: Array<String>) {
    val normalMachine = NormalCoffeeMachine()
    val enhancedMachine = EnhancedCoffeeMachine(normalMachine)

    enhancedMachine.makeCoffeeWithMilk()

    enhancedMachine.makeDoubleLargeCoffee()
}
