package com.example.usermanagementservice.coreDomain.services.authentification

import com.example.usermanagementservice.application.payloads.AuthPayload
import com.example.usermanagementservice.coreDomain.DomainException
import com.example.usermanagementservice.coreDomain.repositoryContracts.UserRepositoryContract
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*


class AuthentificationServiceRedisImplementation constructor(
    private val repository: UserRepositoryContract
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
                return@map domainData.verifyLogin(
                    email =  authCredentialsPayload.userEmail,
                    password = authCredentialsPayload.userPassword
                )
            }
            .catch { ex->
                if (ex is NullPointerException)  throw DomainException.AuthentificationDomainException("Invalid credentials !")
            }
            .flowOn(Dispatchers.IO)
}