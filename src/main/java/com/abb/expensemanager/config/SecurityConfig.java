package com.abb.expensemanager.config;

import com.abb.expensemanager.util.constants.SecurityConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

/**
 * The application security configuration.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * The main app security configuration.
     *
     * @param http the {@link HttpSecurity}.
     * @return a {@link SecurityFilterChain} with the configuration.
     * @throws Exception the error.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .cors().configurationSource(corsConfigurationSource())
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests(request -> request
                        .requestMatchers(SecurityConstants.WHITE_LIST).permitAll()
                        .anyRequest().authenticated());

        return http.build();
    }

    /**
     * The CORS configuration.
     *
     * @return a {@link UrlBasedCorsConfigurationSource}.
     */
    private CorsConfigurationSource corsConfigurationSource() {
        final var config = new CorsConfiguration();

        config.addAllowedOriginPattern("*");
        config.setAllowedHeaders(List.of("*"));
        config.setAllowedMethods(List.of("*"));

        final var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }

}
