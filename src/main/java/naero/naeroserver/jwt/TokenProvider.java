package naero.naeroserver.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import naero.naeroserver.entity.auth.TblUserRole;
import naero.naeroserver.entity.user.TblUser;
import naero.naeroserver.exception.TokenException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;


@Component
public class TokenProvider {

    private static final Logger log = LoggerFactory.getLogger(TokenProvider.class);
    private static final String AUTHORITIES_KEY = "auth";
    private static final String BEARER_TYPE = "bearer";
    //ms 기준
    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 60;


    private final UserDetailsService userDetailsService;

    // java.security.Key로 import 할 것
    private final Key key;

    /* 설명: @Value
     *  스프링이 관리하는 빈(bean)에서 프로퍼티 값들을 주입받기 위한 어노테이션.
     *  이 어노테이션은 필드 값, 생성자 인자, 메소드 인자에 사용되며,
     *  SpringBoot의 설정 파일(application.yaml)로부터 값을 읽어와 주입한다.
     *  주입할 값은 스프링 Expression Language (SpEL) or 프로퍼티 키를 사용해 런타임에 설정 가능하다.
     *  이를 통해 설정 파일에서 관리하는 프로퍼티 값을 소스 코드로 불러와 직접 사용할 수 있게 된다.
     * */
    public TokenProvider(@Lazy UserDetailsService userDetailsService,
                         @Value("${jwt.secret}") String secretKey) {

        // UserDetailsService 인스턴스를 클래스 필드에 할당.
        this.userDetailsService = userDetailsService;
        // 이후, 전달된 secretKey를 Base64 디코딩해서 JWT 서명에 사용할 Key 객체를 생성 및 초기화.
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    /* 목차. 1. 토큰 생성 메서드 */
    public TokenDTO generateTokenDTO(TblUser user) {

        log.info("[TokenProvider] generateTokenDTO() Start");

        // 매개변수로 전달된 회원의 권한을 담기 위한 리스트 생성
        List<String> roles = new ArrayList<>();
        // 회원의 권한을 모두 추출해 리스트에 추가
        for(TblUserRole userRole : user.getTblUserRoles()) {
            roles.add(userRole.getRole().getRoleName());
        }

        log.info("[TokenProvider] authorized authorities {}", roles);

        // 현재 시간(msec)
        long now = System.currentTimeMillis();
        // 위에서 밀리초로 구해놓은 현재 시간에 토큰 만료 시간을 더해 유효 기간을 설정
        Date accessTokenExpiresIn = new Date(now + ACCESS_TOKEN_EXPIRE_TIME);

        // JWT 토큰 생성
        String accessToken = Jwts.builder()
                // 회원 아이디를 "sub"이라는 클레임으로 토큰에 추가
                .setSubject(user.getUsername())
                // 회원의 권한들을 "auth"라는 클레임으로 토큰에 추가
                .claim(AUTHORITIES_KEY, roles)
                // 만료 시간 설정
                .setExpiration(accessTokenExpiresIn)
                // 서명 및 알고리즘 설정
                .signWith(key, SignatureAlgorithm.HS512)
                // 압축 = header + payload + signature
                .compact();
        System.out.println("조립된 accessToken 확인 = " + accessToken);

        log.info("[TokenProvider] generateTokenDTO() End");

        return new TokenDTO(BEARER_TYPE, user.getUserFullname(), accessToken, accessTokenExpiresIn.getTime());
    }

    /* 목차. 2. 토큰에 등록된 클레임의 sub에서 해당 회원의 아이디를 추출 */
    public String getUserId(String token) {

        return Jwts.parserBuilder()
                // 서명 키 설정
                .setSigningKey(key)
                // 파서 빌드
                .build()
                // JWT 토큰을 파싱하여 Claims Jws 객체로 변환
                .parseClaimsJws(token)
                // payload의 Claims 추출
                .getBody()
                // Claims 중에 등록 클레임에 해당하는 sub값 추출(회원 아이디)
                .getSubject();
    }

    /* 목차. 3. AccessToken으로 인증 객체 추출(이 클래스의 5번과 2번에 해당하는 메서드를 사용) */
    public Authentication getAuthentication(String token) {

        log.info("[TokenProvider] getAuthentication() Start");

        // 매개변수로 전달된 토큰에서 claim들 추출(토큰 복호화)
        Claims claims = parseClaims(token);		// 아래 5번에서 만든 메소드

        // 권한 정보가 없는 토큰에 대한 예외 처리
        if (claims.get(AUTHORITIES_KEY) == null) {
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }

        // 클레임에서 권한 정보(auth) 추출
        Collection<? extends GrantedAuthority> authorities =
                // ex: "ROLE_ADMIN"이랑 "ROLE_MEMBER"같은 문자열이 들어있는 문자열 배열
                //우리 DB 에서는 tbl_role의 정보값

                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(role -> new SimpleGrantedAuthority(role))
                        // List<SimpleGrantedAuthority>로 수집.
                        .collect(Collectors.toList());

        log.info("[TokenProvider] authorized authorities {}", authorities);

        // Spring Security에서 제공하는 UserDetailsService를 이용해 사용자 정보를 로드
        // 이 때, UserDetailsService를 구현한 서비스 클래스를 생성해야 한다.
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserId(token));

        log.info("[TokenProvider] getAuthentication() End");

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    /* 목차. 4. 토큰 유효성 검사 */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("[TokenProvider] 잘못된 JWT 서명입니다.");
            throw new TokenException("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            log.info("[TokenProvider] 만료된 JWT 토큰입니다.");
            throw new TokenException("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            log.info("[TokenProvider] 지원되지 않는 JWT 토큰입니다.");
            throw new TokenException("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            log.info("[TokenProvider] JWT 토큰이 잘못되었습니다.");
            throw new TokenException("JWT 토큰이 잘못되었습니다.");
        }
    }

    /* 목차. 5. AccessToken에서 클레임을 추출하는 메서드 */
    private Claims parseClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            /* 설명. 토큰이 만료되어 예외가 발생하더라도 클레임 값들은 뽑을 수 있다. */
            return e.getClaims();
        }
    }
}
