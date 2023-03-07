package com.example.customermanagementservice.coreDomain.repositoryContracts

import com.example.customermanagementservice.coreDomain.entities.BookCustomer
import com.example.customermanagementservice.coreDomain.entities.CustomerLoginCredentials
import kotlinx.coroutines.flow.Flow

interface CustomerRepositoryContract {

    suspend fun saveCustomer(bookCustomer: BookCustomer) : BookCustomer

     fun findCustomerByID(customerID : String) : Flow<BookCustomer>

     fun checkUserCredentialsValidity(loginCredentials: CustomerLoginCredentials?) : Flow<Boolean>
}