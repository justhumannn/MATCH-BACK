package post.post.domain.betting.dto.res;

import lombok.Builder;
import post.post.domain.betting.BettingEntity;

@Builder
public record BettingResponseDto (
        Long id,
        String title,
        String blue,
        String red,
        String result,
        String status,
        Long blueBettingCount,
        Long redBettingCount,
        Long blueBettingCost,
        Long redBettingCost
) {
}