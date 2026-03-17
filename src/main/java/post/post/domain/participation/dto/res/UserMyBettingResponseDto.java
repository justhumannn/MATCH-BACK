package post.post.domain.participation.dto.res;

import lombok.Builder;

@Builder
public record UserMyBettingResponseDto(
        String title,
        String bettingTeam,
        Long bettingCost
) {

}
