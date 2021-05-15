package com.comapas.ws.config

import com.comapas.ws.service.OauthService
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.Ordered
import org.springframework.stereotype.Component
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Configuration
class WebMvcConfig(private val interceptor: Interceptor) : WebMvcConfigurer {

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(interceptor)
    }

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedMethods("*")
    }

    @Bean
    fun customCorsFilter(): FilterRegistrationBean<*> {
        val source = UrlBasedCorsConfigurationSource()
        val config = CorsConfiguration()

        config.allowCredentials = true
        config.allowedOriginPatterns = listOf("*")
        config.addAllowedHeader("*")
        config.addAllowedMethod("*")
        source.registerCorsConfiguration("/**", config)

        val bean: FilterRegistrationBean<*> = FilterRegistrationBean(CorsFilter(source))

        bean.order = Ordered.HIGHEST_PRECEDENCE
        return bean
    }
}

@Component
class Interceptor(
    private val oauthService: OauthService,
) : HandlerInterceptor {

    @Throws(Exception::class)
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        if ("OPTIONS" == request.method) {
            return true
        }
        return oauthService.validate()
    }

}
