package com.example.customermanagementservice.coreDomain.services.authentification

import com.example.customermanagementservice.application.payloads.AuthPayload
import kotlinx.coroutines.flow.Flow

interface AuthentificationService {
    fun authenticateCustomer(authCredentialsPayload: AuthPayload?) : Flow<Boolean>
}