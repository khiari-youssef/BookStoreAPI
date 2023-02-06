package com.example.usermanagementservice.infrastructure.security

import com.example.usermanagementservice.infrastructure.security.encryption.JavaNativeEncryptionServicesEncryptionTestCases
import com.example.usermanagementservice.infrastructure.security.integrity.JavaNativeDataIntegrityServiceTestingCases
import com.example.usermanagementservice.infrastructure.security.keyManagement.KeyGeneratorsTestCases
import org.junit.platform.suite.api.SelectClasses
import org.junit.platform.suite.api.SelectPackages
import org.junit.platform.suite.api.Suite
import org.junit.platform.suite.api.SuiteDisplayName


@SelectClasses(
    JavaNativeEncryptionServicesEncryptionTestCases::class,
    JavaNativeDataIntegrityServiceTestingCases::class,
    KeyGeneratorsTestCases::class
)
@SuiteDisplayName("security features test suite")
@Suite
class SecurityFeaturesTestSuite