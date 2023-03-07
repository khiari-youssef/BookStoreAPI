package com.example.customermanagementservice.coreDomain.services.authentification

import com.example.customermanagementservice.coreDomain.DomainException
import com.example.customermanagementservice.coreDomain.entities.CustomerLoginCredentials
import com.example.customermanagementservice.coreDomain.repositoryContracts.CustomerRepositoryContract
import com.example.customermanagementservice.coreDomain.services.DomainPasswordEncodingService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.take


class AuthenticationServiceRedisImplementation constructor(
    private val repository: CustomerRepositoryContract,
    private val domainPasswordEncodingService: DomainPasswordEncodingService
) : AuthenticationService {

    override suspend fun authenticateCustomer(
        customerLoginCredentials: CustomerLoginCredentials?
    ): Flow<Boolean>  {
        val hashedPassword = customerLoginCredentials?.run {
            domainPasswordEncodingService.encodePassword(password)
        }
       return repository
           .checkUserCredentialsValidity(
               loginCredentials = hashedPassword?.run {
                   customerLoginCredentials.copy(
                       password = hashedPassword
                   )
               }
           )
           .take(1)
           .catch { ex->
               ex.printStackTrace()
               if (ex is NullPointerException)  throw DomainException.AuthentificationDomainException("Invalid credentials !")
           }
           .flowOn(Dispatchers.IO)
    }

}