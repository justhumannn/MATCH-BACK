package post.post.domain.participation.dto.req;

import lombok.Builder;
import post.post.domain.betting.BettingEntity;
import post.post.domain.participation.BettingParticipation;
import post.post.domain.user.UserEntity;

@Builder
public record UserBettingRequestDto (
        Long bettingId,
        String bettingTeam,
        Long bettingCost
){
    public BettingParticipation toEntity(UserEntity user, BettingEntity betting){
        return BettingParticipation.saveParticipationBuilder()
                .user(user)
                .betting(betting)
                .bettingTeam(this.bettingTeam)
                .bettingCost(this.bettingCost)
                .build();
    }
}
