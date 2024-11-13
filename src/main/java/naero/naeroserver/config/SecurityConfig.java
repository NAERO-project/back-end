package naero.naeroserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception {
        http.csrf(csrf -> csrf.disable()).exceptionHandling(exception -> {
            // 필요한 권한이 없을 때 403(Forbidden)을 반환
//            exception.authenticationEntryPoint(jwtAuthenticationEntryPoint);
            // 인증되지 않은 접근 시 401(Unauthorized)를 반환
//            exception.accessDeniedHandler(jwtAccessDeniedHandler);
        });
        http.authorizeHttpRequests(auth -> {
            auth.anyRequest().permitAll();
        })
        .cors(cors -> {});


        return http.build();
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
