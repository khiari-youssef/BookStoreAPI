package com.example.customermanagementservice.infrastructure.dependencies

import com.example.customermanagementservice.application.payloads.CustomerRegistrationRestPayload
import com.example.customermanagementservice.application.payloads.CustomerRestPayloadToDomainEntityMapper
import com.example.customermanagementservice.coreDomain.ExternalToDomainEntityMapper
import com.example.customermanagementservice.coreDomain.entities.BookCustomer
import com.example.customermanagementservice.infrastructure.dto.redis.RedisBookUserDTO
import com.example.customermanagementservice.infrastructure.repositories.customerRepository.UserRepositoryRedisDTOMapper
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




