package knu.networksecuritylab.appserver.config.secure;

import knu.networksecuritylab.appserver.config.jwt.JwtAuthenticationFilter;
import knu.networksecuritylab.appserver.exception.handler.auth.CustomAccessDeniedHandler;
import knu.networksecuritylab.appserver.exception.handler.auth.CustomAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
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
                // permitAll() -> 모두 허용     authenticated() -> 인증 필요     hasRole() -> 권한 필요
                .antMatchers("/api/v1/users/sign-up", "/api/v1/users/sign-in").permitAll()
                .antMatchers("api/v1/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                // 권한을 확인하는 과정에서 통과하지 못하는 예외가 발생할 경우, 예외를 전달 -> CustomAccessDeniedHandler
                .exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler())
                .and()
                // 인증 과정에서 예외가 발생할 경우, 예외를 전달 -> CustomAuthenticationEntryPoint
                .exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                .and()
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
