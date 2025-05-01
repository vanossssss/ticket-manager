package com.project.ticket_manager.config;

import com.project.ticket_manager.service.CustomOAuthUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomOAuthUserService customOAuthUserService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/api/"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2Login(config -> config
                        .defaultSuccessUrl("/api/welcome")
                        .userInfoEndpoint(userInfo -> userInfo.userService(customOAuthUserService))
                )
                .logout(logout -> logout
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/login")
                                .invalidateHttpSession(true)
                                .deleteCookies("JSESSIONID")
                ).csrf(Customizer.withDefaults());

        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_GLOBAL);

        return http.build();

    }
}
