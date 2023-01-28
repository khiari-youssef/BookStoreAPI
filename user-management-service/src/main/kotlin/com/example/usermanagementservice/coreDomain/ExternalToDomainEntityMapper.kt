package com.example.usermanagementservice.coreDomain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface ExternalToDomainEntityMapper<External,Domain> {

    fun toDomain(externalObject : External) : Domain
    fun fromDomain(domainObject : Domain) : External

    fun toDomain(externalObject : List<External>) : List<Domain> = externalObject.map {
        toDomain(it)
    }
    fun fromDomain(domainObject : List<Domain>) : List<External> = domainObject.map {
        fromDomain(it)
    }


}


fun <External,Domain>  Flow<External>.mapToDomain(mapper : ExternalToDomainEntityMapper<External,Domain>) = map {
    mapper.toDomain(it)
}