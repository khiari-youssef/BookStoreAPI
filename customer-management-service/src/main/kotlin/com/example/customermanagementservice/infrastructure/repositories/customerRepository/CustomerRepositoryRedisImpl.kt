package com.example.customermanagementservice.infrastructure.repositories.customerRepository

import com.example.customermanagementservice.coreDomain.ExternalToDomainEntityMapper
import com.example.customermanagementservice.coreDomain.entities.BookCustomer
import com.example.customermanagementservice.coreDomain.entities.CustomerLoginCredentials
import com.example.customermanagementservice.coreDomain.mapToDomain
import com.example.customermanagementservice.coreDomain.repositoryContracts.CustomerRepositoryContract
import com.example.customermanagementservice.infrastructure.dto.redis.RedisBookCustomerDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository
import java.time.Duration

@Repository("RedisImpl")
class CustomerRepositoryRedisImpl @Autowired constructor(
    private val userRepositoryRedisDTOmapper: ExternalToDomainEntityMapper<RedisBookCustomerDTO, BookCustomer>,
    private val redis: RedisTemplate<String, Any>
) : CustomerRepositoryContract {

    companion object{
        private const val USER_REDIS_KEY : String = "user:redis:key"
    }

    override fun findCustomerByID(customerID: String): Flow<BookCustomer> = flow {
        emit(redis.opsForValue().get(USER_REDIS_KEY) as RedisBookCustomerDTO?)
    }
    .map { dto->
          dto ?: throw NullPointerException()
    }.mapToDomain(mapper = userRepositoryRedisDTOmapper)

    override fun checkUserCredentialsValidity(loginCredentials: CustomerLoginCredentials?): Flow<Boolean> = flow {
        emit(
            loginCredentials?.run {
                val cachedCustomer : RedisBookCustomerDTO? = redis.opsForValue().get(USER_REDIS_KEY) as RedisBookCustomerDTO?
                val isLoggedIn = cachedCustomer?.email == loginCredentials.email && cachedCustomer.password == loginCredentials.password
                isLoggedIn
            } ?: false
        )
    }

    override suspend fun saveCustomer(bookCustomer: BookCustomer) : BookCustomer {
        val jsonSerializedUser = Json.encodeToString(
            userRepositoryRedisDTOmapper.fromDomain(bookCustomer)
        )
        redis.opsForValue().set(
            USER_REDIS_KEY,
            jsonSerializedUser
        )
        redis.expire(USER_REDIS_KEY, Duration.ofSeconds(20))

        return userRepositoryRedisDTOmapper.toDomain(Json.decodeFromString<RedisBookCustomerDTO>((redis.opsForValue().get(USER_REDIS_KEY) as String)))
    }




}