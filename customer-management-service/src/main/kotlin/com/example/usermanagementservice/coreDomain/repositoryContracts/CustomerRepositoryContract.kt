package com.example.usermanagementservice.coreDomain.repositoryContracts

import com.example.usermanagementservice.coreDomain.entities.BookCustomer
import kotlinx.coroutines.flow.Flow

interface CustomerRepositoryContract {

    suspend fun saveUser(bookCustomer: BookCustomer) : BookCustomer

     fun getUser() : Flow<BookCustomer>
}