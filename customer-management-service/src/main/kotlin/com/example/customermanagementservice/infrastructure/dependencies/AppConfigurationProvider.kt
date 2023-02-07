package com.example.customermanagementservice.infrastructure.dependencies

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.context.annotation.Scope
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain


@Import(
    RedisConfigurationProvider::class,
    DomainPortsConfigurationProvider::class,
    DomainServicesConfigurationProvider::class
)
@Configuration
class AppConfigurationProvider {

    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    @Bean
    fun provideLog4jLogger(): Logger = LoggerFactory.getLogger(com.example.customermanagementservice.UserManagementServiceApplication::class.java)

    @Bean
    fun  filterChain( http : HttpSecurity) : SecurityFilterChain =
        http.csrf()
            .disable()
            .authorizeHttpRequests()
            .anyRequest()
            .permitAll()
            .and()
            .build()




}