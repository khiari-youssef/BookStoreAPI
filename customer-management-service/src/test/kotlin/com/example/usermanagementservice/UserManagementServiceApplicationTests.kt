package com.example.usermanagementservice

import com.example.usermanagementservice.infrastructure.dependencies.AppTestingConfigurationProvider
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(
	classes = [AppTestingConfigurationProvider::class]
)
class UserManagementServiceApplicationTests {

	@Test
	fun contextLoads() {
	}

}
