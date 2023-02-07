package com.example.customermanagementservice.infrastructure.dependencies

import com.example.customermanagementservice.coreDomain.repositoryContracts.CustomerRepositoryContract
import com.example.customermanagementservice.coreDomain.services.DomainPasswordEncodingService
import com.example.customermanagementservice.coreDomain.services.PasswordEncodingManager
import com.example.customermanagementservice.coreDomain.services.accountManagement.RegistrationService
import com.example.customermanagementservice.coreDomain.services.accountManagement.RegistrationServiceRedisImplementation
import com.example.customermanagementservice.coreDomain.services.authentification.AuthentificationService
import com.example.customermanagementservice.coreDomain.services.authentification.AuthentificationServiceRedisImplementation
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
        @Qualifier("RedisImpl")   repository: CustomerRepositoryContract,
        domainPasswordEncodingService: DomainPasswordEncodingService
) : AuthentificationService = AuthentificationServiceRedisImplementation(repository,domainPasswordEncodingService)

@Bean("RedisRegistrationService")
fun  provideRedisRegistrationServiceImpl(
    @Qualifier("RedisImpl")  repository: CustomerRepositoryContract,
    domainPasswordEncodingService: DomainPasswordEncodingService
) : RegistrationService = RegistrationServiceRedisImplementation(repository,domainPasswordEncodingService)



}