package post.post.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import post.post.domain.participation.BettingParticipationService;
import post.post.domain.participation.dto.res.ListUserMyBettingResponseDto;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final BettingParticipationService participationService;

    @GetMapping("/home")
    public String home(@RequestParam(value = "token", required = false) String token) {
        if (token != null) {
            return "JWT 토큰: <br><br>" + token;
        } else {
            return "토큰이 없습니다.";
        }
    }

    @GetMapping("/mypage")
    public ListUserMyBettingResponseDto getMyBetting(Authentication authentication){
        String email = authentication.getName();
        return participationService.getMyBettingHistory(email);
    }
}
