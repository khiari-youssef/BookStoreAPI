package com.example.customermanagementservice.infrastructure.repositories.entityMappers

import com.example.customermanagementservice.coreDomain.ExternalToDomainEntityMapper
import com.example.customermanagementservice.coreDomain.entities.*
import com.example.customermanagementservice.infrastructure.dto.springJPA.BookCustomerJpaDTO

class CustomerJpaDTOMapper: ExternalToDomainEntityMapper<BookCustomerJpaDTO, BookCustomer> {

    override fun fromDomain(domainObject: BookCustomer): BookCustomerJpaDTO = BookCustomerJpaDTO().apply {
           loginIDRef = ""
           addressRef = ""
           profileRef = ""
    }

    override fun toDomain(externalObject: BookCustomerJpaDTO): BookCustomer = BookCustomer(
        id =  externalObject.loginIDRef,
        loginCredentials =  CustomerLoginCredentials(
            email = "",
            password = ""
        ),
        profile = CustomerProfile(
            firstName = "",
            lastName = "",
            biography = "",
            birthdate = null,
            phoneNumber = ""
        ),
        address = CustomerAddress(
            line1 = CustomerAddressLine(
                buildingNumber = 0,
                streetAddress = "",

            ),
            line2 = null,
            location = CustomerLocation(
                latitude = 0.0,
                longitude = 0.0
            )
        )
    )
}