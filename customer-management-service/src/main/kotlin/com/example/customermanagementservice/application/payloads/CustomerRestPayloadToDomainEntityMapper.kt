package com.example.customermanagementservice.application.payloads

import com.example.customermanagementservice.coreDomain.DomainException
import com.example.customermanagementservice.coreDomain.ExternalToDomainEntityMapper
import com.example.customermanagementservice.coreDomain.entities.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class CustomerRestPayloadToDomainEntityMapper :
    ExternalToDomainEntityMapper<CustomerRegistrationRestPayload, BookCustomer> {


    private val dateFormatter = DateTimeFormatter.ISO_DATE


    override fun fromDomain(domainObject: BookCustomer): CustomerRegistrationRestPayload = CustomerRegistrationRestPayload(
        id = domainObject.id,
        credentials = CustomerLoginCredentialsRestPayload(
            email = domainObject.loginCredentials.email,
            password = domainObject.loginCredentials.password
        ),
        profile = CustomerProfileRestPayload(
            biography = domainObject.profile.biography,
            phoneNumber = domainObject.profile.phoneNumber,
            firstName = domainObject.profile.firstName,
            lastName = domainObject.profile.lastName,
            birthdate = dateFormatter.format(domainObject.profile.birthdate)
        ),
        address = CustomerAddressRestPayload(
            line1 = domainObject.address.line1.run {
                CustomerAddressLineRestPayload(
                    buildingNumber,
                    streetAddress
                )
            },
            line2 = domainObject.address.line2?.run {
                CustomerAddressLineRestPayload(
                    buildingNumber,
                    streetAddress
                )
            } ,
            location = domainObject.address.location?.run {
                CustomerLocationRestPayload(
                    latitude = latitude,
                    longitude = longitude
                )
            }
        )
    )

    override fun toDomain(externalObject: CustomerRegistrationRestPayload): BookCustomer = BookCustomer(
        id = externalObject.id,
        loginCredentials = CustomerLoginCredentials(
            email = externalObject.credentials.email,
            password = externalObject.credentials.password
        ),
        profile = CustomerProfile(
            firstName = externalObject.profile.firstName,
            lastName = externalObject.profile.lastName,
            biography = externalObject.profile.biography,
            phoneNumber = externalObject.profile.phoneNumber,
            birthdate = kotlin.runCatching {
                LocalDate.from(dateFormatter.parse(externalObject.profile.birthdate))
            }.getOrElse { throw DomainException.DomainDateTimeException("Invalid date format !") }
        ),
        address = CustomerAddress(
            line1 = externalObject.address.line1.run {
                CustomerAddressLine(
                    buildingNumber,
                    streetAddress
                )
            },
            line2 = externalObject.address.line2?.run {
                CustomerAddressLine(
                    buildingNumber,
                    streetAddress
                )
            } ,
            location = externalObject.address.location?.run {
                CustomerLocation(
                    latitude = latitude,
                    longitude = longitude
                )
            }
        )
    )
}