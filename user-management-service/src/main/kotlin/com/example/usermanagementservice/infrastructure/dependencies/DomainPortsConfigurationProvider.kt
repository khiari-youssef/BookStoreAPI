package com.example.usermanagementservice.infrastructure.dependencies

import com.example.usermanagementservice.coreDomain.ExternalToDomainEntityMapper
import com.example.usermanagementservice.coreDomain.entities.DomainUser
import com.example.usermanagementservice.infrastructure.dto.RedisDTOUser
import com.example.usermanagementservice.infrastructure.repositories.userRepository.UserRepositoryRedisDTOmapper
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope

@Configuration
class DomainPortsConfigurationProvider {

@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
@Bean
fun provideUserRepositoryRedisDTOmapperInstance() : ExternalToDomainEntityMapper<RedisDTOUser, DomainUser> =
    UserRepositoryRedisDTOmapper()
}


