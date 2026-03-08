package post.post.domain.presentation.dto.req;

import lombok.Builder;
import post.post.domain.domain.entity.BettingEntity;
import post.post.domain.domain.entity.UserEntity;

@Builder
public record UserBettingRequestDto (
        Long bettingId,
        String bettingTeam,
        Long bettingCost
){

}
