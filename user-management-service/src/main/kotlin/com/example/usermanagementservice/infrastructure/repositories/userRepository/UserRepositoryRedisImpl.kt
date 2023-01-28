package com.example.usermanagementservice.infrastructure.repositories.userRepository

import com.example.usermanagementservice.coreDomain.ExternalToDomainEntityMapper
import com.example.usermanagementservice.coreDomain.entities.DomainUser
import com.example.usermanagementservice.coreDomain.mapToDomain
import com.example.usermanagementservice.coreDomain.repositoryContracts.UserRepositoryContract
import com.example.usermanagementservice.infrastructure.dto.RedisDTOUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository
import java.time.Duration

@Repository
class UserRepositoryRedisImpl @Autowired constructor(
    private val userRepositoryRedisDTOmapper: ExternalToDomainEntityMapper<RedisDTOUser, DomainUser>,
    private val redis: RedisTemplate<String, Any>
) : UserRepositoryContract {

    companion object{
        private const val USER_REDIS_KEY : String = "user:redis:key"
    }

    override fun getUser(): Flow<DomainUser> = flow {
        emit(redis.opsForValue().get(USER_REDIS_KEY) as RedisDTOUser?)
    }
        .map { dto->
          dto ?: throw NullPointerException()
        }
        .mapToDomain(mapper = userRepositoryRedisDTOmapper)

    override suspend fun saveUser(domainUser: DomainUser) : DomainUser {
        redis.opsForValue().set(
            USER_REDIS_KEY,
            userRepositoryRedisDTOmapper.fromDomain(domainUser)
        )
        redis.expire(USER_REDIS_KEY, Duration.ofSeconds(20))
        return userRepositoryRedisDTOmapper.toDomain(redis.opsForValue().get("user") as RedisDTOUser)
    }


}