package post.post.domain.presentation.dto.req;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostUpdateRequestDto {
    @NotNull(message = "아이디는 NULL이 되서는 안 됩니다.")
    // @NotNull: 필드가 null이면 요청 유효성 검증에서 실패시킴
    private Long id; // 수정하려는 게시글의 ID
    @NotNull(message = "제목은 NULL이 되서는 안 됩니다.")
    private String title; // 수정할 제목
    @NotNull(message = "내용은 NULL이 되서는 안 됩니다.")
    private String contents; // 수정할 본문
    @NotNull(message = "작가는 NULL이 되서는 안 됩니다.")
    private String writer; // 수정할 작성자
}
