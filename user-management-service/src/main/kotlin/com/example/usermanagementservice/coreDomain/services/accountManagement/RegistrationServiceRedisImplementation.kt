package com.example.usermanagementservice.coreDomain.services.accountManagement

import com.example.usermanagementservice.coreDomain.DomainException
import com.example.usermanagementservice.coreDomain.entities.BookUser
import com.example.usermanagementservice.coreDomain.repositoryContracts.UserRepositoryContract
import com.example.usermanagementservice.coreDomain.services.DomainPasswordEncodingService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class RegistrationServiceRedisImplementation constructor(
    private val repository: UserRepositoryContract,
    private val domainPasswordEncodingService: DomainPasswordEncodingService
) : RegistrationService {



    override suspend fun registerUser(bookUser: BookUser?): Flow<BookUser> = flow<BookUser> {
        bookUser?.takeIf {
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