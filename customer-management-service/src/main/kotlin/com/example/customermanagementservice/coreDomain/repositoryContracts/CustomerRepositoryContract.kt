package com.example.customermanagementservice.coreDomain.repositoryContracts

import com.example.customermanagementservice.coreDomain.entities.BookCustomer
import kotlinx.coroutines.flow.Flow

interface CustomerRepositoryContract {

    suspend fun saveUser(bookCustomer: BookCustomer) : BookCustomer

     fun getUser() : Flow<BookCustomer>
}