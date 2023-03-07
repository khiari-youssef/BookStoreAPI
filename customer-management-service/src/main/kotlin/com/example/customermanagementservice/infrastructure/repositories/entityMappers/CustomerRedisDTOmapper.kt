package com.example.customermanagementservice.infrastructure.repositories.entityMappers

import com.example.customermanagementservice.coreDomain.DomainException
import com.example.customermanagementservice.coreDomain.ExternalToDomainEntityMapper
import com.example.customermanagementservice.coreDomain.entities.*
import com.example.customermanagementservice.infrastructure.dto.redis.RedisBookCustomerDTO
import com.example.customermanagementservice.infrastructure.dto.redis.RedisLocation
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class UserRepositoryRedisDTOMapper : ExternalToDomainEntityMapper<RedisBookCustomerDTO, BookCustomer> {

    private val dateFormatter = DateTimeFormatter.ISO_DATE

    override fun fromDomain(domainObject: BookCustomer): RedisBookCustomerDTO = RedisBookCustomerDTO(
        id = domainObject.id,
        firstName = domainObject.profile.firstName,
        lastName = domainObject.profile.lastName,
        biography = domainObject.profile.biography,
        birthdate = dateFormatter.format(domainObject.profile.birthdate),
        location = domainObject.address.location?.run {
            RedisLocation(
                latitude = latitude,
                longitude = longitude
            )
        },
        addressLine1 = domainObject.address.line1.run {
                  "$buildingNumber-$streetAddress"
        },
        addressLine2 = domainObject.address.line2?.run {
            "$buildingNumber-$streetAddress"
        },
        email = domainObject.loginCredentials.email,
        password = domainObject.loginCredentials.password,
        phoneNumber = domainObject.profile.phoneNumber
    )

    override fun toDomain(externalObject: RedisBookCustomerDTO): BookCustomer = BookCustomer(
        id = externalObject.id,
        loginCredentials = CustomerLoginCredentials(
            email = externalObject.email,
            password = externalObject.password
        ),
        profile = CustomerProfile(
            firstName = externalObject.firstName,
            lastName = externalObject.lastName,
            biography = externalObject.biography,
            birthdate = kotlin.runCatching{ LocalDate.from(dateFormatter.parse(externalObject.birthdate)) }
                .getOrElse { throw DomainException.DomainDateTimeException("Invalid date format !") },
            phoneNumber = externalObject.phoneNumber
        ),
        address = CustomerAddress(
            line1 = externalObject.addressLine1.split("-").run {
                CustomerAddressLine(get(0).toInt(),get(1))
            },
            line2 = externalObject.addressLine2?.split("-")?.run {
                CustomerAddressLine(get(0).toInt(),get(1))
            },
            location = externalObject.location?.run {
                CustomerLocation(
                    latitude, longitude
                )
            }
        )
    )
}