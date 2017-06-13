package structural.facade

/*
 The facade pattern is used to define a simplified interface to a
 more complex subsystem.
 */

import java.util.*

class ComplexSystemStore(val filePath: String) {
	
	val store = HashMap<String, String>()

    init {
        println("Reading data from file: $filePath")
    }

    fun store(key: String, payload: String) {
        this.store.put(key, payload)
    }

    fun read(key: String): String = store[key] ?: ""

    fun commit() = println("Storing cached data: $store to file: $filePath")
}

data class User(val login: String)

//Facade:
class UserRepository {
    val systemPreferences = ComplexSystemStore("/data/default.prefs")

    fun save(user: User) {
        this.systemPreferences.apply {
			store("USER_KEY", user.login)
			commit()
		} 
    }

    fun findFirst(): User = User(systemPreferences.read("USER_KEY"))
}

fun main(args: Array<String>) {
    val userRepository = UserRepository()
    val user = User("dbacinski")
    userRepository.save(user)
    val resultUser = userRepository.findFirst()
    println("Found stored user: $resultUser")
}