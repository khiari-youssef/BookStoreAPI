package com.example.usermanagementservice.coreDomain.services.accountManagement

import com.example.usermanagementservice.coreDomain.entities.BookCustomer
import kotlinx.coroutines.flow.Flow

interface RegistrationService {

    suspend fun registerUser(bookCustomer: BookCustomer?) : Flow<BookCustomer>
}