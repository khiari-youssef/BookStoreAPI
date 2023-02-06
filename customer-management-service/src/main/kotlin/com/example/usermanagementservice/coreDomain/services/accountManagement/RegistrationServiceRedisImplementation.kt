package com.example.usermanagementservice.coreDomain.services.accountManagement

import com.example.usermanagementservice.coreDomain.DomainException
import com.example.usermanagementservice.coreDomain.entities.BookCustomer
import com.example.usermanagementservice.coreDomain.repositoryContracts.CustomerRepositoryContract
import com.example.usermanagementservice.coreDomain.services.DomainPasswordEncodingService
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