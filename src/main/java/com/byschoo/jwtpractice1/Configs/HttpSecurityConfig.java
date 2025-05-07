package com.byschoo.jwtpractice1.Configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class HttpSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Cross-site request forgery o falsificación de petición en sitios cruzados
            // .csrf(csrf -> csrf.ignoringRequestMatchers("/api/v1/products/**"))
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests((requests) -> requests
                .requestMatchers(HttpMethod.GET, "/api/v1/products/all").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/v1/products/save").hasAnyRole("SUPER", "ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/v1/users/all").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/v1/users/save").hasAnyRole("SUPER", "ADMIN")
                .requestMatchers("/error").permitAll() //Endpoint predeterminado para notificar errores en cualquier método HTTP (Spring Security)
                .anyRequest().authenticated()
             )
             .httpBasic(Customizer.withDefaults());

        return http.build();
    }


    // FOR BASIC AUTHORIZATION
    @Bean
    public UserDetailsService userDetailsService() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        
        UserDetails user = User.withUsername("admin")
                            .password(encoder.encode("byschoo"))
                            .roles("SUPER", "ADMIN")
                            .build();

        return new InMemoryUserDetailsManager(user); // No es indispensable el user, pass y role configurado en application.properties
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
