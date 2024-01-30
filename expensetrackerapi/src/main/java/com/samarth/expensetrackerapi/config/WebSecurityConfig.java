package com.samarth.expensetrackerapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("localhost:8080/api/v1/login/**").permitAll()
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .build();
    }

//    @Bean
//    protected void filterChain(AuthenticationManagerBuilder auth) throws Exception{
//        auth.inMemoryAuthentication()
//                .withUser("samarth").password("12345").authorities("admin")
//                .and()
//                .withUser("sam").password("12345").authorities("user")
//                .and()
//                .passwordEncoder(NoOpPasswordEncoder.getInstance());
//    }
}
