package post.post.domain.presentation.dto.req;

import lombok.Builder;
import post.post.domain.domain.entity.BettingEntity;
import post.post.domain.domain.entity.TransectionEntity;
import post.post.domain.domain.entity.UserEntity;

@Builder
public record UserBettingRequestDto (
        Long bettingId,
        String bettingTeam,
        Long bettingCost
){
    public TransectionEntity toEntity(UserEntity user,  BettingEntity betting){
        return TransectionEntity.saveTransectionBuilder()
                .userEntity(user)
                .bettingEntity(betting)
                .bettingTeam(this.bettingTeam)
                .bettingCost(this.bettingCost)
                .build();
    }
}
