package com.example.usermanagementservice.coreDomain.services.accountManagement

import com.example.usermanagementservice.coreDomain.DomainException
import com.example.usermanagementservice.coreDomain.entities.DomainUser
import com.example.usermanagementservice.coreDomain.repositoryContracts.UserRepositoryContract
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RegistrationServiceRedisImplementation constructor(
    private val repository: UserRepositoryContract
) : RegistrationService {

    override suspend fun registerUser(domainUser: DomainUser?): Flow<DomainUser> = flow<DomainUser> {
        if (domainUser == null) {
            throw DomainException.RegistrationException("cannot register user with no data")
        } else repository.saveUser(domainUser)
    }
        .catch {
           throw DomainException.RegistrationException("could not save user")
        }
        .flowOn(Dispatchers.IO)

}