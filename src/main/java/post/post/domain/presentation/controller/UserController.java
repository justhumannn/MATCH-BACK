package post.post.domain.presentation.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import post.post.domain.application.TransectionService;
import post.post.domain.presentation.dto.res.ListUserMyBettingResponseDto;

@RestController
public class UserController {
    private final TransectionService transectionService;

    public UserController(TransectionService transectionService) {
        this.transectionService = transectionService;
    }

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
        return transectionService.getMyBettingHistory(email);
    }
}
