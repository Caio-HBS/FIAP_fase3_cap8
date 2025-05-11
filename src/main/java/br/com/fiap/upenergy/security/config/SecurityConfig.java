package br.com.fiap.upenergy.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Desativa proteção CSRF
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // Permite todas as requisições
                )
                .httpBasic(httpBasic -> httpBasic.disable()) // Desativa autenticação HTTP Basic
                .formLogin(formLogin -> formLogin.disable()); // Desativa o formulário de login

        return http.build();
    }
}
