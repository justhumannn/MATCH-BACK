package post.post.domain.betting;

import post.post.domain.betting.dto.res.BettingResponseDto;
import java.util.List;
import java.util.Optional;

public interface BettingRepositoryCustom {
    List<BettingResponseDto> findAllBettingCardsWithStats();
    Optional<BettingResponseDto> findBettingCardWithStatsById(Long bettingId);
}
