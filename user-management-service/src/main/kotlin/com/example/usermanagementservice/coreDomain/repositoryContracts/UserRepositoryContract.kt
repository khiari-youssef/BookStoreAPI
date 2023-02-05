package com.example.usermanagementservice.coreDomain.repositoryContracts

import com.example.usermanagementservice.coreDomain.entities.BookUser
import kotlinx.coroutines.flow.Flow

interface UserRepositoryContract {

    suspend fun saveUser(bookUser: BookUser) : BookUser

     fun getUser() : Flow<BookUser>
}