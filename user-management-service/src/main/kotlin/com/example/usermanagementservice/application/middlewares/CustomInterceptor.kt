package com.example.usermanagementservice.application.middlewares

import jakarta.servlet.*

//@Component
class CustomInterceptor  : Filter {

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
      //  chain?.doFilter(request,response)
        println(request?.parameterNames)
        /*if ((request is RequestFacade?) && request?.getHeader("Authorization").isNullOrBlank().not()){
            chain?.doFilter(request,response)
        } else throw Exception("Unauthorized")*/
    }

    override fun init(filterConfig: FilterConfig?) {
        super.init(filterConfig)
    }

}