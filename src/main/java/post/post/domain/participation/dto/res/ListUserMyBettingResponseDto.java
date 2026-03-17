package post.post.domain.participation.dto.res;

import lombok.Builder;

import java.util.List;

@Builder
public record ListUserMyBettingResponseDto(
        List<UserMyBettingResponseDto> bettingHistory
) {
    public static ListUserMyBettingResponseDto of(List<UserMyBettingResponseDto> userMyBettingResponseDtoList){
        return new ListUserMyBettingResponseDto(userMyBettingResponseDtoList);
    }
}
