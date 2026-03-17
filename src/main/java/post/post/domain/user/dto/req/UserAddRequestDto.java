package post.post.domain.user.dto.req;

import lombok.Builder;

@Builder
public record UserAddRequestDto(
        String name,
        String email
) {
}
