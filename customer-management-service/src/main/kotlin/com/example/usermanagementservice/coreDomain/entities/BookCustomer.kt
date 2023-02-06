package com.example.usermanagementservice.coreDomain.entities

import java.time.LocalDate


data class CustomerLoginCredentials constructor(
    val email : String,
    val password: String
)

data class CustomerAddressLine(
    val buildingNumber : Int,
    val streetAddress : String
){
    override fun toString(): String  = "$buildingNumber,$streetAddress"
}

data class CustomerLocation(
    val latitude : Double,
    val longitude : Double
)

class CustomerAddress(
    val line1: CustomerAddressLine,
    val line2: CustomerAddressLine?=null,
    val location : CustomerLocation?=null
){
    override fun toString(): String {
        return "CustomerAddress(addressLine1=$line1, addressLine2=$line2, location=$location)"
    }
}
class CustomerProfile(
    val firstName : String,
    val lastName : String,
    val biography : String?=null,
    val birthdate : LocalDate?=null,
    val phoneNumber : String?=null
){
    override fun toString(): String {
        return "CustomerProfile(firstName='$firstName', lastName='$lastName', biography='$biography', birthdate=$birthdate)"
    }
}

data class BookCustomer constructor(
    val id : String,
    val loginCredentials: CustomerLoginCredentials,
    val profile : CustomerProfile,
    val address: CustomerAddress
) {
    fun hasLogin(
        email: String,
        password: String
    ) : Boolean = (email == this.loginCredentials.email) and (password == this.loginCredentials.password)

    fun hasValidCredentials() : Boolean = hasValidEmail() and hasValidPassword()

    private fun hasValidEmail() : Boolean = this.loginCredentials.email.matches(Regex(".*@.*"))

    private fun hasValidPassword() : Boolean = this.loginCredentials.password.length == 8

    override fun toString(): String {
        return "BookCustomer(id='$id', loginCredentials=$loginCredentials, profile=$profile, address=$address)"
    }

}