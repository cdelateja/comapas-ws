package com.comapas.ws.config

import com.comapas.ws.util.TokenUtil
import com.comapas.ws.util.WebUtil
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.AuditorAware
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import java.util.*

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
class JpaAuditingConfiguration(
    private val webUtil: WebUtil
) {

    @Bean
    open fun auditorProvider(): AuditorAware<String> {
        return SecurityAuditorAware(webUtil)
    }
}

class SecurityAuditorAware(
    private val webUtil: WebUtil
) : AuditorAware<String> {

    override fun getCurrentAuditor(): Optional<String> {
        return if (!webUtil.ifOutOfWebRequest()) {
            try {
                Optional.of(TokenUtil.getUsername(webUtil.getToken()))
            } catch (e: IndexOutOfBoundsException) {
                Optional.of("admin")
            }
        } else {
            Optional.of("admin")
        }
    }
}
