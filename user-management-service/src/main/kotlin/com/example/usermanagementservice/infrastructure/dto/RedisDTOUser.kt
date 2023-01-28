package com.example.usermanagementservice.infrastructure.dto

import java.io.Serializable


data class RedisDTOUser(
    val id : String,
    val name : String,
    val email : String,
    val password : String
) : Serializable