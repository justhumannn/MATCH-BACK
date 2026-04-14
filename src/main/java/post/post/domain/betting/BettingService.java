package post.post.domain.betting;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import post.post.domain.betting.dto.req.BettingAddRequestDto;
import post.post.domain.betting.dto.res.BettingResponseDto;
import post.post.domain.betting.dto.res.ListBettingResponseDto;
import post.post.global.exception.BusinessException;
import post.post.global.exception.ErrorCode;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BettingService {

    private final BettingRepository bettingRepository;

    @Transactional(readOnly = true)
    public ListBettingResponseDto getBettingCard(){
        List<BettingResponseDto> responseDtoList = bettingRepository.findAllBettingCardsWithStats();
        return ListBettingResponseDto.of(responseDtoList);
    }

    @Transactional(readOnly = true)
    public BettingResponseDto getBettingCardById(Long id){
        return bettingRepository.findBettingCardWithStatsById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.BETTING_CARD_NOT_FOUND));
    }

    @Transactional
    public Long postAddBetting(BettingAddRequestDto bettingAddRequestDto){
        BettingEntity bettingEntity = BettingEntity.saveBettingBuilder()
                .title(bettingAddRequestDto.title())
                .blue(bettingAddRequestDto.blue())
                .red(bettingAddRequestDto.red())
                .result("미정")
                .status("시작 전")
                .time(bettingAddRequestDto.time())
                .build();
        BettingEntity saveBetting = bettingRepository.save(bettingEntity);

        return saveBetting.getId();
    }

    @Transactional
    public void deleteDeleteBetting(Long id){
        bettingRepository.deleteById(id);
    }

    @Scheduled(fixedRate = 60000)
    @Transactional
    public void updateExpiredBettings() {
        bettingRepository.updateStatusForExpiredBettings("종료", LocalDateTime.now());
    }
}
