package com.example.usermanagementservice.infrastructure.dependencies

import com.example.usermanagementservice.application.payloads.CustomerRegistrationRestPayload
import com.example.usermanagementservice.application.payloads.CustomerRestPayloadToDomainEntityMapper
import com.example.usermanagementservice.coreDomain.ExternalToDomainEntityMapper
import com.example.usermanagementservice.coreDomain.entities.BookCustomer
import com.example.usermanagementservice.infrastructure.dto.RedisBookUserDTO
import com.example.usermanagementservice.infrastructure.repositories.userRepository.UserRepositoryRedisDTOMapper
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope

@Configuration
class DomainPortsConfigurationProvider {

    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    @Bean
    fun provideUserRepositoryRedisDTOMapperInstance(): ExternalToDomainEntityMapper<RedisBookUserDTO, BookCustomer> =
        UserRepositoryRedisDTOMapper()


    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    @Bean
    fun provideCustomerRegistrationRestPayloadMapperInstance(): ExternalToDomainEntityMapper<CustomerRegistrationRestPayload, BookCustomer> =
        CustomerRestPayloadToDomainEntityMapper()
}




