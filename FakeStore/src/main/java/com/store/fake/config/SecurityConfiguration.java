package com.store.fake.config;

import com.store.fake.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    SecurityFilterChain springWebFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> {
                    CorsConfigurationSource source = request -> {
                        CorsConfiguration configuration = new CorsConfiguration();
                        configuration.setAllowedOrigins(List.of("*"));
                        configuration.setAllowedMethods(List.of(CorsConfiguration.ALL));
                        configuration.setAllowedHeaders(List.of(CorsConfiguration.ALL));
                        return configuration;
                    };
                    cors.configurationSource(source);
                })
                .headers(httpSecurityHeadersConfigurer -> httpSecurityHeadersConfigurer
                        .httpStrictTransportSecurity(hstsConfig -> hstsConfig
                                .includeSubDomains(true)
                                .maxAgeInSeconds(31536000) // a year
                                .preload(true)))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/v1/FakeStore/Login").permitAll()
                        .requestMatchers("/**").permitAll()
                        .requestMatchers("/swagger-ui/**",  "/v3/api-docs/**","/v2/api-docs/**","/swagger-resources/**").permitAll()

                        .anyRequest().authenticated()
                )
                .addFilterBefore(new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    public void configure(WebSecurity web) {

        web.ignoring().requestMatchers("/v2/api-docs",
                "/configuration/ui",
                "/v3/api-docs/**",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**");
    }
}
