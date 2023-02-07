package com.example.customermanagementservice.coreDomain.services.accountManagement

import com.example.customermanagementservice.coreDomain.DomainException
import com.example.customermanagementservice.coreDomain.entities.BookCustomer
import com.example.customermanagementservice.coreDomain.repositoryContracts.CustomerRepositoryContract
import com.example.customermanagementservice.coreDomain.services.DomainPasswordEncodingService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class RegistrationServiceRedisImplementation constructor(
    private val repository: CustomerRepositoryContract,
    private val domainPasswordEncodingService: DomainPasswordEncodingService
) : RegistrationService {



    override suspend fun registerUser(bookCustomer: BookCustomer?): Flow<BookCustomer> = flow<BookCustomer> {
        bookCustomer?.takeIf {
            it.hasValidCredentials()
        }?.let { validUser->
            val encodedPassword = domainPasswordEncodingService.encodePassword(validUser.loginCredentials.password)
            repository
                .saveUser(validUser.copy(
                    loginCredentials = validUser.loginCredentials.copy(
                        password = encodedPassword
                    )
                ))
        } ?: throw DomainException.RegistrationException("invalid credentials ! ")
    }.catch { ex->
        ex.printStackTrace()
           throw DomainException.RegistrationException("could not save user")
     }
     .flowOn(Dispatchers.IO)

}