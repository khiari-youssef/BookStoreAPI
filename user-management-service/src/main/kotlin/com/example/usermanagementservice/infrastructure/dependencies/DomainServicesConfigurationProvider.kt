package com.example.usermanagementservice.infrastructure.dependencies

import com.example.usermanagementservice.coreDomain.repositoryContracts.UserRepositoryContract
import com.example.usermanagementservice.coreDomain.services.accountManagement.RegistrationService
import com.example.usermanagementservice.coreDomain.services.accountManagement.RegistrationServiceRedisImplementation
import com.example.usermanagementservice.coreDomain.services.authentification.AuthentificationService
import com.example.usermanagementservice.coreDomain.services.authentification.AuthentificationServiceRedisImplementation
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class DomainServicesConfigurationProvider  {

@Bean("RedisAuthService")
fun  provideRedisAuthServiceImpl(
    repository: UserRepositoryContract
) : AuthentificationService = AuthentificationServiceRedisImplementation(repository)

@Bean("RedisRegistrationService")
fun  provideRedisRegistrationServiceImpl(
    repository: UserRepositoryContract
) : RegistrationService = RegistrationServiceRedisImplementation(repository)

}