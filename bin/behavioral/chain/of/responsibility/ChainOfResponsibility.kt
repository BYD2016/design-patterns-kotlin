package behavioral.chain.of.responsibility

/**
 The chain of responsibility pattern is used to process varied
 requests, each of which may be dealt with by a different handler.
 */

interface MessageChain {
    fun addLines(upStream: String): String
}

class AuthenticationHeader(val token: String?, var next: MessageChain? = null) : MessageChain {

    override fun addLines(upStream: String): String {
        token ?: throw IllegalStateException("Token should be not null")
        return "$upStream Authorization: Bearer $token\n".let { next?.addLines(it) ?: it }
    }
}

class ContentTypeHeader(val contentType: String, var next: MessageChain? = null) : MessageChain {

    override fun addLines(upStream: String): String
            = "$upStream ContentType: $contentType\n".let { next?.addLines(it) ?: it }
}

class BodyPayload(val body: String, var next: MessageChain? = null) : MessageChain {

    override fun addLines(upStream: String): String
            = "$upStream $body\n".let { next?.addLines(it) ?: it }
}

fun main(args: Array<String>) {
    val authenticationHeader = AuthenticationHeader("123456")
    val contentTypeHeader = ContentTypeHeader("json")
    val messageBody = BodyPayload("{\"username\"=\"dbacinski\"}")

    val messageChainWithAuthorization = messageChainWithAuthorization(authenticationHeader, contentTypeHeader, messageBody)
    val messageWithAuthentication = messageChainWithAuthorization.addLines("Message with Authentication:\n")
    println(messageWithAuthentication)

    val messageChainUnauthenticated = messageChainUnauthenticated(contentTypeHeader, messageBody)
    val message = messageChainUnauthenticated.addLines("Message:\n")
    println(message)
}

private fun messageChainWithAuthorization(authenticationHeader: AuthenticationHeader, contentTypeHeader: ContentTypeHeader, messageBody: BodyPayload): MessageChain {
    authenticationHeader.next = contentTypeHeader
    contentTypeHeader.next = messageBody
    return authenticationHeader
}

private fun messageChainUnauthenticated(contentTypeHeader: ContentTypeHeader, messageBody: BodyPayload): MessageChain {
    contentTypeHeader.next = messageBody
    return contentTypeHeader
}



