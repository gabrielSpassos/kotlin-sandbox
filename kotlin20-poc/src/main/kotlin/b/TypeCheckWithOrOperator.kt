package com.gabrielspassos.b

interface ErrorStatus {
    val statusCode: Int

    fun signal(): String {
        return "Error Signal, code $statusCode"
    }

}

interface Ok
class OkImpl: Ok

interface NotFound: ErrorStatus
class NotFoundImpl: NotFound {
    override val statusCode: Int = 404
}

interface Authorized: ErrorStatus
class AuthorizedImpl: Authorized {
    override val statusCode: Int = 401
}

interface Denied: ErrorStatus
class DeniedImpl: Denied {
    override val statusCode: Int = 403
}

fun checkStatus(status: Any) {
    if (status is Authorized || status is Denied || status is NotFound) {
        println("Check status=${status.javaClass.simpleName} ${status.signal()}") // smart-cast improvement
    } else {
        println("Not error status=${status.javaClass.simpleName}")
    }
}

fun main() {
    val notFound = NotFoundImpl()
    val authorized = AuthorizedImpl()
    val denied = DeniedImpl()
    val ok = OkImpl()

    checkStatus(notFound)
    checkStatus(authorized)
    checkStatus(denied)
    checkStatus(ok)
}