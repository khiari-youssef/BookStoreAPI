package com.example.usermanagementservice.coreDomain.services

interface DomainPasswordEncodingService {

    suspend fun encodePassword(password : String) : String

    suspend fun verifyPassword(clearTextPassword : String,hashedPassword : String) : Boolean

}