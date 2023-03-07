package com.example.customermanagementservice.coreDomain.services.authentification

import com.example.customermanagementservice.coreDomain.entities.CustomerLoginCredentials
import kotlinx.coroutines.flow.Flow

interface AuthenticationService {
   suspend fun authenticateCustomer(customerLoginCredentials: CustomerLoginCredentials?) : Flow<Boolean>
}