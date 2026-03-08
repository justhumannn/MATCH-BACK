package post.post.domain.presentation.dto.res;

import lombok.Builder;

@Builder
public record UserMyBettingResponseDto(
        String title,
        String bettingTeam,
        Long bettingCost
) {

}
