package com.example.usermanagementservice.infrastructure.dependencies

import org.springframework.beans.factory.annotation.Value
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate



@Configuration
class RedisConfigurationProvider {


    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    @Bean
    fun provideJedisFactory(
        @Value("\${redis.database.host}")  host : String,
        @Value("\${redis.database.port}")  port : Int
    ): JedisConnectionFactory = JedisConnectionFactory(
        RedisStandaloneConfiguration(
            host,
            port
        )
    )

    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    @Bean
    fun provideRedisTemplate(factory: JedisConnectionFactory): RedisTemplate<String, Any>
            = RedisTemplate<String, Any>().apply {
        setConnectionFactory(factory)
    }

}