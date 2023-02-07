package knu.networksecuritylab.appserver.config;

import knu.networksecuritylab.appserver.config.jwt.JwtAuthenticationFilter;
import knu.networksecuritylab.appserver.exception.handler.auth.CustomAccessDeniedHandler;
import knu.networksecuritylab.appserver.exception.handler.auth.CustomAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class AuthenticationConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .httpBasic().disable() // UI 인증이 아닌 토큰 인이기 때문에 basic disable
                .csrf().disable() // Cross-Site Request Forgery 방지
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // jwt 이용 시 사용
                .and()
                .authorizeRequests()
                .antMatchers("/api/v1/users/sign-up", "/api/v1/users/sign-in").permitAll() // 허용
                .antMatchers(HttpMethod.GET, "/api/v1/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/v1/**").authenticated()
                .anyRequest().hasRole("ADMIN")
                .and()
                .exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler())
                .and()
                .exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                .and()
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
