package com.example.usermanagementservice.infrastructure.repositories.userRepository

import com.example.usermanagementservice.coreDomain.ExternalToDomainEntityMapper
import com.example.usermanagementservice.coreDomain.entities.*
import com.example.usermanagementservice.infrastructure.dto.RedisBookUserDTO
import com.example.usermanagementservice.infrastructure.dto.RedisLocation
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class UserRepositoryRedisDTOmapper : ExternalToDomainEntityMapper<RedisBookUserDTO,BookUser> {

    override fun fromDomain(domainObject: BookUser): RedisBookUserDTO = RedisBookUserDTO(
        id = domainObject.id,
        firstName = domainObject.profile.firstName,
        lastName = domainObject.profile.lastName,
        biography = domainObject.profile.biography,
        birthdate = domainObject.profile.birthdate,
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

    override fun toDomain(externalObject: RedisBookUserDTO): BookUser = BookUser(
        id = externalObject.id,
        loginCredentials = BookUserLoginCredentials(
            email = externalObject.email,
            password = externalObject.password
        ),
        profile = BookUserProfile(
            firstName = externalObject.firstName,
            lastName = externalObject.lastName,
            biography = externalObject.biography,
            birthdate = externalObject.birthdate,
            phoneNumber = externalObject.phoneNumber
        ),
        address = BookUserAddress(
            line1 = externalObject.addressLine1.split("-").run {
                AddressLine(get(0).toInt(),get(1))
            },
            line2 = externalObject.addressLine2?.split("-")?.run {
                AddressLine(get(0).toInt(),get(1))
            },
            location = externalObject.location?.run {
                BookUserLocation(
                    latitude, longitude
                )
            }
        )
    )
}