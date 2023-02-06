package com.example.usermanagementservice.application.payloads

import kotlinx.serialization.Serializable

@Serializable
data class CustomerLocationRestPayload(
    val latitude : Double,
    val longitude : Double
)

@Serializable
data class CustomerAddressLineRestPayload(
    val buildingNumber : Int,
    val streetAddress : String
)

@Serializable
class CustomerAddressRestPayload(
    val line1: CustomerAddressLineRestPayload,
    val line2: CustomerAddressLineRestPayload?=null,
    val location : CustomerLocationRestPayload?=null
)

@Serializable
data class CustomerLoginCredentialsRestPayload constructor(
    val email : String,
    val password: String
)

@Serializable
class CustomerProfileRestPayload(
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

@Serializable
data class CustomerRegistrationRestPayload(
    val id : String,
    val credentials: CustomerLoginCredentialsRestPayload,
    val profile : CustomerProfileRestPayload,
    val address : CustomerAddressRestPayload
)


