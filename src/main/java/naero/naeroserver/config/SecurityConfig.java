package naero.naeroserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception {
        http.authorizeHttpRequests(auth -> {
                    auth.anyRequest().permitAll();
                })
                .cors(cors -> {})
                .csrf(csrf -> csrf.disable());

        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {

        // CORS 관련 설정을 진행할 객체 생성
        CorsConfiguration configuration = new CorsConfiguration();

        // 허용할 도메인
        configuration.setAllowedOrigins(Arrays.asList(
                "http://localhost:3000",
                "http://localhost:3333",
                "http://web-server:3333"
        ));
        // 허용할 메서드
        configuration.setAllowedMethods(Arrays.asList("GET", "PUT", "POST", "DELETE"));
        // 허용할 헤더
        configuration.setAllowedHeaders(
                Arrays.asList(
                        // 서버에서 응답할 때, 어떤 출처(origin)에서 요청을 허용할지를 결정하는 헤더
                        "Access-Control-Allow-Origin",
                        // 요청 또는 응답의 콘텐츠 유형(미디어 타입)
                        "Content-type",
                        // CORS 요청을 통해 허용되는 HTTP 요청 헤더
                        "Access-Control-Allow-Headers",
                        // 클라이언트가 서버로 인증 정보(여기서는 JWT)를 보내기 위해 사용되는 헤더
                        "Authorization",
                        //XMLHttpRequest로 요청이 이루어졌는지를 나타내기 위해 사용
                        "X-Requested-With"
                )
        );

        // CORS 설정을 적용할 URL 패턴 설정
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}