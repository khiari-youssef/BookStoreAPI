package com.example.customermanagementservice.infrastructure.repositories.customerRepository

import com.example.customermanagementservice.coreDomain.entities.BookCustomer
import com.example.customermanagementservice.coreDomain.repositoryContracts.CustomerRepositoryContract
import com.example.customermanagementservice.infrastructure.dto.springJPA.CustomerJpaDTO
import kotlinx.coroutines.flow.Flow
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository("SpringJPAImpl")
abstract class CustomerRepositoryRemoteDatabaseImpl :  CustomerRepositoryContract, JpaRepository<CustomerJpaDTO,String> {



    override fun getUser(): Flow<BookCustomer> {
        TODO("Not yet implemented")
    }

    override suspend fun saveUser(bookCustomer: BookCustomer): BookCustomer {
        TODO("Not yet implemented")
    }


}