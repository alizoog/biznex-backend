package com.biznex.config;

import com.biznex.security.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.List;

import static com.biznex.utils.WebConstants.OPEN_APIS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // disable csrf, cors and headers
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .headers(AbstractHttpConfigurer::disable)

                // making requests secure
                .authorizeHttpRequests(request -> request
                        .requestMatchers(OPEN_APIS).permitAll()
                        .anyRequest().authenticated());

        // for Bearer (jwt) authentication
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        // making request's sessions STATELESS
        http.sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

    @Bean
    public CorsFilter corsFilter() {

        var configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedOriginPatterns(List.of("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setExposedHeaders(List.of("x-auth-token"));

        var source = new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", configuration);
        return new CorsFilter(source);
    }
}
