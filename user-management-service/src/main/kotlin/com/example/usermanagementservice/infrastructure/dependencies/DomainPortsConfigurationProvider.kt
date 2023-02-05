package com.example.usermanagementservice.infrastructure.dependencies

import com.example.usermanagementservice.coreDomain.ExternalToDomainEntityMapper
import com.example.usermanagementservice.coreDomain.entities.BookUser
import com.example.usermanagementservice.infrastructure.dto.RedisBookUserDTO
import com.example.usermanagementservice.infrastructure.repositories.userRepository.UserRepositoryRedisDTOmapper
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope

@Configuration
class DomainPortsConfigurationProvider {

@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
@Bean
fun provideUserRepositoryRedisDTOmapperInstance() : ExternalToDomainEntityMapper<RedisBookUserDTO, BookUser> =
    UserRepositoryRedisDTOmapper()
}


