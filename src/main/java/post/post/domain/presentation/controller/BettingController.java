package post.post.domain.presentation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import post.post.domain.application.BettingService;
import post.post.domain.presentation.dto.res.ListBettingResponseDto;

import java.util.List;

@RestController
@RequestMapping("/betting")
@RequiredArgsConstructor
public class BettingController {
    private final BettingService bettingService;

    @GetMapping("/")
    public ListBettingResponseDto getBetting(){
        return bettingService.getBettingCard();
    }
}
