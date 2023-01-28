package com.example.usermanagementservice.coreDomain.services.accountManagement

import com.example.usermanagementservice.coreDomain.entities.DomainUser
import kotlinx.coroutines.flow.Flow

interface RegistrationService {

    suspend fun registerUser(domainUser: DomainUser?) : Flow<DomainUser>
}