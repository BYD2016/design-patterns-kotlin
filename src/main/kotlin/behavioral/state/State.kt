package behavioral.state

/**
 The state pattern is used to alter the behaviour of an object as
 its internal state changes. The pattern allows the class for an
 object to apparently change at run-time.
 */

sealed class AuthorizationState {

    class Unauthorized : AuthorizationState()

    class Authorized(val userName: String) : AuthorizationState()
}

class AuthorizationPresenter {

    private var state: AuthorizationState = AuthorizationState.Unauthorized()

	val isAuthorized: Boolean
        get() {
            when (state) {
                is AuthorizationState.Authorized -> return true
                else -> return false
            }
        }

    val userLogin: String
        get() {
            when (state) {
                is AuthorizationState.Authorized -> return (state as AuthorizationState.Authorized).userName
                is AuthorizationState.Unauthorized -> return "Unknown"
            }
        }
	
    fun loginUser(userLogin: String) {
        this.state = AuthorizationState.Authorized(userLogin)
    }

    fun logoutUser() {
        this.state = AuthorizationState.Unauthorized()
    }

    

    override fun toString(): String {
        return "User '$userLogin' is logged in: $isAuthorized"
    }
}

fun main(args: Array<String>) {
    val authorization = AuthorizationPresenter()
    authorization.loginUser("admin")
    println(authorization)
	
    authorization.logoutUser()
    println(authorization)
}