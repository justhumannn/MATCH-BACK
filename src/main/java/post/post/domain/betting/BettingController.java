package post.post.domain.betting;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import post.post.domain.betting.dto.req.BettingAddRequestDto;
import post.post.domain.betting.dto.res.BettingResponseDto;
import post.post.domain.betting.dto.res.ListBettingResponseDto;
import post.post.domain.participation.BettingParticipationService;
import post.post.domain.participation.dto.req.UserBettingRequestDto;

@RestController
@RequestMapping("/betting")
@RequiredArgsConstructor
public class BettingController {
    private final BettingService bettingService;
    private final BettingParticipationService participationService;

    @GetMapping("")
    public ListBettingResponseDto getBetting(){
        return bettingService.getBettingCard();
    }

    @GetMapping("/{bettingId}")
    public ResponseEntity<BettingResponseDto> getBettingById(@PathVariable Long bettingId){
        return ResponseEntity.ok(bettingService.getBettingCardById(bettingId));
    }

    @PostMapping("")
    public void postBetting(@RequestBody @Valid UserBettingRequestDto userBettingRequestDto,
                            Authentication authentication){
        String userEmail = authentication.getName();
        participationService.postUserBetting(userBettingRequestDto, userEmail);
    }

    @PostMapping("/save")
    public ResponseEntity<Long> postAddBetting(@RequestBody @Valid BettingAddRequestDto bettingAddRequestDto){
        Long response = bettingService.postAddBetting(bettingAddRequestDto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/del/{bettingId}")
    public void deleteBettingById(@PathVariable Long bettingId){
        bettingService.deleteDeleteBetting(bettingId);
    }
}
