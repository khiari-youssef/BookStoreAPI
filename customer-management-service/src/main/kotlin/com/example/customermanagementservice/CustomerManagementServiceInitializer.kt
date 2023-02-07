package com.example.customermanagementservice

import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent
import java.util.logging.Level
import java.util.logging.Logger

class CustomerManagementServiceInitializer : SpringBootServletInitializer() {


    override fun configure(application: SpringApplicationBuilder): SpringApplicationBuilder {
        application.listeners(object: ApplicationListener<ContextRefreshedEvent> {
            override fun onApplicationEvent(event: ContextRefreshedEvent) {
                Logger.getGlobal().log(Level.ALL,event.applicationContext.applicationName)
            }
        })
        return application.sources(com.example.customermanagementservice.UserManagementServiceApplication::class.java)
    }

}
