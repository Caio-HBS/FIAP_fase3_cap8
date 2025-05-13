package br.com.fiap.upenergy.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private VerificarToken verificarToken;

    public SecurityConfig(VerificarToken verificarToken) {
        this.verificarToken = verificarToken;
    }

    @Bean
    public SecurityFilterChain filtrarCadeiaDeSeguranca(
            HttpSecurity httpSecurity
    ) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/alertas").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/usuarios/**").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/usuarios/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/equipamentos/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/v1/equipamentos/**").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/v1/sensores/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/v1/sensores/**").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/v1/leituras/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/v1/leituras/**").authenticated()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(verificarToken, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration
    ) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
