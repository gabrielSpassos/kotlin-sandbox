package com.gabrielspassos

class Contact (val id: Long, var email: String) {
    fun sendEmail() {
        println("Sent email to $email")
    }
}