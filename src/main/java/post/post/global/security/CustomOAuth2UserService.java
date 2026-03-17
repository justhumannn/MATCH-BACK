package post.post.global.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import post.post.domain.user.UserRepository;
import post.post.domain.user.UserService;
import post.post.domain.user.dto.req.UserAddRequestDto;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;
    private final UserService userService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");
        if (email == null || !email.endsWith("@bssm.hs.kr")) {
            throw new OAuth2AuthenticationException("학교 계정(@bssm.hs.kr)으로만 로그인할 수 있습니다.");
        }
        if (userRepository.findByEmail(email).isEmpty()) {
            UserAddRequestDto addRequestDto = UserAddRequestDto.builder()
                    .name(name)
                    .email(email)
                    .build();
            userService.register(addRequestDto);
        }
        return oAuth2User;
    }
}
