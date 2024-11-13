package naero.naeroserver.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
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

            auth.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll();
            //모두가 요청 가능한 페이지
            auth.requestMatchers("/auth/**","").permitAll();
            auth.requestMatchers("").permitAll();

            //로그인한 유저부터 요청 가능한 api
            auth.requestMatchers("").hasAnyAuthority("USER");

            //사업자 아이디로 로그인했을 때 부터 요청 가능한 api
            auth.requestMatchers("").hasAnyAuthority("USER");


            //swagger 사용시
//            auth.requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**").permitAll();

//            auth.anyRequest().permitAll();
        })
        .cors(cors -> {});


        return http.build();
    }
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                .requestMatchers("/css/**", "/js/**", "/images/**", "/lib/**", "/productimgs/**");
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
