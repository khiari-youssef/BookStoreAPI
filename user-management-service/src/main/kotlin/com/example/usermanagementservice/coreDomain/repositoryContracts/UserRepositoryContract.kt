package com.example.usermanagementservice.coreDomain.repositoryContracts

import com.example.usermanagementservice.coreDomain.entities.DomainUser
import kotlinx.coroutines.flow.Flow

interface UserRepositoryContract {

    suspend fun saveUser(domainUser: DomainUser) : DomainUser

     fun getUser() : Flow<DomainUser>
}