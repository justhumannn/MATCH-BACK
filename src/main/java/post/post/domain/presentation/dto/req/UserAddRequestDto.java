package post.post.domain.presentation.dto.req;

import lombok.Builder;

@Builder
public record UserAddRequestDto(
        String name,
        String email
) {
}
