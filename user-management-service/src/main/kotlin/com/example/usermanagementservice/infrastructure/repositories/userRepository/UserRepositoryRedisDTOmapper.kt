package com.example.usermanagementservice.infrastructure.repositories.userRepository

import com.example.usermanagementservice.coreDomain.ExternalToDomainEntityMapper
import com.example.usermanagementservice.coreDomain.entities.DomainUser
import com.example.usermanagementservice.infrastructure.dto.RedisDTOUser


class UserRepositoryRedisDTOmapper : ExternalToDomainEntityMapper<RedisDTOUser,DomainUser> {

    override fun fromDomain(domainObject: DomainUser): RedisDTOUser = RedisDTOUser(
        id = domainObject.id,
        name = domainObject.name,
        email = domainObject.email,
        password = domainObject.password
    )

    override fun toDomain(externalObject: RedisDTOUser): DomainUser = DomainUser(
        id = externalObject.id,
        name = externalObject.name,
        email = externalObject.email,
        password = externalObject.password
    )
}