package post.post.domain.betting.dto.res;

import lombok.Builder;

import java.util.List;

@Builder
public record ListBettingResponseDto (
    List<BettingResponseDto> listBettingResponseDto
){
    public static ListBettingResponseDto of(List<BettingResponseDto> responseDtoList){
        return new ListBettingResponseDto(
            responseDtoList
        );
    }
}
