package naero.naeroserver.config;

import naero.naeroserver.jwt.JwtAccessDeniedHandler;
import naero.naeroserver.jwt.JwtAuthenticationEntryPoint;
import naero.naeroserver.jwt.JwtFilter;
import naero.naeroserver.jwt.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    // JWT 토큰을 발급하고 검증하는 Token Provider
    private final TokenProvider tokenProvider;
    // 인증 실패 관련 예외
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    // 접근 거부 관련 예외
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Autowired
    public SecurityConfig(TokenProvider tokenProvider,
                          JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
                          JwtAccessDeniedHandler jwtAccessDeniedHandler) {
        this.tokenProvider = tokenProvider;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception {
        http.csrf(csrf -> csrf.disable()).exceptionHandling(exception -> {
            // 필요한 권한이 없을 때 403(Forbidden)을 반환
            exception.authenticationEntryPoint(jwtAuthenticationEntryPoint);
            // 인증되지 않은 접근 시 401(Unauthorized)를 반환
            exception.accessDeniedHandler(jwtAccessDeniedHandler);
        });
        http.authorizeHttpRequests(auth -> {
                    auth.anyRequest().permitAll();

//                    auth.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll();
//                    //모두가 요청 가능한 페이지
//                    auth.requestMatchers("/auth/**").permitAll();
//                    auth.requestMatchers("").permitAll();

                    //로그인한 유저부터 요청 가능한 api
//                    auth.requestMatchers("").hasAnyAuthority("USER");

                    //사업자 아이디로 로그인했을 때 부터 요청 가능한 api
//                    auth.requestMatchers("").hasAnyAuthority("USER");


                    //swagger 사용시
//            auth.requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**").permitAll();

                }).sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(cors -> {
                })
                .addFilterBefore(new JwtFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class);;


        return http.build();
    }

    /* 목차. 4. CORS 설정용 Bean(허용 할 origin과 httpMethod 종류와 header 값) */
    @Bean
    CorsConfigurationSource corsConfigurationSource() {

        // CORS 관련 설정을 진행할 객체 생성
        CorsConfiguration configuration = new CorsConfiguration();

        // 허용할 도메인
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000","http://semin.store"));
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
