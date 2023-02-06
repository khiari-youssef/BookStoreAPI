package com.example.usermanagementservice.coreDomain.services

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.security.crypto.password.PasswordEncoder

class PasswordEncodingManager(
   private val passwordEncoder: PasswordEncoder
) : DomainPasswordEncodingService {

    override suspend fun encodePassword(password: String): String = withContext(Dispatchers.Default){
        passwordEncoder.encode(password)
    }

    override suspend fun verifyPassword(clearTextPassword: String, hashedPassword: String): Boolean = withContext(Dispatchers.Default){
        passwordEncoder.matches(
            clearTextPassword,
            hashedPassword
        )
    }

}