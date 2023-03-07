package com.example.customermanagementservice.infrastructure.dto.redis

import java.io.Serializable


@kotlinx.serialization.Serializable
data class RedisLocation(
    val latitude : Double,
    val longitude : Double
) : Serializable

@kotlinx.serialization.Serializable
data class RedisBookCustomerDTO(
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