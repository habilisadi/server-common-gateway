package com.habilisadi.gateway.config


import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain


@Configuration
class SecurityConfig {

    @Bean
    fun filterChain(
        http: ServerHttpSecurity
    ): SecurityWebFilterChain {
        return http
            .csrf { it.disable() }
            .cors { }
            .authorizeExchange { authorize ->
                authorize
                    // Public 경로 - 인증 불필요
                    .pathMatchers(
                        "/api/v1/*/public/**",
                    ).permitAll()

                    // Private 경로 - 인증 필요
//                    .pathMatchers(
//                        "/api/v1/*/private/**",
//                        "/api/v1/auth/profile",
//                        "/api/v1/auth/user-details/**",
//                        "/api/v1/user/**",
//                        "/api/v1/order/**"
//                    ).authenticated()

                    // Admin 경로 - 관리자 권한 필요
                    .pathMatchers("/api/v1/*/admin/**")
                    .hasAuthority("SCOPE_admin")

                    .anyExchange().authenticated()
            }
            .oauth2ResourceServer { oauth2 ->
                oauth2.jwt { }  // 기본 설정 사용
            }
            .build()
    }
}