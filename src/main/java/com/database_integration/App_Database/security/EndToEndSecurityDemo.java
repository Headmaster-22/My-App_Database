package com.database_integration.App_Database.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class EndToEndSecurityDemo {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Disable CSRF if you're building an API (Keep enabled for form apps)
                .csrf(csrf -> csrf.disable())

                // Authorization rules
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/registration/**").permitAll()
                        .anyRequest().authenticated()
                )

                // Login configuration
                .formLogin(form -> form
                        .loginPage("/login")    // Custom login page
                        .usernameParameter("email")     // Login form input name
                        .defaultSuccessUrl("/", true)       // Redirect after success
                        .permitAll()
                )

                // Logout configuration (no matcher needed unless you want a custom path)
                .logout(logout -> logout
                        .logoutUrl("/logout")       // Optional, default is "/logout"
                        .logoutSuccessUrl("/")     // Redirect after logout
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .permitAll()
                );

        return http.build();
    }

}
