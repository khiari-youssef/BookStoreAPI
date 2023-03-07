package com.example.customermanagementservice.coreDomain.services.accountManagement

import com.example.customermanagementservice.coreDomain.DomainException
import com.example.customermanagementservice.coreDomain.entities.BookCustomer
import com.example.customermanagementservice.coreDomain.repositoryContracts.CustomerRepositoryContract
import com.example.customermanagementservice.coreDomain.services.DomainPasswordEncodingService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RegistrationServiceRedisImplementation constructor(
    private val redisRepository: CustomerRepositoryContract,
    private val domainPasswordEncodingService: DomainPasswordEncodingService
) : RegistrationService {



    override suspend fun registerUser(bookCustomer: BookCustomer?): Flow<BookCustomer> = flow<BookCustomer> {
        bookCustomer?.takeIf {
            it.hasValidCredentials()
        }?.let { validUser->
            val encodedPassword = domainPasswordEncodingService.encodePassword(validUser.loginCredentials.password)
            val updatedUser = validUser.copy(
                loginCredentials = validUser.loginCredentials.copy(
                    password = encodedPassword
                )
            )
           // val savedUser = jpaRepository.saveCustomer(updatedUser)
            val cachedUser = redisRepository.saveCustomer(updatedUser)
            emit(cachedUser)
        } ?: throw DomainException.RegistrationException("invalid credentials ! ")
    }.catch { ex->
        ex.printStackTrace()
           throw DomainException.RegistrationException("could not save user")
     }
     .flowOn(Dispatchers.IO)

}