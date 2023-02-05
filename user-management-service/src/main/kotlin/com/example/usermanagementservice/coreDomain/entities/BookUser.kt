package com.example.usermanagementservice.coreDomain.entities

import com.example.usermanagementservice.coreDomain.services.DomainPasswordEncodingService
import java.time.LocalDate



data class BookUserLoginCredentials constructor(
    val email : String,
    val password: String
)

data class AddressLine(
    val buildingNumber : Int,
    val streetAddress : String
){
    override fun toString(): String  = "$buildingNumber,$streetAddress"
}

data class BookUserLocation(
    val latitude : Double,
    val longitude : Double
)

class BookUserAddress(
 val line1: AddressLine,
 val line2: AddressLine?=null,
 val location : BookUserLocation?=null
){
    override fun toString(): String {
        return "BookUserAddress(addressLine1=$line1, addressLine2=$line2, location=$location)"
    }
}
class BookUserProfile(
    val firstName : String,
    val lastName : String,
    val biography : String?=null,
    val birthdate : String?=null,
    val phoneNumber : String?=null
){
    override fun toString(): String {
        return "BookUserProfile(firstName='$firstName', lastName='$lastName', biography='$biography', birthdate=$birthdate)"
    }
}

data class BookUser constructor(
    val id : String,
    val loginCredentials: BookUserLoginCredentials,
    val profile : BookUserProfile,
    val address: BookUserAddress
) {
    fun hasLogin(
        email: String,
        password: String
    ) : Boolean = (email == this.loginCredentials.email) and (password == this.loginCredentials.password)

    fun hasValidCredentials() : Boolean = hasValidEmail() and hasValidPassword()

    fun hasValidEmail() : Boolean = this.loginCredentials.email.matches(Regex(".*@.*"))

    fun hasValidPassword() : Boolean = this.loginCredentials.password.length == 8

    override fun toString(): String {
        return "BookUser(id='$id', loginCredentials=$loginCredentials, profile=$profile, address=$address)"
    }

}