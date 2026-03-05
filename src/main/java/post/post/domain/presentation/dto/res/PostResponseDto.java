package post.post.domain.presentation.dto.res;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import post.post.domain.domain.entity.PostEntity;

@Builder
public record PostResponseDto (
    @NotNull(message = "제목은 NULL이 되서는 안 됩니다.")
    String title, // 응답에 포함될 게시글 제목

    @NotNull(message = "내용은 NULL이 되서는 안 됩니다.")
    String contents, // 응답에 포함될 본문

    @NotNull(message = "작가는 NULL이 되서는 안 됩니다.")
    String writer // 응답에 포함될 작성자
) {
    public static PostResponseDto from(PostEntity post) {
        return new PostResponseDto(
                post.getTitle(),
                post.getContents(),
                post.getWriter()
                );
    }
}
