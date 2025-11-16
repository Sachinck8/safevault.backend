package com.safevault.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.safevault.backend.service.CustomUserDetails;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final CustomUserDetails customUserDetails;
    private final OAuth2SuccessHandler successHandler;

    public SecurityConfig(CustomUserDetails customUserDetails, OAuth2SuccessHandler successHandler){
        this.customUserDetails=customUserDetails;
        this.successHandler=successHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf(csrf -> csrf.disable())
                 .authorizeHttpRequests(request -> request.requestMatchers(
                "/register",
                "/login"
        ).permitAll().anyRequest().authenticated())
        .oauth2Login(oauth -> oauth
                .loginPage("/register")
                .defaultSuccessUrl("/home", true)
            )
                 .formLogin(form -> form.loginProcessingUrl("/login").defaultSuccessUrl("/welcome", true).permitAll())
                 .logout(logout -> logout.logoutSuccessUrl("/login").permitAll()).userDetailsService(customUserDetails);
                 return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
