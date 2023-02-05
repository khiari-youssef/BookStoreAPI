package com.example.usermanagementservice.coreDomain.services.accountManagement

import com.example.usermanagementservice.coreDomain.entities.BookUser
import kotlinx.coroutines.flow.Flow

interface RegistrationService {

    suspend fun registerUser(bookUser: BookUser?) : Flow<BookUser>
}