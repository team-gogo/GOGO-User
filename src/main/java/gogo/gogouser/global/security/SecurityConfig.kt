package gogo.gogouser.global.security

import gogo.gogouser.domain.auth.application.AuthReader
import gogo.gogouser.domain.user.persistence.Authority
import gogo.gogouser.global.filter.AuthenticationFilter
import gogo.gogouser.global.filter.LoggingFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authorization.AuthorizationDecision
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.access.intercept.RequestAuthorizationContext
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.util.matcher.IpAddressMatcher
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import java.util.function.Supplier


@Configuration
class SecurityConfig(
    private val customAccessDeniedHandler: CustomAccessDeniedHandler,
    private val customAuthenticationEntryPointHandler: CustomAuthenticationEntryPointHandler,
    private val authenticationFilter: AuthenticationFilter,
    private val loggingFilter: LoggingFilter,
    private val securityProperties: SecurityProperties,
) {

    @Bean
    fun filterChain(http: HttpSecurity, authReader: AuthReader): SecurityFilterChain {
        http.formLogin { it.disable() }
            .httpBasic { it.disable() }

        http.csrf { it.disable() }
            .cors { it.configurationSource(corsConfigurationSource()) }

        http.exceptionHandling { handling ->
            handling.accessDeniedHandler(customAccessDeniedHandler)
            handling.authenticationEntryPoint(customAuthenticationEntryPointHandler)
        }

        http.sessionManagement { sessionManagement ->
            sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        }

        http.addFilterBefore(loggingFilter, UsernamePasswordAuthenticationFilter::class.java)
            .addFilterBefore(authenticationFilter, LoggingFilter::class.java)

        http.authorizeHttpRequests { httpRequests ->
            httpRequests
                // health check
                .requestMatchers(HttpMethod.GET, "/actuator/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/user/health").permitAll()

                // auth
                .requestMatchers(HttpMethod.POST, "/user/auth/login").permitAll()
                .requestMatchers(HttpMethod.POST, "/user/auth/refresh").permitAll()
                .requestMatchers(HttpMethod.POST, "/user/auth/signup").hasRole(Authority.UNAUTHENTICATED.name)

                // student
                .requestMatchers(HttpMethod.GET, "/user/student/search").hasAnyRole(Authority.USER.name, Authority.STAFF.name)
                .requestMatchers(HttpMethod.GET, "/user/student/me").hasAnyRole(Authority.USER.name, Authority.STAFF.name)
                .requestMatchers(HttpMethod.PATCH, "/user/student/me").hasAnyRole(Authority.USER.name, Authority.STAFF.name)

                // server to server
                .requestMatchers(HttpMethod.GET, "/user/student").access { _, context -> hasIpAddress(context) }
                .requestMatchers(HttpMethod.GET, "/user/student/bundle").access { _, context -> hasIpAddress(context) }

                .anyRequest().denyAll()
        }

        return http.build()
    }

    private fun hasIpAddress(context: RequestAuthorizationContext): AuthorizationDecision {
        val ALLOWED_IP_ADDRESS_MATCHER = IpAddressMatcher("${securityProperties.serverToServerIp}${securityProperties.serverToServerSubnet}")
        return AuthorizationDecision(ALLOWED_IP_ADDRESS_MATCHER.matches(context.request))
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration()

        // plz custom allowed client origins
        configuration.allowedOrigins = listOf("*")
        configuration.allowedHeaders = listOf("*")

        configuration.allowedMethods = listOf(
            HttpMethod.GET.name(),
            HttpMethod.POST.name(),
            HttpMethod.PUT.name(),
            HttpMethod.PATCH.name(),
            HttpMethod.DELETE.name(),
            HttpMethod.OPTIONS.name()
        )

        configuration.allowCredentials = true
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()

}
