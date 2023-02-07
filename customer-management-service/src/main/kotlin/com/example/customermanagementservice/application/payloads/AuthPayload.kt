package com.example.customermanagementservice.application.payloads

import java.io.Serializable

data class AuthPayload(
    val userEmail: String,
    val userPassword: String
) : Serializable