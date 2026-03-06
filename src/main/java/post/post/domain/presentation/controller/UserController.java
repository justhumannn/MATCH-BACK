package post.post.domain.presentation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @GetMapping("/home")
    public String home(@RequestParam(value = "token", required = false) String token) {
        if (token != null) {
            return "JWT 토큰: <br><br>" + token;
        } else {
            return "토큰이 없습니다.";
        }
    }
}
