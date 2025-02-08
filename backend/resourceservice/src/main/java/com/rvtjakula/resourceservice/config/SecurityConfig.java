package com.rvtjakula.resourceservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
		jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new RoleConverter());
		http
				.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(arm->arm
						.requestMatchers(AppConstants.PUBLIC_URLS).permitAll()
						.requestMatchers(AppConstants.USER_URLS).hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
						.requestMatchers(AppConstants.ADMIN_URLS).hasAuthority("ROLE_ADMIN")
						.anyRequest()
						.permitAll())
				.sessionManagement(sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.exceptionHandling(ehc-> ehc.accessDeniedHandler((request, response, authException) ->
						response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized")));
		http.oauth2ResourceServer(rsc -> rsc.jwt(jwtConfigurer ->
				jwtConfigurer.jwtAuthenticationConverter(jwtAuthenticationConverter)));
		return  http.build();
	}

}