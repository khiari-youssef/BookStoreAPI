package com.example.usermanagementservice.infrastructure.repositories.userRepository

import com.example.usermanagementservice.coreDomain.ExternalToDomainEntityMapper
import com.example.usermanagementservice.coreDomain.entities.BookUser
import com.example.usermanagementservice.coreDomain.mapToDomain
import com.example.usermanagementservice.coreDomain.repositoryContracts.UserRepositoryContract
import com.example.usermanagementservice.infrastructure.dto.RedisBookUserDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.json.JsonParserFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.time.Duration

@Repository
class UserRepositoryRedisImpl @Autowired constructor(
    private val userRepositoryRedisDTOmapper: ExternalToDomainEntityMapper<RedisBookUserDTO, BookUser>,
    private val redis: RedisTemplate<String, Any>
) : UserRepositoryContract {

    companion object{
        private const val USER_REDIS_KEY : String = "user:redis:key"
    }

    override fun getUser(): Flow<BookUser> = flow {
        emit(redis.opsForValue().get(USER_REDIS_KEY) as RedisBookUserDTO?)
    }
        .map { dto->
          dto ?: throw NullPointerException()
        }
        .mapToDomain(mapper = userRepositoryRedisDTOmapper)

    override suspend fun saveUser(bookUser: BookUser) : BookUser {
        val jsonSerializedUser = Json.encodeToString(
            userRepositoryRedisDTOmapper.fromDomain(bookUser)
        )
        redis.opsForValue().set(
            USER_REDIS_KEY,
            jsonSerializedUser
        )
        redis.expire(USER_REDIS_KEY, Duration.ofSeconds(20))

        return userRepositoryRedisDTOmapper.toDomain(Json.decodeFromString<RedisBookUserDTO>((redis.opsForValue().get(USER_REDIS_KEY) as String)))
    }




}