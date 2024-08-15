package com.ngfrt.appmain.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                authorizeRequests -> authorizeRequests
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .requestMatchers("/api/**", "/halls/**", "/halls/details", "/halls/details/**").permitAll()
                        .requestMatchers("/event/attend", "/event/plan", "/events/featured", "/event/purchase", "/event/purchase**", "/event/purchase/**").permitAll()
                        .requestMatchers("/user/login").anonymous()
                        .requestMatchers("/user/register").anonymous()
                        .requestMatchers("/", "/error").permitAll()
                        .anyRequest().authenticated()

        ).formLogin(formLogin -> {
           formLogin.loginPage("/user/login")
                   .usernameParameter("email")
                   .passwordParameter("password")
                   .defaultSuccessUrl("/", true)
                   .failureForwardUrl("/user/login-error");
        }).logout(logout -> {
            logout
                    .logoutUrl("/user/logout")
                    .logoutSuccessUrl("/")
                    .invalidateHttpSession(true);
        }).exceptionHandling(exceptionHandling ->
                exceptionHandling
                        .accessDeniedPage("/")
        );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    }
}
