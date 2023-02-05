package com.example.usermanagementservice.coreDomain.services.authentification

import com.example.usermanagementservice.application.payloads.AuthPayload
import com.example.usermanagementservice.coreDomain.DomainException
import com.example.usermanagementservice.coreDomain.repositoryContracts.UserRepositoryContract
import com.example.usermanagementservice.coreDomain.services.DomainPasswordEncodingService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*


class AuthentificationServiceRedisImplementation constructor(
    private val repository: UserRepositoryContract,
    private val domainPasswordEncodingService: DomainPasswordEncodingService
) : AuthentificationService {

    override fun authenticateUser(
        authCredentialsPayload: AuthPayload?
    ): Flow<Boolean>  = if (authCredentialsPayload == null) flow {
        throw DomainException.AuthentificationDomainException("Invalid credentials !")
    } else
        repository
            .getUser()
            .take(1)
            .map { domainData->
                return@map domainData.hasLogin(
                    email =  authCredentialsPayload.userEmail,
                    password = domainPasswordEncodingService.encodePassword(authCredentialsPayload.userPassword)
                )
            }
            .catch { ex->
                if (ex is NullPointerException)  throw DomainException.AuthentificationDomainException("Invalid credentials !")
            }
            .flowOn(Dispatchers.IO)
}