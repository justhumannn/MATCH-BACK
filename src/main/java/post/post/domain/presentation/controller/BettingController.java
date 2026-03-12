package post.post.domain.presentation.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import post.post.domain.application.BettingService;
import post.post.domain.application.TransectionService;
import post.post.domain.application.UserService;
import post.post.domain.presentation.dto.req.BettingAddRequestDto;
import post.post.domain.presentation.dto.req.UserBettingRequestDto;
import post.post.domain.presentation.dto.res.BettingResponseDto;
import post.post.domain.presentation.dto.res.ListBettingResponseDto;

@RestController
@RequestMapping("/betting")
@RequiredArgsConstructor
public class BettingController {
    private final BettingService bettingService;
    private final TransectionService transectionService;
    private final UserService userService;

    @GetMapping("")
    public ListBettingResponseDto getBetting(){
        return bettingService.getBettingCard();
    }

    @GetMapping("/{bettingId}")
    public BettingResponseDto getBettingById(@PathVariable Long bettingId){
        return bettingService.getBettingCardById(bettingId);
    }

    @PostMapping("")
    public void postBettingByID(@RequestBody @Valid UserBettingRequestDto userBettingRequestDto,
                                Authentication authentication){
        String userEmail = authentication.getName();
        transectionService.postUserBetting(userBettingRequestDto,userEmail);
        userService.costChange(userEmail,userBettingRequestDto.bettingCost());
    }
    @PostMapping("/save")
    public Long postAddBetting(@RequestBody @Valid BettingAddRequestDto bettingAddRequestDto){
        return bettingService.postAddBetting(bettingAddRequestDto);
    }
}