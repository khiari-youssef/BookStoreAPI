package com.example.customermanagementservice.coreDomain

sealed class DomainException : Exception(){
    data class UnauthorizedDomainException(override val message: String?) : DomainException()
    data class AuthentificationDomainException(override val message: String?) : DomainException()
    data class RegistrationException(override val message: String?) : DomainException()
    data class ResourceNotFoundDomainException(override val message: String?) : DomainException()
    data class InternalErrorDomainException(override val message: String?) : DomainException()
    data class DomainDateTimeException(override val message: String?) : DomainException()
}

