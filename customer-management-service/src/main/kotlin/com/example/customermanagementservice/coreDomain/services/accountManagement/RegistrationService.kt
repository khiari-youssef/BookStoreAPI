package com.example.customermanagementservice.coreDomain.services.accountManagement

import com.example.customermanagementservice.coreDomain.entities.BookCustomer
import kotlinx.coroutines.flow.Flow

interface RegistrationService {

    suspend fun registerUser(bookCustomer: BookCustomer?) : Flow<BookCustomer>
}