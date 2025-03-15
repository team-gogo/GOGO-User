package gogo.gogouser.global.security

import gogo.gogouser.domain.auth.application.AuthReader
import gogo.gogouser.domain.user.persistence.Authority
import gogo.gogouser.global.filter.AuthenticationFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
class SecurityConfig(
    private val customAccessDeniedHandler: CustomAccessDeniedHandler,
    private val customAuthenticationEntryPointHandler: CustomAuthenticationEntryPointHandler,
    private val authenticationFilter: AuthenticationFilter
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

        http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter::class.java)

        http.authorizeHttpRequests { httpRequests ->
            httpRequests
                // health check
                .requestMatchers(HttpMethod.GET, "/user/health").permitAll()

                // auth
                .requestMatchers(HttpMethod.POST, "/user/auth/login").permitAll()
                .requestMatchers(HttpMethod.POST, "/user/auth/refresh").permitAll()
                .requestMatchers(HttpMethod.POST, "/user/auth/signup").hasRole(Authority.UNAUTHENTICATED.name)

                // student
                .requestMatchers(HttpMethod.GET, "/user/student/search").hasAnyRole(Authority.USER.name, Authority.STAFF.name)

                // server to server
                .requestMatchers(HttpMethod.GET, "/user/student").permitAll()
                .requestMatchers(HttpMethod.GET, "/user/student/bundle").permitAll()

                // test
                .requestMatchers(HttpMethod.POST, "/user/auth/test-login/{user_id}").permitAll()

                .anyRequest().denyAll()
        }

        return http.build()
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration()

        // plz custom allowed client origins
        configuration.allowedOrigins = listOf("*")

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
