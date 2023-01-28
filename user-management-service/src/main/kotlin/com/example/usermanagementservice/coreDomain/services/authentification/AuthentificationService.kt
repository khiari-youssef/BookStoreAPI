package com.example.usermanagementservice.coreDomain.services.authentification

import com.example.usermanagementservice.application.payloads.AuthPayload
import kotlinx.coroutines.flow.Flow

interface AuthentificationService {
    fun authenticateUser(authCredentialsPayload: AuthPayload?) : Flow<Boolean>
}