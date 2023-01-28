package com.example.usermanagementservice.application.payloads

import com.example.usermanagementservice.coreDomain.entities.DomainUser

data class RegistrationPayload(
    val user : DomainUser
)