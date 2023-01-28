package com.example.usermanagementservice.infrastructure.dependencies

import com.example.usermanagementservice.UserManagementServiceApplication
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.context.annotation.Scope

@Import(
    RedisConfigurationProvider::class,
    DomainPortsConfigurationProvider::class,
    DomainServicesConfigurationProvider::class
)
@Configuration
class AppConfigurationProvider {

    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    @Bean
    fun provideLog4jLogger(): Logger = LoggerFactory.getLogger(UserManagementServiceApplication::class.java)

}