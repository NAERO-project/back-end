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

/* CSRF:
 *  CSRF stands for Cross-Site Request Forgery. It is a security vulnerability that allows an
 *  attacker to trick a victim into performing actions they did not intend to on a different
 *  website, typically one where the user is authenticated.
 * How to Protect Against CSRF
 *  1. CSRF Tokens: Most web frameworks (including Spring Security) use CSRF tokens to protect
 *      against CSRF attacks. When a user makes a request to a protected resource, the server
 *      sends a unique, secret toke in the response. This token must be included in subsequent
 *      requests, and the server verifies it before performing any actions. If the token is missing
 *      or incorrect, the request is denied.
 *  2. Same-Site Cookies: Modern browsers support Same-Site cookies, which prevent cookies from being
 *      sent along with cross-site requests.
 *  3. Validating Referrers: Some websites check the Referer header of the request to ensure that it
 *      is coming from a trusted source.
 * CSRF Protection in Spring Security
 *  By default, Spring Security provides built-in protection against CSRF attacks. However, in some
 *  cases (like building a public API), you may choose to disable it. Note: Disabling CSRF protection
 *  can be risky. Make sure you understand the implications and use it appropriately.
 * When to Use CSRF Protection
 *  - Use CSRF Protection for web applications where the user interacts with the website using a browser
 *      and has a session (e.g., forms that perform actions like updating data).
 *  - CSRF Protection May Not Be Necessary for stateless APIs (e.g., REST APIs used by mobile applications)
 *      where authentication is handled differently (like with tokens).
 *  */
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

                    auth.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll();
                    //모두가 요청 가능한 페이지
                    auth.requestMatchers("/auth/**").permitAll();
//                    auth.requestMatchers("").permitAll();

                    //로그인한 유저부터 요청 가능한 api
//                    auth.requestMatchers("").hasAnyAuthority("USER");

                    //사업자 아이디로 로그인했을 때 부터 요청 가능한 api
//                    auth.requestMatchers("").hasAnyAuthority("USER");


                    //swagger 사용시
//            auth.requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**").permitAll();

//            auth.anyRequest().permitAll();
                }).sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(cors -> {
                })
                .addFilterBefore(new JwtFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class);;


        return http.build();
    }

    /* CORS: CORS stands for Cross-Origin Resource Sharing.
     *  - It is a security feature implemented by web browsers to prevent websites from making
     *    requests to a different domain than the one that served the original content, unless
     *    explicitly allowed.
     * What is the Same-Origin Policy?
     *  - By default, web browsers enforce the Same-Origin Policy for security reasons. This policy
     *    ensures that a web page can only make requests to the same origin (domain, protocol, and
     *    port) from which it was loaded.
     *  - For example, if you load a page from https://example.com, the page can only make requests
     *    to https://example.com, not to https://otherdomain.com
     * Why is CORS Needed?
     *  Web applications often need to make requests to different domains or APIs. For instance, a
     *  frontend app hosted at http://localhost:3000 might need to make a request to an API running
     *  at http://localhost: 8000. The Same-Origin Policy would block such requests by default. CORS
     *  provides a way for the server to allow requests from a different origin.
     * How CORS Works?
     *  When a web page makes a cross-origin HTTP requests (e.g., using fetch or XMLHttpRequest), the
     *  browser sends a CORS preflight request (an OPTIONS request) to the server to check if the
     *  cross-origin requests is allowed.
     *  1. Preflight Request:
     *  - The browser sends and OPTIONS request to the server with headers like Origin, Access-Control-Request-Method,
     *    and Access-Control-Request-Headers.
     *  2. Response from the Server:
     *  - The server must respond with specific headers to allow the cross-origin request:
     *    - Access-Control-Allow-Origin: Specifies which origin(s) are allowed to access the resource.
     *    - Access-Control-Allow-Methods: Lists the HTTP methods (e.g., GET, POST, PUT)that are allowed.
     *    - Access-Control-Allow-Headers: Specifies which headers are allowed in the request.
     *  - If the server's response includes these headers and the browser deems the response valid, the
     *    browser allows the cross-origin request to proceed.
     * Common CORS Error?
     *  If a server does not return the required CORS headers, the browser will block the cross-origin
     *  request, and you will see an error like:
     *      - Access to XMLHttpRequest at 'http://localhost:8080/api' from origin 'http://localhost:3000' has been
     *        blocked by CORS policy: No 'Access-Control-Allow-Origin' header is present on the requested resource.
     * Key Points to Remember?
     *  - CORS is a browser-based security feature. It does not apply to server-to-server communication.
     *  - The server must be configured to allow cross-origin requests.
     *  - CORS headers are sued to specify which domains are allowed to make requests to the server.
     * */
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
