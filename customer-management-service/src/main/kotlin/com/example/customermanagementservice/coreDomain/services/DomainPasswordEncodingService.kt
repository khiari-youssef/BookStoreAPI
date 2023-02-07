package com.example.customermanagementservice.coreDomain.services

interface DomainPasswordEncodingService {

    suspend fun encodePassword(password : String) : String

    suspend fun verifyPassword(clearTextPassword : String,hashedPassword : String) : Boolean

}