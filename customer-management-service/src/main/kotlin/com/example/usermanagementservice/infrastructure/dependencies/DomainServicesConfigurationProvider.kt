package com.example.usermanagementservice.infrastructure.dependencies

import com.example.usermanagementservice.coreDomain.repositoryContracts.CustomerRepositoryContract
import com.example.usermanagementservice.coreDomain.services.DomainPasswordEncodingService
import com.example.usermanagementservice.coreDomain.services.PasswordEncodingManager
import com.example.usermanagementservice.coreDomain.services.accountManagement.RegistrationService
import com.example.usermanagementservice.coreDomain.services.accountManagement.RegistrationServiceRedisImplementation
import com.example.usermanagementservice.coreDomain.services.authentification.AuthentificationService
import com.example.usermanagementservice.coreDomain.services.authentification.AuthentificationServiceRedisImplementation
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder


@Configuration
class DomainServicesConfigurationProvider  {

@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
@Bean("bcryptEncoder")
fun providePasswordEncoder() : PasswordEncoder = BCryptPasswordEncoder()

@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
@Bean
fun provideDomainPasswordEncodingService(
    @Qualifier("bcryptEncoder")  passwordEncoder: PasswordEncoder
) : DomainPasswordEncodingService = PasswordEncodingManager(passwordEncoder)

    @Bean("RedisAuthService")
fun  provideRedisAuthServiceImpl(
        repository: CustomerRepositoryContract,
        domainPasswordEncodingService: DomainPasswordEncodingService
) : AuthentificationService = AuthentificationServiceRedisImplementation(repository,domainPasswordEncodingService)

@Bean("RedisRegistrationService")
fun  provideRedisRegistrationServiceImpl(
    repository: CustomerRepositoryContract,
    domainPasswordEncodingService: DomainPasswordEncodingService
) : RegistrationService = RegistrationServiceRedisImplementation(repository,domainPasswordEncodingService)



}