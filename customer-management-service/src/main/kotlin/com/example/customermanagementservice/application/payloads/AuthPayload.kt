package com.example.customermanagementservice.application.payloads

import com.example.customermanagementservice.coreDomain.entities.CustomerLoginCredentials
import java.io.Serializable

data class AuthPayload(
    val userEmail: String,
    val userPassword: String
) : Serializable {
    fun asDomainCredentials() : CustomerLoginCredentials = CustomerLoginCredentials(
        email = userEmail,
        password = userPassword
    )
}