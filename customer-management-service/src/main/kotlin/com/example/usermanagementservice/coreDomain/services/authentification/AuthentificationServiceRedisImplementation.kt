package com.example.usermanagementservice.coreDomain.services.authentification

import com.example.usermanagementservice.application.payloads.AuthPayload
import com.example.usermanagementservice.coreDomain.DomainException
import com.example.usermanagementservice.coreDomain.repositoryContracts.CustomerRepositoryContract
import com.example.usermanagementservice.coreDomain.services.DomainPasswordEncodingService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*


class AuthentificationServiceRedisImplementation constructor(
    private val repository: CustomerRepositoryContract,
    private val domainPasswordEncodingService: DomainPasswordEncodingService
) : AuthentificationService {

    override fun authenticateCustomer(
        authCredentialsPayload: AuthPayload?
    ): Flow<Boolean>  = if (authCredentialsPayload == null) flow {
        throw DomainException.AuthentificationDomainException("Invalid credentials !")
    } else
        repository
            .getUser()
            .take(1)
            .map { domainData->
                return@map if (domainData.hasLogin(
                    email =  authCredentialsPayload.userEmail,
                    password = domainPasswordEncodingService.encodePassword(authCredentialsPayload.userPassword)
                )) true else throw NullPointerException()
            }
            .catch { ex->
                if (ex is NullPointerException)  throw DomainException.AuthentificationDomainException("Invalid credentials !")
            }
            .flowOn(Dispatchers.IO)
}