package com.gabrielspassos

interface Status {
    fun signal(): String {
        return "Signal Done"
    }
}

interface NotFound: Status
class NotFoundImpl: NotFound

interface Authorized: Status
class AuthorizedImpl: Authorized

interface Denied: Status
class DeniedImpl: Denied

fun checkStatus(status: Any) {
    if (status is Authorized || status is Denied) {
        println("Check status=$status ${status.signal()}")
    } else {
        println("Not check for status=$status")
    }
}

fun main() {
    val notFound = NotFoundImpl()
    val authorized = AuthorizedImpl()
    val denied = DeniedImpl()

    checkStatus(notFound)
    checkStatus(authorized)
    checkStatus(denied)
}