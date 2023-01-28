package com.example.usermanagementservice.coreDomain.entities

class DomainUser constructor(
    val id : String,
    val name : String,
    val email : String,
    val password : String
) {

    fun verifyLogin(
        email: String,
        password: String
    ) : Boolean = (email == this.email) and (password == this.password)

    fun hasValidEmail() : Boolean = this.email.matches(Regex(".*@.*"))

    fun hasValidPassword() : Boolean = this.password.length == 8


    override fun toString(): String {
        return "DomainUser(id='$id', name='$name', email='$email', password='$password')"
    }

}