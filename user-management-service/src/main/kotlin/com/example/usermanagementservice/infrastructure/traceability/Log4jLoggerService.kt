package com.example.usermanagementservice.infrastructure.traceability

import org.slf4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component


@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
@Component("Log4jLoggerService")
class Log4jLoggerService @Autowired constructor(
    private val logger: Logger
) : LoggerService {


    override fun logE(tag: String, message: String) {
        logger.error(message)
    }

    override fun logI(tag: String, message: String) {
        logger.info(message)
    }

    override fun logW(tag: String, message: String) {
        logger.warn(message)
    }

    override fun log(tag: String, message: String) {
        logger.debug("")
    }


}