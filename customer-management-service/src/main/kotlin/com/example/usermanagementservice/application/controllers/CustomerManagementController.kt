package com.example.usermanagementservice.application.controllers


import com.example.usermanagementservice.application.CustomerManagementRoutes
import com.example.usermanagementservice.application.payloads.AuthPayload
import com.example.usermanagementservice.application.payloads.CustomerRegistrationRestPayload
import com.example.usermanagementservice.coreDomain.DomainException
import com.example.usermanagementservice.coreDomain.ExternalToDomainEntityMapper
import com.example.usermanagementservice.coreDomain.entities.BookCustomer
import com.example.usermanagementservice.coreDomain.services.accountManagement.RegistrationService
import com.example.usermanagementservice.coreDomain.services.authentification.AuthentificationService
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.util.*


@ControllerAdvice
class UserAuthExceptionHandler() : ResponseEntityExceptionHandler() {

    @ExceptionHandler(value = [RuntimeException::class, DomainException::class])
    fun handleException(ex: RuntimeException, request: WebRequest) : ResponseEntity<Any>  {
        ex.printStackTrace()
        return handleRequestFailures(ex)
    }

}


@RestController
class BookCustomerAuthController @Autowired constructor(
    @Qualifier("RedisAuthService") private val authentificationService: AuthentificationService,
    @Qualifier("RedisRegistrationService") private val registrationService: RegistrationService,
    private val customerPayloadMapper : ExternalToDomainEntityMapper<CustomerRegistrationRestPayload, BookCustomer>
) {

    @PostMapping("${CustomerManagementRoutes.ROOT}/${CustomerManagementRoutes.VERSIONNING_NAME}/login/credentials")
    suspend fun handleCustomerAuthentification(
        @RequestBody authCredentialsPayload: AuthPayload?
    ): Flow<ResponseEntity<Boolean>> = authentificationService
        .authenticateCustomer(authCredentialsPayload)
        .buildSuccessResponse()
        .handleRequestFlowFailures()

    @PostMapping("${CustomerManagementRoutes.ROOT}/${CustomerManagementRoutes.VERSIONNING_NAME}/signup")
    suspend fun handleCustomerRegistration(
        @RequestBody bookCustomer: CustomerRegistrationRestPayload?
    ): Flow<ResponseEntity<BookCustomer>> =
          registrationService
         .registerUser(customerPayloadMapper.toDomain(bookCustomer!!))
        .buildSuccessResponse()
        .handleRequestFlowFailures()

}