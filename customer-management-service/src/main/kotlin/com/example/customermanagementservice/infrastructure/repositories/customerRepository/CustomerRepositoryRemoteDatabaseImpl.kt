package com.example.customermanagementservice.infrastructure.repositories.customerRepository

import com.example.customermanagementservice.coreDomain.ExternalToDomainEntityMapper
import com.example.customermanagementservice.coreDomain.entities.*
import com.example.customermanagementservice.coreDomain.repositoryContracts.CustomerRepositoryContract
import com.example.customermanagementservice.infrastructure.dto.springJPA.BookCustomerJpaDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository("SpringJPAImpl")
abstract class CustomerRepositoryRemoteDatabaseImpl(
    private val userRepositoryJPADTOmapper: ExternalToDomainEntityMapper<BookCustomerJpaDTO, BookCustomer>
) :  CustomerRepositoryContract, JpaRepository<BookCustomerJpaDTO,String> {

    val mockCustomer = BookCustomer(
        "youssef-id",
        CustomerLoginCredentials(
            "khiari.youssef98@gmail.com",
            "00000000"
        ),
        CustomerProfile(
            "Youssef",
            "Khiari",
            "No description",
            LocalDate.now()
        ),
        CustomerAddress(
            line1 = CustomerAddressLine(72,"Ibn el jazzar street")
        )
    )


    override fun findCustomerByID(customerID: String) = flow {
          // val jpaEntity = findByIdOrNull(customerID)
        //emit(userRepositoryJPADTOmapper.toDomain(jpaEntity!!))
        emit(mockCustomer)
    }.flowOn(Dispatchers.IO)

    override fun checkUserCredentialsValidity(loginCredentials: CustomerLoginCredentials?): Flow<Boolean> = flow {
        emit(loginCredentials?.run {
            true
        } ?: false)
    }.flowOn(Dispatchers.IO)

    override suspend fun saveCustomer(bookCustomer: BookCustomer): BookCustomer = withContext(Dispatchers.IO){
       // val jpaEntity = userRepositoryJPADTOmapper.fromDomain(bookCustomer)
       // val transactionResult = saveAndFlush(jpaEntity)
       // userRepositoryJPADTOmapper.toDomain(transactionResult)
        mockCustomer
    }



}