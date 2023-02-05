package com.example.usermanagementservice.infrastructure.dto

import com.example.usermanagementservice.coreDomain.entities.AddressLine
import java.io.Serializable


@kotlinx.serialization.Serializable
data class RedisLocation(
    val latitude : Double,
    val longitude : Double
) : Serializable

@kotlinx.serialization.Serializable
data class RedisBookUserDTO(
    val id : String,
    val email : String,
    val password : String,
    val firstName : String,
    val lastName : String,
    val biography : String?,
    val phoneNumber : String?,
    val birthdate : String?,
    val addressLine1: String,
    val addressLine2: String?,
    val location : RedisLocation?
    ) : Serializable