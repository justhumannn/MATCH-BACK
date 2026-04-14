package post.post.domain.betting.dto.req;

import java.time.LocalDateTime;

public record BettingAddRequestDto(
        String title,
        String blue,
        String red,
        LocalDateTime time
) {
}
