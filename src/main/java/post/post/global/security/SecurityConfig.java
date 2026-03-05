package post.post.global.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // 개발 편의성을 위해 일단 비활성화
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/login**").permitAll() // 로그인 관련 페이지는 모두 접근 허용
                        .anyRequest().authenticated() // 그 외의 모든 요청은 로그인한 사용자만 접근 가능
                )
                .oauth2Login(oauth2 -> oauth2
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(customOAuth2UserService) // 위에서 만든 커스텀 서비스 등록
                        )
                        .defaultSuccessUrl("/home", true) // 로그인 성공 시 이동할 엔드포인트
                );

        return http.build();
    }
}